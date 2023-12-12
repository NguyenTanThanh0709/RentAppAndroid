package com.example.rentappandroid.Activity.Landlord.FORMADD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Adapter.ImageAdapter;
import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiHopDong;
import com.example.rentappandroid.api.ApiRoomHouse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormContractActivity extends AppCompatActivity {

    private TextInputEditText editTextday_ngaybatdau;
    private TextInputEditText editTextday_ngayketthuc;
    private TextInputEditText editTextday_ngaythanhtoan_contract;
    private TextView phonghientai_contract, kihanhientai_contract;
    private Spinner spinner_kyhan_list;
    private Spinner danhSachPhongSpinner;
    private ImageView cccd_mattruoc;
    private ImageView cccd_matsau;
    private EditText tienPhongEditText;
    private EditText phiCocPhongEditText;
    private RecyclerView hinhanhopdong_recycleview;

    private RadioButton statusTrueRadioButton;
    private RadioButton statusFalseRadioButton;
    private RadioButton payTrueRadioButton;
    private RadioButton payFalseRadioButton;
    private EditText sdtKhachHangEditText;
    private EditText mkKhachHangEditText;
    private Button buttonAddRoom;


    private ArrayList<Uri> selectedImages = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final int GALLERY_REQUEST_CODE = 102;
    private int checkimg = -1;
    private  Uri img;
    private void init() {
        spinner_kyhan_list = findViewById(R.id.spinner_kyhan_list);
        cccd_mattruoc = findViewById(R.id.cccd_mattruoc);
        cccd_matsau = findViewById(R.id.cccd_matsau);
        danhSachPhongSpinner = findViewById(R.id.danh_sach_phong_contract);
        tienPhongEditText = findViewById(R.id.tienphong_contract);
        phiCocPhongEditText = findViewById(R.id.phicocphong_contract);
        statusTrueRadioButton = findViewById(R.id.status_true_contract);
        statusFalseRadioButton = findViewById(R.id.status_false_contract);
        payTrueRadioButton = findViewById(R.id.pay_true);
        payFalseRadioButton = findViewById(R.id.pay_false);
        sdtKhachHangEditText = findViewById(R.id.sdtKH);
        mkKhachHangEditText = findViewById(R.id.mkKH);
        buttonAddRoom = findViewById(R.id.buttonAddRoom_contract);
        phonghientai_contract = findViewById(R.id.phonghientai_contract);
        kihanhientai_contract  = findViewById(R.id.kihanhientai_contract);

    }

    private void eventData() {
        ArrayList<String> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monthList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_kyhan_list.setAdapter(adapter);

        ApiRoomHouse.apiRoom.getListRoomByOwner(phoneOwner,token).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                roomList.clear();
                roomList.addAll(response.body());
                for (Room room : roomList){
                    String id = room.getTitle() + " - " +room.get_id();
                    roomInfo.add(id);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(FormContractActivity.this, android.R.layout.simple_spinner_item, roomInfo);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                danhSachPhongSpinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {

            }
        });


    }

    private String token;
    private String phoneOwner;
    private String nameOwner;
    private Leasecontracts leasecontracts;
    private List<Room> roomList;
    private List<String> roomInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contract);

        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);
        roomList = new ArrayList<>();
        roomInfo = new ArrayList<>();
        leasecontracts = new Leasecontracts();

