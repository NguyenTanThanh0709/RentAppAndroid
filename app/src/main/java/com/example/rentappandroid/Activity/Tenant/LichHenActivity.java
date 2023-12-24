package com.example.rentappandroid.Activity.Tenant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Dto.Request.Schema.AppointmentRequest;
import com.example.rentappandroid.FireBase.FirebaseHelper;
import com.example.rentappandroid.Model.Notification;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiLandrod;
import com.example.rentappandroid.api.ApiTenant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichHenActivity extends AppCompatActivity {

    private String token;
    private String phoneOwner;
    private String role;
    private String name;

    private FirebaseHelper firebaseHelper;
    private EditText editTextAppointmentDescription;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button buttonConfirm;

    private void init(){
        editTextAppointmentDescription = findViewById(R.id.editTextAppointmentDescription);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        buttonConfirm = findViewById(R.id.buttonConfirm);
    }

    private Owner landlord;
    private Owner tenant;

    private void getdata(String idt, String idl){
        landlord = new Owner();

        tenant = new Owner();
        ApiTenant.apiTenant.getOne(idt,token).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                tenant = new Owner();
                tenant = response.body();

            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {

            }
        });

        ApiLandrod.apiLandrod.getOne(idl, token).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                landlord = new Owner();
                landlord = response.body();
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_hen);
        firebaseHelper = new FirebaseHelper();
        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);

        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        role = preferences.getString("role", "");
        name = preferences.getString("name", "");
        Intent intent = getIntent();
        String idHouse = intent.getStringExtra("idhouse");
        String idowwner = intent.getStringExtra("id");
        String tenchu = intent.getStringExtra("tenchu");
        String sdtchur = intent.getStringExtra("sdtchur");
        init();
        getdata(phoneOwner,idowwner);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1; // Months are 0-indexed
                int year = datePicker.getYear();

                int hour;
                int minute;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                } else {
                    // For devices running versions earlier than Android 6.0
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                }

                // Create a formatted string with the selected date and time
                String selectedDateTime = year + "-" + month + "-" + day + " " + hour + ":" + minute;


                String des = editTextAppointmentDescription.getText().toString();
                String des1  = "Bạn Có Lịch Bạn Xem Phòng \n" + "Của khách hàng: " + name + "\n số điện thoại khách hàng: " + phoneOwner + "\n"
                        + "Chủ trọ SĐT: " + sdtchur + "\n"
                        + "Tên chủ trọ: " + tenchu + "\n" +
                        "Nội dung: " + des +
                        selectedDateTime;


                Notification notification = new Notification(UUID.randomUUID().toString()   , des1
                        , tenant, landlord, LocalDate.now().toString(), "LỊCH HẸN", landlord.get_id()+ "_" + tenant.get_id());
                firebaseHelper.addNotification(notification);


            }
        });



    }
}