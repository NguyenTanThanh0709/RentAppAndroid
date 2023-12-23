package com.example.rentappandroid.Activity.Landlord.FORMADD;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Dto.Request.Add.PostRequest;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiBaiDang;
import com.example.rentappandroid.api.ApiRoomHouse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPostActivity extends AppCompatActivity {

    private TextInputEditText editTextDate;
    private Spinner danhSachPhongSpinner;
    private EditText phiCocPhongEditText;
    private TextView phonghientai;
    private MultiAutoCompleteTextView moTaBaiDangTextView;
    private RadioGroup radioGroupStatus;
    private RadioButton troChuaDuocThueRadioButton;
    private RadioButton troDaDuocThueRadioButton;
    private RadioButton hetHieuLucRadioButton;
    private String id = "";
    private String type = "";
     private void event(){


         buttonAddPOST.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String selectedItem = danhSachPhongSpinner.getSelectedItem().toString();
                 if (selectedItem.isEmpty()) {
                     // Handle the case when no item is selected in the spinner
                     showToast("Please select a room.");
                     return;
                 }
                 String[] split = selectedItem.split("-");
                 String idTro = split[split.length-1];

                 String depositText = phiCocPhongEditText.getText().toString();
                 if (depositText.isEmpty()) {
                     // Handle the case when the deposit is empty
                     showToast("Please enter a deposit value.");
                     return;
                 }
                 int deposit;
                 try {
                      deposit = Integer.parseInt(depositText);
                     // The 'deposit' variable now contains the valid integer value
                 } catch (NumberFormatException e) {
                     // Handle the case when the input is not a valid integer
                     showToast("Invalid deposit value. Please enter a valid number.");
                     return;
                 }

                 String date = editTextDate.getText().toString();
                 if (date.isEmpty()) {
                     // Handle the case when the date is empty
                     showToast("Please enter a date.");
                     return;
                 }

                 String mota = moTaBaiDangTextView.getText().toString();
                 if (mota.isEmpty()) {
                     // Handle the case when the description is empty
                     showToast("Please enter a description.");
                     return;
                 }

                 String status = "";
                 if (troChuaDuocThueRadioButton.isChecked()) {
                     // Handle the case when "Trọ Chưa Được Thuê" is selected
                     status = "Trọ Chưa Được Thuê";
                 } else if (troDaDuocThueRadioButton.isChecked()) {
                     // Handle the case when "Trọ Đã Được Thuê" is selected
                     status = "Trọ Đã Được Thuê";
                 } else if (hetHieuLucRadioButton.isChecked()) {
                     // Handle the case when "Hết Hiệu Lực" is selected
                     status = "Bài Viết Hết Hiệu Lực";
                 } else {
                     showToast("Please check a status.");
                     return;
                 }


                 PostRequest postRequest = new PostRequest(phoneOwner,deposit,
                         date, idTro,mota,status
                         );

                 if(type.equals("")){
                     ApiBaiDang.apiBaiDang.Add(postRequest,token).enqueue(new Callback<Void>() {
                         @Override
                         public void onResponse(Call<Void> call, Response<Void> response) {
                             if (response.isSuccessful()) {
                                 showToast("Thêm Bài Thành Công");
                                 Log.d("API Call Success", "API call was successful");
                             } else {
                                 showToast("Thêm Bài Thất Bại");

                                 // Log information when the API call is not successful
                                 Log.e("API Call Error", "Error during API call. Response code: " + response.code());
                                 String errorMessage = "Thêm Bài Thất Bại\n" + response.message();
                                 showToast(errorMessage);
                             }
                         }

                         @Override
                         public void onFailure(Call<Void> call, Throwable t) {
                             showToast("Thêm Bài Thất Bại");

                             // Log the error
                             Log.e("API Call Error", "Error during API call", t);

                             // You can also log the error message
                             // Log.e("API Call Error", "Error message: " + t.getMessage());

                             // If you want to display the error message in the toast, you can do something like this:
                             String errorMessage = "Thêm Bài Thất Bại\n" + t.getMessage();
                             showToast(errorMessage);
                         }
                     });

                 }else {
                     if(!postRequest.getDay_up().contains("/")){


                     String inputDateString = postRequest.getDay_up();

                     // Parse the input string to Instant
                     Instant instant = Instant.parse(inputDateString);

                     // Convert Instant to LocalDateTime
                     LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));

                     // Format LocalDateTime to the desired format
                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                     String formattedDate = localDateTime.format(formatter);
                     postRequest.setDay_up(formattedDate);
                     }
                     ApiBaiDang.apiBaiDang.update(id,postRequest,token).enqueue(new Callback<Void>() {
                         @Override
                         public void onResponse(Call<Void> call, Response<Void> response) {
                             if (response.isSuccessful()) {
                                 showToast("Chỉnh Bài Thành Công");
                                 Log.d("API Call Success", "API call was successful");
                             } else {
                                 showToast("Thêm Chỉnh Thất Bại");

                                 // Log information when the API call is not successful
                                 Log.e("API Call Error", "Error during API call. Response code: " + response.code());
                                 String errorMessage = "Chỉnh Bài Thất Bại\n" + response.message();
                                 showToast(errorMessage);
                             }
                         }

                         @Override
                         public void onFailure(Call<Void> call, Throwable t) {
                             showToast("Chỉnh Thất Bại");

                         }
                     });
                 }


             }
         });

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    public void showDatePickerDialog(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String selectedDate = day + "/" + (month + 1) + "/" + year;
                        editTextDate.setText(selectedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void setCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());
        editTextDate.setText(currentDate);
    }

    private BaiViet baiViet;
    private List<Room> roomList;
    private List<String> roomInfo;

    private void getData(){
        roomInfo = new ArrayList<>();
        ApiRoomHouse.apiRoom.getListRoomByOwner(phoneOwner,token).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                roomList.clear();
                roomList.addAll(response.body());
                for (Room room : roomList){
                    String id = room.getAddress().getFullAddress() + "-"+ room.getTitle() + "-" +room.get_id();
                    roomInfo.add(id);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(FormPostActivity.this, android.R.layout.simple_spinner_item, roomInfo);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                danhSachPhongSpinner.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });
    }

    private String token;
    private String phoneOwner;
    private String nameOwner;
    private Button buttonAddPOST;

    private void init(){
        danhSachPhongSpinner = findViewById(R.id.danh_sach_phong_);
        phiCocPhongEditText = findViewById(R.id.phicocphong);
        moTaBaiDangTextView = findViewById(R.id.motaBaiDang);
        radioGroupStatus = findViewById(R.id.radioGroupStatus);
        troChuaDuocThueRadioButton = findViewById(R.id.trochuaduocthue);
        troDaDuocThueRadioButton = findViewById(R.id.trodaduocthue);
        hetHieuLucRadioButton = findViewById(R.id.hethieuluc);
        phonghientai = findViewById(R.id.phonghientai);
        buttonAddPOST = findViewById(R.id.buttonAddPOST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_post);

        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        nameOwner = preferences.getString("name", "");
        init();
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayoutDate);
        editTextDate = textInputLayout.findViewById(R.id.editTextDate);
        roomList = new ArrayList<>();
        baiViet  = new BaiViet();
        getData();
        setCurrentDate();
        event();

        danhSachPhongSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle item selection, you can get the selected item using roomInfo.get(position)
                String selectedRoomInfo = roomInfo.get(position);
                phonghientai.setText(selectedRoomInfo);
                // Do something with the selected room info
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
             id = intent.getStringExtra("idPost");
             type = intent.getStringExtra("type");
            id = (id != null) ? id : "";

