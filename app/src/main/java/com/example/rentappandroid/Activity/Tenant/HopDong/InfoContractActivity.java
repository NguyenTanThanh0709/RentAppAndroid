package com.example.rentappandroid.Activity.Tenant.HopDong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rentappandroid.Adapter.ImageAdapter;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiHopDong;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoContractActivity extends AppCompatActivity {
    private TextView tvPhongHientai;
    private EditText etTienPhong, etPhiCocPhong;
    private TextInputEditText etNgayBatDau, etNgayKetThuc, etNgayThanhToan;
    private TextView spnKyHan;
    private RecyclerView recyclerViewHinhAnh;
    private RadioButton rbStatusTrue, rbStatusFalse, rbPayTrue, rbPayFalse;
    private ImageView imgCccdMatTruoc, imgCccdMatSau;

    private ArrayList<Uri> selectedImages = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private void fillData(String id) {
        ApiHopDong.apiHopDong.getallleasecontractByid(id,token).enqueue(new Callback<Leasecontracts>() {
            @Override
            public void onResponse(Call<Leasecontracts> call, Response<Leasecontracts> response) {
                leasecontracts = response.body();
                tvPhongHientai.setText(leasecontracts.getRoomingHouse().getAddress().getFullAddress() + " - " + leasecontracts.getRoomingHouse().getTitle() );
                etTienPhong.setText(leasecontracts.getRoomingHouse().getPrice() + " vnd/tháng");
                etPhiCocPhong.setText(leasecontracts.getDeposit() + " vnđ");
                etNgayBatDau.setText(leasecontracts.getStart_date());
                etNgayKetThuc.setText(leasecontracts.getEnd_date());
                etNgayThanhToan.setText(leasecontracts.getBilling_start_date());
                spnKyHan.setText(leasecontracts.getPayment_term() + "/tháng");
                if(leasecontracts.isIs_paid()){
                    rbPayTrue.setChecked(true);
                }else {
                    rbPayFalse.setChecked(true);
                }
                if(leasecontracts.isStatus()){
                    rbStatusTrue.setChecked(true);
                }else {
                    rbStatusFalse.setChecked(true);
                }
                Picasso.with(getApplicationContext())
                        .load(leasecontracts.getCccd_front())
                        .placeholder(R.drawable.house)
                        .error(R.drawable.house)
                        .into(imgCccdMatTruoc);
                Picasso.with(getApplicationContext())
                        .load(leasecontracts.getCccd_back())
                        .placeholder(R.drawable.house)
                        .error(R.drawable.house)
                        .into(imgCccdMatSau);

                for (String imageUrl : leasecontracts.getImage_url()) {
                    Log.d("Image URL", imageUrl);
                    Uri imageUri = Uri.parse(imageUrl);
                    selectedImages.add(imageUri);
                }
            }

            @Override
            public void onFailure(Call<Leasecontracts> call, Throwable t) {

            }
        });
    }

    private  void init(){
        tvPhongHientai = findViewById(R.id.info_tenant_contract_phonghientai_contract);
        etTienPhong = findViewById(R.id.info_tenant_contract_tienphong_contract);
        etPhiCocPhong = findViewById(R.id.info_tenant_contract_phicocphong_contract);
        etNgayBatDau = findViewById(R.id.info_tenant_contract_editTextday_ngaybatdau);
        etNgayKetThuc = findViewById(R.id.info_tenant_contract_editTextday_ngayketthuc);
        etNgayThanhToan = findViewById(R.id.info_tenant_contract_editTextday_ngaythanhtoan_contract);
        spnKyHan = findViewById(R.id.info_tenant_contract_kihanhientai_contract);
        recyclerViewHinhAnh = findViewById(R.id.info_tenant_contract_hinhanhopdong_recycleview);
        rbStatusTrue = findViewById(R.id.info_tenant_contract_status_true_contract);
        rbStatusFalse = findViewById(R.id.info_tenant_contract_status_false_contract);
        rbPayTrue = findViewById(R.id.info_tenant_contract_pay_true);
        rbPayFalse = findViewById(R.id.info_tenant_contract_pay_false);
        imgCccdMatTruoc = findViewById(R.id.info_tenant_contract_cccd_mattruoc);
        imgCccdMatSau = findViewById(R.id.info_tenant_contract_cccd_matsau);
    }

    private String token;
    private String phoneOwner;
    private String nameOwner;
    private Leasecontracts leasecontracts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_contract);
        init();
        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);

        leasecontracts = new Leasecontracts();

// Retrieve values
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        nameOwner = preferences.getString("name", "");
        selectedImages = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        recyclerViewHinhAnh.setLayoutManager(gridLayoutManager);
        imageAdapter = new ImageAdapter(this, selectedImages, "see");
        recyclerViewHinhAnh.setAdapter(imageAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            String id = intent.getStringExtra("idContract");
            fillData(id);
        }
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