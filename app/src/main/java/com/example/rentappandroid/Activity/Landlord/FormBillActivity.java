package com.example.rentappandroid.Activity.Landlord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rentappandroid.Adapter.ServiceChargeAdapterBill;
import com.example.rentappandroid.Dto.Reponse.ServiceCharge;
import com.example.rentappandroid.R;

import java.util.ArrayList;
import java.util.List;

public class FormBillActivity extends AppCompatActivity {

    private List<ServiceCharge> serviceCharges;
    private ServiceChargeAdapterBill serviceChargeAdapterBill;
    private RecyclerView listDichVubill_recycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bill);

        serviceCharges = new ArrayList<>();
        listDichVubill_recycleview = findViewById(R.id.listDichVubill_recycleview);
        listDichVubill_recycleview.setLayoutManager(new LinearLayoutManager(this));
        serviceChargeAdapterBill = new ServiceChargeAdapterBill(serviceCharges, this);
        listDichVubill_recycleview.setAdapter(serviceChargeAdapterBill);
    }
}