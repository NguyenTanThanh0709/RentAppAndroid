package com.example.rentappandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Adapter.DistrictAdapter;
import com.example.rentappandroid.Adapter.ProvincesAdapter;
import com.example.rentappandroid.Adapter.WardAdapter;
import com.example.rentappandroid.Dto.District;
import com.example.rentappandroid.Dto.Provinces;
import com.example.rentappandroid.Dto.Ward;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiAddress;

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
    }

    private void showRoleSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bạn Là Ai?")
                .setItems(new CharSequence[]{"Người Cho Thuê", "Người Đi Tìm Phòng"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the selected role
                        switch (which) {
                            case 0:
                                // Người Cho Thuê
                                // Implement your logic here
                                address.setVisibility(View.VISIBLE);
                                spinner.setVisibility(View.VISIBLE);
                                Toast.makeText(RegisterActivity.this, "Người Cho Thuê", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                // Người Đi Tìm Phòng
                                // Implement your logic here
                                Toast.makeText(RegisterActivity.this, "Người Đi Tìm Phòng", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // Dismiss the dialog only if a role is selected
                        dialog.dismiss();
                    }
                });

        // Set a listener to handle the dialog cancellation
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                showRoleSelectionDialog();
                // Handle the case when the user cancels the dialog without selecting a role
                // You can display a message or take appropriate action
                Toast.makeText(RegisterActivity.this, "Vui lòng chọn một vai trò", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    private  void event(){
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
        showRoleSelectionDialog();
    }
}