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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiBaiDang;
import com.example.rentappandroid.api.ApiRoomHouse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
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
    private void event(){




        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
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
                    String id = room.getTitle() + " - " +room.get_id();
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

    private void init(){
        danhSachPhongSpinner = findViewById(R.id.danh_sach_phong_);
        phiCocPhongEditText = findViewById(R.id.phicocphong);
        moTaBaiDangTextView = findViewById(R.id.motaBaiDang);
        radioGroupStatus = findViewById(R.id.radioGroupStatus);
        troChuaDuocThueRadioButton = findViewById(R.id.trochuaduocthue);
        troDaDuocThueRadioButton = findViewById(R.id.trodaduocthue);
        hetHieuLucRadioButton = findViewById(R.id.hethieuluc);
        phonghientai = findViewById(R.id.phonghientai);
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
            String id = intent.getStringExtra("idPost");
            fillData(id);
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