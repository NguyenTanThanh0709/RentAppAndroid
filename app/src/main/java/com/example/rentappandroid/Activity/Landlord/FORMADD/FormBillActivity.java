package com.example.rentappandroid.Activity.Landlord.FORMADD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.rentappandroid.Adapter.ServiceChargeAdapterBill;
import com.example.rentappandroid.Dto.Reponse.ServiceCharge;
import com.example.rentappandroid.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FormBillActivity extends AppCompatActivity {

    private List<ServiceCharge> serviceCharges;
    private ServiceChargeAdapterBill serviceChargeAdapterBill;
    private RecyclerView listDichVubill_recycleview;
    private TextInputEditText editTextday_ngaythanhtoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bill);

        serviceCharges = new ArrayList<>();
        listDichVubill_recycleview = findViewById(R.id.listDichVubill_recycleview);
        listDichVubill_recycleview.setLayoutManager(new LinearLayoutManager(this));
        serviceChargeAdapterBill = new ServiceChargeAdapterBill(serviceCharges, this);
        listDichVubill_recycleview.setAdapter(serviceChargeAdapterBill);

        TextInputLayout textinputday_ngaythanhtoan = findViewById(R.id.textinputday_ngaythanhtoan);
        editTextday_ngaythanhtoan = textinputday_ngaythanhtoan.findViewById(R.id.editTextday_ngaythanhtoan);
        event();
    }

    private void event() {
        editTextday_ngaythanhtoan.setOnClickListener(new View.OnClickListener() {
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
                        editTextday_ngaythanhtoan.setText(selectedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}