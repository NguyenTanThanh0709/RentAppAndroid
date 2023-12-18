package com.example.rentappandroid.Activity.Tenant.HoaDon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.rentappandroid.Model.HoaDon;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiHoadon;
import com.example.rentappandroid.api.ApiHopDong;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoBillTenantActivity extends AppCompatActivity {

    private HoaDon hoaDon;
    private String token;


    private TextView  textView3, textView1, textView2, textView33, textView4 , t5;
    private EditText editText1, editText2;
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
}