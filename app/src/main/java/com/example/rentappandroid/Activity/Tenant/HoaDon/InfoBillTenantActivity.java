package com.example.rentappandroid.Activity.Tenant.HoaDon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Dto.Request.Add.BillRequest;
import com.example.rentappandroid.Model.HoaDon;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.Model.Mess;
import com.example.rentappandroid.R;
import com.example.rentappandroid.ZaloPay.Api.CreateOrder;
import com.example.rentappandroid.api.ApiHoadon;
import com.example.rentappandroid.api.ApiHopDong;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class InfoBillTenantActivity extends AppCompatActivity {

    private HoaDon hoaDon;
    private String token;


    private TextView  textView3, textView1,editText2, textView2, textView33, textView4 , t5;
    private EditText editText1 ;
    private Button button;
    private void init(){
        textView1 = findViewById(R.id.bill_tenant_kihan_hd);
        textView2 = findViewById(R.id.bill_tenant_gia_hd);
        textView33 = findViewById(R.id.bill_tenant_tong_price_hd);
        textView4 = findViewById(R.id.bill_tenant_phidichvu);

                t5 = findViewById(R.id.bill_tenant_textView_mota_bill);
        textView3 = findViewById(R.id.bill_tenant_tongtien_bill);
        editText1 = findViewById(R.id.bill_tenant_id_hopdong);
        editText2 = findViewById(R.id.bill_tenant_editTextday_ngaythanhtoan);
        button = findViewById(R.id.bill_tenant_buttonAddBill);
        eventButton();
    }

    private void eventButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZaloPayRequest();
            }
        });
    }

    private void ZaloPayRequest() {
        CreateOrder orderApi = new CreateOrder();
        try {
            JSONObject data = orderApi.createOrder(hoaDon.getAmount() + "");
            String code = data.getString("return_code");
            Toast.makeText(getApplicationContext(), "return_code: " + code, Toast.LENGTH_LONG).show();

            if (code.equals("1")) {
                String token = data.getString("zp_trans_token");
                BillRequest billRequest = new BillRequest(hoaDon.getAmount(), hoaDon.getPayment_date(), hoaDon.getDescription(),hoaDon.getLeaseContract().get_id(),true);

                ApiHoadon.apiHoadon.put(hoaDon.get_id(),billRequest, token).enqueue(new Callback<Mess>() {
                    @Override
                    public void onResponse(Call<Mess> call, Response<Mess> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(InfoBillTenantActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(InfoBillTenantActivity.this,"Thanh Toán Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Mess> call, Throwable t) {
                        Toast.makeText(InfoBillTenantActivity.this,"Thanh Toán Thất Bại", Toast.LENGTH_SHORT).show();

                    }
                });
                ZaloPaySDK.getInstance().payOrder(InfoBillTenantActivity.this, token, "demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(String s, String s1, String s2) {
                        Toast.makeText(InfoBillTenantActivity.this,"Thanh Toán Thành công", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPaymentCanceled(String s, String s1) {
                        Toast.makeText(InfoBillTenantActivity.this,"Thanh Toán Thất Bại", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                        Toast.makeText(InfoBillTenantActivity.this,"Thanh Toán Thất Bại", Toast.LENGTH_SHORT).show();

                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void anhxa(){
        editText1.setText(hoaDon.getLeaseContract().getRoomingHouse().getAddress().getFullAddress());
        editText2.setText(hoaDon.getPayment_date());
        textView3.setText("Tổng Tiền: "  + hoaDon.getAmount() + "");

        textView1.setText(hoaDon.getLeaseContract().getPayment_term() + "");
        textView2.setText(hoaDon.getLeaseContract().getRoomingHouse().getPrice() + "");
        int ok = hoaDon.getLeaseContract().getPayment_term() * hoaDon.getLeaseContract().getRoomingHouse().getPrice();
        textView33.setText(ok + "");
        int ok_ = hoaDon.getAmount() - ok;
        textView4.setText("Phí Dịch Vụ: " + ok_ );
        t5.setText(hoaDon.getDescription());


    }
    private void getData(){
        Intent intent = getIntent();
        if (intent.hasExtra("idBill")) {
            String id = intent.getStringExtra("idBill");
            ApiHoadon.apiHoadon.getHoaDonByContract(id,token).enqueue(new Callback<HoaDon>() {
                @Override
                public void onResponse(Call<HoaDon> call, Response<HoaDon> response) {
                    hoaDon = response.body();
                    anhxa();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_bill_tenant);
        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        hoaDon = new HoaDon();
        init();
        getData();

        //ZALOPAY
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_back) {
            // Handle menu item 1 click
            // Example: replace the fragment with a different fragment
            this.finish();
            return  true;
        }  else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}