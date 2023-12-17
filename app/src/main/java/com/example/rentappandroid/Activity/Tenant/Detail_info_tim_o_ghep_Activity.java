package com.example.rentappandroid.Activity.Tenant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentappandroid.Adapter.Image_info_Adapter;
import com.example.rentappandroid.Adapter.ServiceChargeAdapter;
import com.example.rentappandroid.Adapter.TienNghiAdapter;
import com.example.rentappandroid.Dto.Reponse.ServiceCharge;
import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
import com.example.rentappandroid.Model.TimNguoiOGhep;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiTimNguoiOGhep;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_info_tim_o_ghep_Activity extends AppCompatActivity {
    private ImageView imagePhongTro;
    private RecyclerView listImagePhongTroRecycle;
    private TextView loaiPhongTro;
    private TextView tieuDeBaiDang;
    private TextView giaTro;
    private ImageView heart;
    private TextView diaChiBaiDangInfo;
    private TextView sdtOwnerInfoBaiDang;
    private TextView statusPostInfoBaiDang;
    private TextView dienTichPhongInfo;
    private TextView soNguoiInfo;
    private RecyclerView phiDichVuRecycleView;
    private TextView moTaPhongTroInfo;
    private TextView tenChuTro;
    private TextView soBaiDangInfo;
    private RecyclerView tienNghiRecycleView;
    private Button chatBtnBottom;
    private Button goiBtnBottom;

    private TienNghiAdapter tienNghiAdapter;
    private ServiceChargeAdapter serviceChargeAdapter;

    private void anhxa() {
        Picasso.with(this)
                .load(timNguoiOGhep.getImage_url().get(0))
                .placeholder(R.drawable.phongtro)
                .error(R.drawable.phongtro)
                .into(imagePhongTro);

        listImagePhongTroRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Image_info_Adapter imageAdapter = new Image_info_Adapter(this, timNguoiOGhep.getImage_url());
        listImagePhongTroRecycle.setAdapter(imageAdapter);
        loaiPhongTro.setText("Loại trọ");
        tieuDeBaiDang.setText(timNguoiOGhep.getTitle());
        giaTro.setText(timNguoiOGhep.getPrice() + "triệu/tháng");
        diaChiBaiDangInfo.setText(timNguoiOGhep.getAddress().getFullAddress());
        sdtOwnerInfoBaiDang.setText(timNguoiOGhep.getUser().getPhoneNumber());
        statusPostInfoBaiDang.setText(timNguoiOGhep.getStatus());
        diaChiBaiDangInfo.setText(timNguoiOGhep.getSquare_feet() + "");
        soNguoiInfo.setText(timNguoiOGhep.getPeopeleNumber() + "");
        moTaPhongTroInfo.setText(timNguoiOGhep.getDescription());
        tenChuTro.setText(timNguoiOGhep.getUser().getName());
        soNguoiInfo.setText(timNguoiOGhep.getPeopeleNumber() + "");
        soBaiDangInfo.setText(timNguoiOGhep.getUser().getEmail());

        int spanCount = 3; // Number of columns
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        tienNghiRecycleView.setLayoutManager(layoutManager);
        tienNghiAdapter = new TienNghiAdapter(timNguoiOGhep.getAmenities(), this);
        tienNghiRecycleView.setAdapter(tienNghiAdapter);


        List<ServireChareReponse> list1 = new ArrayList<>();
        for (ServiceCharge in : timNguoiOGhep.getServiceCharge()){
            ServireChareReponse servireChareReponse = in.getServiceChargeId();
            servireChareReponse.setPhi(in.getPrice() + " vnd" );
            list1.add(servireChareReponse);

        }
        GridLayoutManager gridLayoutManager____ = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL,false);
        phiDichVuRecycleView.setLayoutManager(gridLayoutManager____);
        serviceChargeAdapter = new ServiceChargeAdapter(this, list1);
        phiDichVuRecycleView.setAdapter(serviceChargeAdapter);
    }

    private void init(){
        imagePhongTro = findViewById(R.id.tenant_timnguoi_oGhep_imagephongtro);
        listImagePhongTroRecycle = findViewById(R.id.tenant_timnguoi_oGhep_listimagephongtro_recycle);
        loaiPhongTro = findViewById(R.id.tenant_timnguoi_oGhep_loaiphongtro);
        tieuDeBaiDang = findViewById(R.id.tenant_timnguoi_oGhep_tieudebaidang);
        giaTro = findViewById(R.id.tenant_timnguoi_oGhep_giatro);
        heart = findViewById(R.id.tenant_timnguoi_oGhep_heart);
        diaChiBaiDangInfo = findViewById(R.id.tenant_timnguoi_oGhep_diachi_baidang_info);
        sdtOwnerInfoBaiDang = findViewById(R.id.tenant_timnguoi_oGhep_sdtOwner_infobaidang);
        statusPostInfoBaiDang = findViewById(R.id.tenant_timnguoi_oGhep_statusPost_infobaidang);
        dienTichPhongInfo = findViewById(R.id.tenant_timnguoi_oGhep_dientichphong_baidanginfo);
        soNguoiInfo = findViewById(R.id.tenant_timnguoi_oGhep_songuoi_baidanginfo);
        phiDichVuRecycleView = findViewById(R.id.tenant_timnguoi_oGhep_phidich_baidanginfo_recycleview);
        moTaPhongTroInfo = findViewById(R.id.tenant_timnguoi_oGhep_motaphongtro_baidanginfo);
        tenChuTro = findViewById(R.id.tenant_timnguoi_oGhep_tenchutro);
        soBaiDangInfo = findViewById(R.id.tenant_timnguoi_oGhep_sobaidang_baidanginfo);
        tienNghiRecycleView = findViewById(R.id.tenant_timnguoi_oGhep_tiennghi_baidanginfo_recycleview);
        chatBtnBottom = findViewById(R.id.tenant_timnguoi_oGhep_chat_btn_bottom);
        goiBtnBottom = findViewById(R.id.tenant_timnguoi_oGhep_goi_btn_bottom);
    }


    private TimNguoiOGhep timNguoiOGhep;

    private String postId  = "";
    private String token = "";
    private String role = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info_tim_oghep);
        init();
        timNguoiOGhep = new TimNguoiOGhep();
        Intent intent = getIntent();
        if (intent != null) {
            postId = intent.getStringExtra("ID_POST");
            token = intent.getStringExtra("token");
            String role = intent.getStringExtra("role");
            fillData(postId, token);
        }
    }

    private void fillData(String postId, String token) {
        ApiTimNguoiOGhep.apiApiTimNguoiOGhep.getTimNguoiOGhepByID(postId,token).enqueue(new Callback<TimNguoiOGhep>() {
            @Override
            public void onResponse(Call<TimNguoiOGhep> call, Response<TimNguoiOGhep> response) {
                timNguoiOGhep = response.body();
                anhxa();
            }



            @Override
            public void onFailure(Call<TimNguoiOGhep> call, Throwable t) {

            }
        });
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