// Retrieve values
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        nameOwner = preferences.getString("name", "");

        TextInputLayout textinputday_ngaybatdau = findViewById(R.id.textinputday_ngaybatdau);
        editTextday_ngaybatdau = textinputday_ngaybatdau.findViewById(R.id.editTextday_ngaybatdau);
        TextInputLayout textinputday_ngayketthuc = findViewById(R.id.textinputday_ngayketthuc);
        editTextday_ngayketthuc = textinputday_ngayketthuc.findViewById(R.id.editTextday_ngayketthuc);
        TextInputLayout textinputday_ngaythanhtoan_contract = findViewById(R.id.textinputday_ngaythanhtoan_contract);
        editTextday_ngaythanhtoan_contract = textinputday_ngaythanhtoan_contract.findViewById(R.id.editTextday_ngaythanhtoan_contract);
        init();
        event();
        eventData();

        TextView chooseImgTextView = findViewById(R.id.chooseIMG_contract);
        hinhanhopdong_recycleview = findViewById(R.id.hinhanhopdong_recycleview);
        chooseImgTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkimg = 0;
                openGalleryForImage();
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        hinhanhopdong_recycleview.setLayoutManager(gridLayoutManager);
        imageAdapter = new ImageAdapter(this, selectedImages);
        hinhanhopdong_recycleview.setAdapter(imageAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            String id = intent.getStringExtra("idContract");
            fillData(id);
        }

    }

    private void fillData(String id) {
        ApiHopDong.apiHopDong.getallleasecontractByid(id,token).enqueue(new Callback<Leasecontracts>() {
            @Override
            public void onResponse(Call<Leasecontracts> call, Response<Leasecontracts> response) {
                leasecontracts = response.body();
                phonghientai_contract.setText(leasecontracts.getRoomingHouse().getTitle() + " - " + leasecontracts.getRoomingHouse().get_id());
                tienPhongEditText.setText(leasecontracts.getRoomingHouse().getPrice() + "");
                phiCocPhongEditText.setText(leasecontracts.getDeposit() + "");
                editTextday_ngaybatdau.setText(leasecontracts.getBilling_start_date());
                editTextday_ngayketthuc.setText(leasecontracts.getEnd_date());
                editTextday_ngaythanhtoan_contract.setText(leasecontracts.getBilling_start_date());
                kihanhientai_contract.setText(leasecontracts.getPayment_term() + "");
                if(leasecontracts.isStatus()){
                    statusTrueRadioButton.setChecked(true);
                }else {
                    statusFalseRadioButton.setChecked(true);
                }
                if(leasecontracts.isIs_paid()){
                    payTrueRadioButton.setChecked(true);
                }else {
                    payFalseRadioButton.setChecked(true);
                }

                sdtKhachHangEditText.setText(leasecontracts.getTenant().getPhoneNumber());
                Picasso.with(getApplicationContext())
                        .load(leasecontracts.getCccd_front())
                        .placeholder(R.drawable.house)
                        .error(R.drawable.house)
                        .into(cccd_mattruoc);
                Picasso.with(getApplicationContext())
                        .load(leasecontracts.getCccd_back())
                        .placeholder(R.drawable.house)
                        .error(R.drawable.house)
                        .into(cccd_matsau);
                for (String imageUrl : leasecontracts.getImage_url()) {
                    Log.d("Image URL", imageUrl);
                    Uri imageUri = Uri.parse(imageUrl);
                    selectedImages.add(imageUri);
                }
                checkimg = 0;
                updateImageAdapter();



            }

            @Override
            public void onFailure(Call<Leasecontracts> call, Throwable t) {

            }
        });
    }



    private void event() {
        editTextday_ngaybatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v, "1");
            }
        });
        editTextday_ngayketthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v, "2");
            }
        });
        editTextday_ngaythanhtoan_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v, "3");
            }
        });

        cccd_mattruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkimg = 1;
                openGalleryForImage();
            }
        });

        cccd_matsau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkimg = 2;
                openGalleryForImage();
            }
        });
    }

    public void showDatePickerDialog(View view, String check) {
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
                        if (check.equals("1")) {
                            editTextday_ngaybatdau.setText(selectedDate);
                        } else if (check.equals("2")) {
                            editTextday_ngayketthuc.setText(selectedDate);
                        } else if (check.equals("3")) {
                            editTextday_ngaythanhtoan_contract.setText(selectedDate);
                        }
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void requestPermissionAndOpenGallery() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            openGallery();
        }
    }

    private void openGalleryForImage() {
        requestPermissionAndOpenGallery();
    }


    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // Allow multiple image selection
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission denied. Cannot open gallery.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_CODE) {
                if (data.getClipData() != null) {
                    // Multiple images selected
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        selectedImages.add(imageUri);
                    }
                    updateImageAdapter();
                } else if (data.getData() != null) {
                    // Single image selected
                    Uri imageUri = data.getData();
                    img = imageUri;
                    updateImageAdapter();
                }
            }
        }
    }

    private void updateImageAdapter() {
        if(checkimg == 0){
            imageAdapter = new ImageAdapter(this, selectedImages);
            hinhanhopdong_recycleview.setAdapter(imageAdapter);
        }else if(checkimg ==1){
            cccd_mattruoc.setImageURI(img);
        }else  if(checkimg ==2){
            cccd_matsau.setImageURI(img);
        }


    }
}
