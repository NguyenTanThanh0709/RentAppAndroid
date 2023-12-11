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
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Adapter.ImageAdapter;
import com.example.rentappandroid.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class FormContractActivity extends AppCompatActivity {

    private TextInputEditText editTextday_ngaybatdau;
    private TextInputEditText editTextday_ngayketthuc;
    private TextInputEditText editTextday_ngaythanhtoan_contract;
    private Spinner spinner_kyhan_list;

    private RecyclerView hinhanhopdong_recycleview;
    private ArrayList<Uri> selectedImages = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final int GALLERY_REQUEST_CODE = 102;
    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView cccd_mattruoc;
    private ImageView cccd_matsau;
    private int checkimg = -1;
    private  Uri img;
    private void init() {
        spinner_kyhan_list = findViewById(R.id.spinner_kyhan_list);
        cccd_mattruoc = findViewById(R.id.cccd_mattruoc);
        cccd_matsau = findViewById(R.id.cccd_matsau);
    }

    private void eventData() {
        ArrayList<String> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monthList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_kyhan_list.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contract);
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
