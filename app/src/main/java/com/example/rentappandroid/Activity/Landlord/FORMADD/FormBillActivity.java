package com.example.rentappandroid.Activity.Landlord.FORMADD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rentappandroid.Activity.Landlord.FORMLIST.QuanLyHopDongActivity;
import com.example.rentappandroid.Adapter.ServiceChargeAdapterBill;
import com.example.rentappandroid.Dto.Reponse.ServiceCharge;
import com.example.rentappandroid.Model.HoaDon;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiHoadon;
import com.example.rentappandroid.api.ApiHopDong;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormBillActivity extends AppCompatActivity {

    private List<ServiceCharge> serviceCharges;
    private ServiceChargeAdapterBill serviceChargeAdapterBill;
    private RecyclerView listDichVubill_recycleview;
    private TextInputEditText editTextday_ngaythanhtoan;
    private EditText idHopDongEditText;
    private TextView tongTienBillTextView, chonhopdonglambill;
    private EditText editTextMotaBill;
    private Button buttonAddBill;

    private TextView kihanTextView, giaTextView, tongPriceTextView;
    private Button xacNhanButton;

    private Leasecontracts leasecontracts;
    private HoaDon hoaDon;

    private void init(){
        idHopDongEditText = findViewById(R.id.id_hopdong);
        tongTienBillTextView = findViewById(R.id.tongtien_bill);
        editTextMotaBill = findViewById(R.id.editText_mota_bill);
        buttonAddBill = findViewById(R.id.buttonAddBill);
        chonhopdonglambill = findViewById(R.id.chonhopdonglambill);
        kihanTextView = findViewById(R.id.kihan_hd);
        giaTextView = findViewById(R.id.gia_hd);
        tongPriceTextView = findViewById(R.id.tong_price_hd);
        xacNhanButton = findViewById(R.id.xacnhangia);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bill);
        init();
        serviceCharges = new ArrayList<>();
        leasecontracts = new Leasecontracts();
        hoaDon = new HoaDon();

        listDichVubill_recycleview = findViewById(R.id.listDichVubill_recycleview);
        listDichVubill_recycleview.setLayoutManager(new LinearLayoutManager(this));
        serviceChargeAdapterBill = new ServiceChargeAdapterBill(serviceCharges, this);
        listDichVubill_recycleview.setAdapter(serviceChargeAdapterBill);

        TextInputLayout textinputday_ngaythanhtoan = findViewById(R.id.textinputday_ngaythanhtoan);
        editTextday_ngaythanhtoan = textinputday_ngaythanhtoan.findViewById(R.id.editTextday_ngaythanhtoan);
        event();

        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");

        Intent intent = getIntent();
        if (intent.hasExtra("idContract")) {

            String id = intent.getStringExtra("idContract");
            ApiHopDong.apiHopDong.getallleasecontractByid(id,token).enqueue(new Callback<Leasecontracts>() {
                @Override
                public void onResponse(Call<Leasecontracts> call, Response<Leasecontracts> response) {
                    leasecontracts = response.body();
                    idHopDongEditText.setText(leasecontracts.getTenant().getPhoneNumber() + " - " + leasecontracts.getRoomingHouse().getTitle());
                    kihanTextView.setText(leasecontracts.getPayment_term() + "");
                    giaTextView.setText(leasecontracts.getRoomingHouse().getPrice() + "");
                    tongPriceTextView.setText(leasecontracts.getPayment_term()* leasecontracts.getRoomingHouse().getPrice() + "");
                    serviceCharges.addAll(leasecontracts.getRoomingHouse().getServiceCharge());
                    serviceChargeAdapterBill.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Leasecontracts> call, Throwable t) {

                }
            });



        } else if (intent.hasExtra("idBill")) {
            String id = intent.getStringExtra("idBill");
            ApiHoadon.apiHoadon.getHoaDonByContract(id,token).enqueue(new Callback<HoaDon>() {
                @Override
                public void onResponse(Call<HoaDon> call, Response<HoaDon> response) {
                    hoaDon = response.body();
                    idHopDongEditText.setText(hoaDon.getLeaseContract().getRoomingHouse().getTitle() + " -- " + hoaDon.getLeaseContract().getTenant().getPhoneNumber());
                    editTextday_ngaythanhtoan.setText(hoaDon.getPayment_date());
                    tongTienBillTextView.setText("Tổng: "+hoaDon.getAmount() + "Đ");
                    editTextMotaBill.setText(hoaDon.getDescription());
                    leasecontracts = hoaDon.getLeaseContract();

                    idHopDongEditText.setText(leasecontracts.getTenant().getPhoneNumber() + " - " + leasecontracts.getRoomingHouse().getTitle());
                    kihanTextView.setText(leasecontracts.getPayment_term() + "");
                    giaTextView.setText(leasecontracts.getRoomingHouse().getPrice() + "");
                    tongPriceTextView.setText(leasecontracts.getPayment_term()* leasecontracts.getRoomingHouse().getPrice() + "");
                    serviceCharges.addAll(leasecontracts.getRoomingHouse().getServiceCharge());
                    serviceChargeAdapterBill.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<HoaDon> call, Throwable t) {

                }
            });

        } else {
            // Không có dữ liệu được truyền
            Log.d("HopDongId", "Không có dữ liệu được truyền");
        }
    }

    private void event() {
        editTextday_ngaythanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        xacNhanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("OK","OK");
                int totalValueForFirstItem = serviceChargeAdapterBill.getTotalValueAtPosition();
                tongTienBillTextView.setText("Tổng: "+totalValueForFirstItem + "Đ");

            }
        });

        chonhopdonglambill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormBillActivity.this, QuanLyHopDongActivity.class);

                intent.putExtra("type", "bill");
                startActivity(intent);
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