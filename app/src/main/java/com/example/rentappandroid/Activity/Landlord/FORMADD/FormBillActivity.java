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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Activity.Landlord.FORMLIST.QuanLyHopDongActivity;
import com.example.rentappandroid.Adapter.ServiceChargeAdapterBill;
import com.example.rentappandroid.Dto.Reponse.ServiceCharge;
import com.example.rentappandroid.Dto.Request.Add.BillRequest;
import com.example.rentappandroid.FireBase.FirebaseHelper;
import com.example.rentappandroid.Global.NotificationHelper;
import com.example.rentappandroid.Model.HoaDon;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.Model.Mess;
import com.example.rentappandroid.Model.Notification;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiHoadon;
import com.example.rentappandroid.api.ApiHopDong;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

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
    private FirebaseHelper firebaseHelper;
    private TextView kihanTextView, giaTextView, tongPriceTextView;
    private RadioButton truebill, falsebill;
    private Button xacNhanButton;

    private Leasecontracts leasecontracts;
    private HoaDon hoaDon;
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void Add() {
        int amount = 0;
        try {
            amount = Integer.parseInt(tongTienBillTextView.getText().toString().replace("Đ", "").replace("Tổng: ", ""));
            if (amount <= 0) {
                Toast.makeText(this, "Xem lại số tiền", Toast.LENGTH_SHORT).show();
                return;

            }
        } catch (NumberFormatException e) {

        }
        String date = editTextday_ngaythanhtoan.getText().toString();
        String des = editTextMotaBill.getText().toString();
        if(leasecontracts == null){
            Toast.makeText(this, "Vui Chọn hóa đơn", Toast.LENGTH_SHORT).show();
            return;
        }
        String idContract = leasecontracts.get_id();

        if(date.isEmpty()){

        }

        BillRequest billRequest =  new BillRequest(amount,date,des,idContract,false);

        if(type.equals("")){
            ApiHoadon.apiHoadon.add(billRequest,token).enqueue(new Callback<Mess>() {
                @Override
                public void onResponse(Call<Mess> call, Response<Mess> response) {
                    if (response.isSuccessful()) {
                        showToast("Thêm hóa đơn thành công, và đã được gửi đến khách hàng");
                        Notification notification = new Notification(UUID.randomUUID().toString()   , "Hóa đơn tiền nhà tháng này của bạn nè \n " + billRequest.getPayment_date() + "\n vui lòng thanh toán"
                                , leasecontracts.getTenant(), leasecontracts.getLandlord(), LocalDate.now().toString(), "HÓA ĐƠN",response.body().getMessage()
                                );
                        firebaseHelper.addNotification(notification);
                        NotificationHelper.showNotification(FormBillActivity.this, "HÓA ĐƠN", "Hóa đơn tiền nhà tháng  này của bạn nè \n " + billRequest.getPayment_date() + "\n vui lòng thanh toán");

                        Log.d("API Call Success", "API call was successful");
                    } else {
                        showToast("Add Bill Thất Bại");

                        // Log information when the API call is not successful
                        Log.e("API Call Error", "Error during API call. Response code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Mess> call, Throwable t) {
                    showToast("Add Bill Thất Bại");
                }
            });
        }else {
            ApiHoadon.apiHoadon.put(idBill,billRequest,token).enqueue(new Callback<Mess>() {
                @Override
                public void onResponse(Call<Mess> call, Response<Mess> response) {
                    if (response.isSuccessful()) {
                        showToast("chỉnh sửa hóa đơn thành công, và đã được gửi đến khách hàng");
                        Notification notification = new Notification(UUID.randomUUID().toString()   , "(có chỉnh sửa)Hóa đơn tiền nhà tháng  này của bạn nè \n " + billRequest.getPayment_date() + "\n vui lòng thanh toán"
                                , leasecontracts.getTenant(), leasecontracts.getLandlord(), LocalDate.now().toString(), "HÓA ĐƠN",response.body().getMessage()
                        );
                        firebaseHelper.addNotification(notification);
                        NotificationHelper.showNotification(FormBillActivity.this, "HÓA ĐƠN", "(có chỉnh sửa)Hóa đơn tiền nhà tháng  này của bạn nè \n " + billRequest.getPayment_date() + "\n vui lòng thanh toán");
                    } else {
                        showToast("update Bill Thất Bại");

                        // Log information when the API call is not successful
                        Log.e("API Call Error", "Error during API call. Response code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Mess> call, Throwable t) {
                    showToast("update Bill Thất Bại");
                }
            });
        }



    }
    private void init(){
        truebill = findViewById(R.id.status_true_bill);
        falsebill = findViewById(R.id.status_false_bill);
        idHopDongEditText = findViewById(R.id.id_hopdong);
        tongTienBillTextView = findViewById(R.id.tongtien_bill);
        editTextMotaBill = findViewById(R.id.editText_mota_bill);
        buttonAddBill = findViewById(R.id.buttonAddBill);
        chonhopdonglambill = findViewById(R.id.chonhopdonglambill);
        kihanTextView = findViewById(R.id.kihan_hd);
        giaTextView = findViewById(R.id.gia_hd);
        tongPriceTextView = findViewById(R.id.tong_price_hd);
        xacNhanButton = findViewById(R.id.xacnhangia);
        buttonAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add();
            }
        });
    }


    private String token;
    private String phoneOwner;
    private String nameOwner;
    private String type;
    private String idBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bill);
        firebaseHelper = new FirebaseHelper();
        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        nameOwner = preferences.getString("name", "");
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


        Intent intent = getIntent();
        if (intent.hasExtra("idContract")) {
            type = (type != null) ? type : "";
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
             idBill = intent.getStringExtra("idBill");
            type= intent.getStringExtra("type");
            type = (type != null) ? type : "";
            ApiHoadon.apiHoadon.getHoaDonByContract(idBill,token).enqueue(new Callback<HoaDon>() {
                @Override
                public void onResponse(Call<HoaDon> call, Response<HoaDon> response) {
                    hoaDon = response.body();
                    idHopDongEditText.setText(hoaDon.getLeaseContract().getRoomingHouse().getTitle() + " -- " + hoaDon.getLeaseContract().getTenant().getPhoneNumber());
                    editTextday_ngaythanhtoan.setText(hoaDon.getPayment_date());
                    tongTienBillTextView.setText("Tổng: "+hoaDon.getAmount() + "Đ");
                    editTextMotaBill.setText(hoaDon.getDescription());
                    if(hoaDon.getStatus()){
                        truebill.setChecked(true);
                    }else {
                        falsebill.setChecked(true);
                    }
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