// If type is null, set it to an empty string
            type = (type != null) ? type : "";
            if(type.equals("edit")){
                fillData(id);
            }

        }
    }

    private void fillData(String id) {

        ApiBaiDang.apiBaiDang.getallBaiDangByid(id,token).enqueue(new Callback<BaiViet>() {
            @Override
            public void onResponse(Call<BaiViet> call, Response<BaiViet> response) {
                baiViet = response.body();

                phonghientai.setText("Phòng hiện tại: "+baiViet.getRoom().getTitle());
                phiCocPhongEditText.setText(baiViet.getDeposit() + "");
                moTaBaiDangTextView.setText(baiViet.getDescription());
                if(baiViet.getStatus().equals("Trọ Chưa Được Thuê")){
                    troChuaDuocThueRadioButton.setChecked(true);

                }else if(baiViet.getStatus().equals("Trọ Đã Được Thuê")){
                    troDaDuocThueRadioButton.setChecked(true);
                }else if(baiViet.getStatus().equals("Bài Viết Hết Hiệu Lực")){
                    hetHieuLucRadioButton.setChecked(true);
                }
                editTextDate.setText(baiViet.getDay_up());

                Log.d("OK", response.body().toString());

            }

            @Override
            public void onFailure(Call<BaiViet> call, Throwable t) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_back) {
            // Handle menu item 1 click
            // Example: replace the fragment with a different fragment
            this.finish();
            return  true;
        }  else {
            return super.onOptionsItemSelected(item);
        }
    }

}