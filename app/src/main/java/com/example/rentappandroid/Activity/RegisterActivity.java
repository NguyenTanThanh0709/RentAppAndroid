package com.example.rentappandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Adapter.DistrictAdapter;
import com.example.rentappandroid.Adapter.ProvincesAdapter;
import com.example.rentappandroid.Adapter.WardAdapter;
import com.example.rentappandroid.Dto.District;
import com.example.rentappandroid.Dto.Provinces;
import com.example.rentappandroid.Dto.Request.Add.UserRegister;
import com.example.rentappandroid.Dto.Ward;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiAddress;
import com.example.rentappandroid.api.ApiLandrod;
import com.example.rentappandroid.api.ApiTenant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Spinner provincesSpinner;
    private Spinner districtSpinner;
    private Spinner wardsSpinner;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private EditText passwordEditText;
    private EditText repasswordEditText;
    private Button registerButton;
    private TextView loginTextView;

    private TextView address;
    private LinearLayoutCompat spinner;

    private List<Provinces> listProvicense;
    private  List<District> districtList;
    private  List<Ward> wardList;

    private RadioGroup radioGroup;
    private RadioButton radioButtonTenant;
    private RadioButton radioButtonLandlord;

    private void init(){
        nameEditText = findViewById(R.id.name_register);
        provincesSpinner = findViewById(R.id.provinces);
        districtSpinner = findViewById(R.id.district);
        wardsSpinner = findViewById(R.id.wards);
        emailEditText = findViewById(R.id.email_register);
        phoneNumberEditText = findViewById(R.id.phoneNumber_register);
        passwordEditText = findViewById(R.id.password_register);
        repasswordEditText = findViewById(R.id.repassword_register);
        registerButton = findViewById(R.id.button_register);
        loginTextView = findViewById(R.id.login);
        address = findViewById(R.id.address);
        spinner = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radioGroup1);
        radioButtonTenant = findViewById(R.id.radioButtonTenant1);
        radioButtonLandlord = findViewById(R.id.radioButtonLandlord1);
    }


    private  void event(){

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String sdt = phoneNumberEditText.getText().toString().trim();
                String mk = passwordEditText.getText().toString().trim();
                String remk = repasswordEditText.getText().toString().trim();
                String selectedProvince = provincesSpinner.getSelectedItem().toString();
                String selectedDistrict = districtSpinner.getSelectedItem().toString();
                String selectedWard = wardsSpinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(name)) {
                    // Show an error and stop further processing
                    Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    return; // Stop further processing
                }

// Check if email is a valid email address
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    // Show an error and stop further processing
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    return; // Stop further processing
                }

// Check if password and re-entered password are the same
                if (!mk.equals(remk) || TextUtils.isEmpty(mk)) {
                    // Show an error and stop further processing
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return; // Stop further processing
                }

// Check if other fields are not null or empty
                if (TextUtils.isEmpty(sdt)) {
                    // Show an error and stop further processing
                    Toast.makeText(getApplicationContext(), "Phone number cannot be empty", Toast.LENGTH_SHORT).show();
                    return; // Stop further processing
                }

                if (selectedProvince.equals("Chọn Tỉnh") || selectedDistrict.equals("Chọn Quận") || selectedWard.equals("Chọn Phường")) {
                    // Show an error and stop further processing
                    Toast.makeText(getApplicationContext(), "Please select province, district, and ward", Toast.LENGTH_SHORT).show();
                    return; // Stop further processing
                }

                String address = selectedProvince+"-" + selectedDistrict + "-" + selectedWard;
                if (radioButtonTenant.isChecked()) {

                    UserRegister userRegister = new UserRegister(name,email,mk,sdt,address,"USER");
                    ApiTenant.apiTenant.register(userRegister).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (radioButtonLandlord.isChecked()) {
                    UserRegister userRegister = new UserRegister(name,email,mk,sdt,address,"ADMIN");
                    ApiLandrod.apiLandrod.register(userRegister).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang LoginActivity khi loginTextView được nhấn
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                // Kết thúc hoạt động hiện tại (RegisterActivity)
                finish();
            }
        });

        ApiAddress.apiDriverTrip.getListProvices().enqueue(new Callback<List<Provinces>>() {
            @Override
            public void onResponse(Call<List<Provinces>> call, Response<List<Provinces>> response) {
                listProvicense =  response.body();
                listProvicense.add(0, new Provinces("Chọn Tỉnh", -1));
                ProvincesAdapter provincesAdapter = new ProvincesAdapter(RegisterActivity.this, listProvicense);
                provincesSpinner.setAdapter(provincesAdapter);
                Log.d("OK","OK");
            }

            @Override
            public void onFailure(Call<List<Provinces>> call, Throwable t) {

            }
        });

        provincesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Lấy thông tin của mục được chọn từ danh sách Provinces
                Provinces selectedProvince = listProvicense.get(position);
                ApiAddress.apiDriverTrip.getListDistricts().enqueue(new Callback<List<District>>() {
                    @Override
                    public void onResponse(Call<List<District>> call, Response<List<District>> response) {
                        List<District> listtemp = new ArrayList<>();
                        listtemp = response.body();
                        districtList.clear();
                        districtList.add(new District("Chọn Quận", -1,-1));
                        for (District district: listtemp){
                            if(district.getProvince_code() == selectedProvince.getCode()){
                                districtList.add(district);
                            }
                        }
                        DistrictAdapter prcesAdapter = new DistrictAdapter(RegisterActivity.this, districtList);
                        districtSpinner.setAdapter(prcesAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<District>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                District district_ = districtList.get(i);
                ApiAddress.apiDriverTrip.getListWard().enqueue(new Callback<List<Ward>>() {
                    @Override
                    public void onResponse(Call<List<Ward>> call, Response<List<Ward>> response) {
                        List<Ward> listtemp = new ArrayList<>();
                        listtemp = response.body();
                        wardList.clear();
                        wardList.add(new Ward("Chọn Phường", -1,-1));
                        for (Ward district: listtemp){
                            if(district.getDistrict_code() == district_.getCode()){
                                wardList.add(district);
                            }
                        }
                        WardAdapter prceswAdapter = new WardAdapter(RegisterActivity.this, wardList);
                        wardsSpinner.setAdapter(prceswAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Ward>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        listProvicense = new ArrayList<>();
        districtList = new ArrayList<>();
        districtList.add(new District("Chọn Quận", -1,-1));
        wardList = new ArrayList<>();
        wardList.add(new Ward("Chọn Phường", -1,-1));
        init();
        event();
    }
}