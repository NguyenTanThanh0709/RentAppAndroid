package com.example.rentappandroid.Activity.Tenant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Dto.Request.Schema.AppointmentRequest;
import com.example.rentappandroid.R;

import java.util.ArrayList;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class LichHenActivity extends AppCompatActivity {

    private String token;
    private String phoneOwner;
    private String role;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_hen);

        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);

        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        role = preferences.getString("role", "");
        Intent intent = getIntent();
        String idHouse = intent.getStringExtra("idhouse");
        String idowwner = intent.getStringExtra("id");
        init();

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
                AppointmentRequest appointmentRequest = new AppointmentRequest(phoneOwner,idHouse,des,selectedDateTime, "UNCONFIRMED");


            }
        });



    }
}