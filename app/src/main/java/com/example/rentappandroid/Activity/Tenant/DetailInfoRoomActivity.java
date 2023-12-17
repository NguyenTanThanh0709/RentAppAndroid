package com.example.rentappandroid.Activity.Tenant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentappandroid.Adapter.AreaInformationAdapter;
import com.example.rentappandroid.Adapter.BaiVietAdapter;
import com.example.rentappandroid.Adapter.ImageAdapter;
import com.example.rentappandroid.Adapter.Image_info_Adapter;
import com.example.rentappandroid.Adapter.ServiceChargeAdapter;
import com.example.rentappandroid.Adapter.TienNghiAdapter;
import com.example.rentappandroid.Dto.Reponse.AreaInformation;
import com.example.rentappandroid.Dto.Reponse.AreaInformationReponse;
import com.example.rentappandroid.Dto.Reponse.ServiceCharge;
import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiBaiDang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailInfoRoomActivity extends AppCompatActivity {
    private ImageView imagePhongTro;
    private RecyclerView listImagePhongTroRecycle;
    private TextView loaiPhongTro;
    private TextView tieuDeBaiDang;
    private TextView moTaBaiDang;
    private TextView giaTro;

    private TextView diaChiBaiDangInfo;
    private TextView sdtOwnerInfoBaiDang;
    private TextView statusPostInfoBaiDang;
    private TextView statusRoomInfoBaiDang;
    private TextView tieuDePhongTroInfoBaiDang;
    private TextView dienTichPhongBaiDangInfo;
    private TextView datCocBaiDangInfo;
    private TextView soNguoiBaiDangInfo;

    private RecyclerView phiDichBaiDangInfoRecyclerView;
    private TextView moTaPhongTroBaiDangInfo;
    private TextView quyTacBaiDangInfoRecyclerView;

    private TextView tenChuTro;
    private TextView soBaiDangChuTro;

    private RecyclerView tienNghiBaiDangInfoRecyclerView;
    private RecyclerView khuVucXungQuanhBaiDangInfoRecyclerView;
    private RecyclerView listBaiDangBaiDangInfoRecyclerView;

    private Button chatBtnBottom;
    private Button giuChoBtnBottom;
    private Button goiBtnBottom;
    private Button baoCaoBtnBottom;

    private BaiViet baiViet;
    private String postId  = "";
    private String token = "";
    private String role = "";

    private TienNghiAdapter tienNghiAdapter;
    private ServiceChargeAdapter serviceChargeAdapter;
    private AreaInformationAdapter areaInformationAdapter;
    private List<BaiViet> baiVietList;
    private BaiVietAdapter baiVietAdapter;

    private void getListByVIetBuyOwner(){
        ApiBaiDang.apiBaiDang.getallBaiDangByOwner(baiViet.getRoom().getOwner().get_id(), token).enqueue(new Callback<List<BaiViet>>() {
            @Override
            public void onResponse(Call<List<BaiViet>> call, Response<List<BaiViet>> response) {
                baiVietList.addAll(response.body());
                baiVietAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<BaiViet>> call, Throwable t) {

            }
        });
    }
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info_room);
        init();
        baiViet = new BaiViet();
        baiVietList = new ArrayList<>();
        event();
        Intent intent = getIntent();
        if (intent != null) {
             postId = intent.getStringExtra("ID_POST");
             token = intent.getStringExtra("token");
            String role = intent.getStringExtra("role");
            fillData(postId, token);
        }


        listBaiDangBaiDangInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        baiVietAdapter = new BaiVietAdapter(baiVietList,this,role,token);
        listBaiDangBaiDangInfoRecyclerView.setAdapter(baiVietAdapter);
    }

    private void fillData(String postId, String token) {
        ApiBaiDang.apiBaiDang.getallBaiDangByid(postId,token).enqueue(new Callback<BaiViet>() {
            @Override
            public void onResponse(Call<BaiViet> call, Response<BaiViet> response) {
                baiViet = response.body();
                fillAll();
            }

            @Override
            public void onFailure(Call<BaiViet> call, Throwable t) {

            }
        });
    }

    private void fillAll() {
        Picasso.with(this)
                .load(baiViet.getRoom().getImage_url().get(0))
                .placeholder(R.drawable.phongtro)
                .error(R.drawable.phongtro)
                .into(imagePhongTro);

        listImagePhongTroRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Image_info_Adapter imageAdapter = new Image_info_Adapter(this, baiViet.getRoom().getImage_url());
        listImagePhongTroRecycle.setAdapter(imageAdapter);

        loaiPhongTro.setText("Loại phòng: " + baiViet.getRoom().getTypehouse().getTypehouse_name());
        moTaBaiDang.setText("mô tả bài đăng: " + baiViet.getDescription());
        giaTro.setText("Giá trọ: " + baiViet.getRoom().getPrice() + " triệu/tháng");
        diaChiBaiDangInfo.setText("Địa chỉ: " + baiViet.getRoom().getAddress().getFullAddress());
        sdtOwnerInfoBaiDang.setText("Số điện thoại chủ trọ: " + baiViet.getRoom().getOwner().getPhoneNumber());
        statusPostInfoBaiDang.setText("Trạng thái bài đăng: " + baiViet.getStatus());
        statusRoomInfoBaiDang.setText("Trạng thái phòng trọ: " + baiViet.getRoom().getStatus());
        tieuDePhongTroInfoBaiDang.setText("Tiêu đề phòng trọ: "  +baiViet.getRoom().getTitle());
        dienTichPhongBaiDangInfo.setText(baiViet.getRoom().getSquare_feet() + "");
        datCocBaiDangInfo.setText(baiViet.getDeposit() + "");
        soNguoiBaiDangInfo.setText(baiViet.getRoom().getPeopeleNumber() + "");

        moTaPhongTroBaiDangInfo.setText(baiViet.getRoom().getDescription());
        tenChuTro.setText(baiViet.getRoom().getOwner().getName());

        String rules = "";
        for (String in : baiViet.getRoom().getRules()){
            rules += in + "\n";
        }
        quyTacBaiDangInfoRecyclerView.setText(rules);
        soBaiDangChuTro.setText(baiViet.getRoom().getOwner().getEmail());

        int spanCount = 3; // Number of columns
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        tienNghiBaiDangInfoRecyclerView.setLayoutManager(layoutManager);
        tienNghiAdapter = new TienNghiAdapter(baiViet.getRoom().getAmenities(), this);
        tienNghiBaiDangInfoRecyclerView.setAdapter(tienNghiAdapter);

        GridLayoutManager gridLayoutManager___ = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL,false);
        phiDichBaiDangInfoRecyclerView.setLayoutManager(gridLayoutManager___);


        List<ServireChareReponse> list1 = new ArrayList<>();
        for (ServiceCharge in : baiViet.getRoom().getServiceCharge()){
            ServireChareReponse servireChareReponse = in.getServiceChargeId();
            servireChareReponse.setPhi(in.getPrice() + " vnd" );
            list1.add(servireChareReponse);

        }
        GridLayoutManager gridLayoutManager____ = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL,false);
        phiDichBaiDangInfoRecyclerView.setLayoutManager(gridLayoutManager____);
        serviceChargeAdapter = new ServiceChargeAdapter(this, list1);
        phiDichBaiDangInfoRecyclerView.setAdapter(serviceChargeAdapter);

        List<AreaInformationReponse> list2 = new ArrayList<>();
        for (AreaInformation in : baiViet.getRoom().getAreaInformation()){
            AreaInformationReponse areaInformationReponse = in.getAreaInformationID();
            areaInformationReponse.setDescription(in.getDescription());
            areaInformationReponse.setDistance(in.getDistance());

            list2.add(areaInformationReponse);

        }
        GridLayoutManager gridLayoutManager_____ = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL,false);
        khuVucXungQuanhBaiDangInfoRecyclerView.setLayoutManager(gridLayoutManager_____);
        areaInformationAdapter = new AreaInformationAdapter(list2,this, "INFO");
        khuVucXungQuanhBaiDangInfoRecyclerView.setAdapter(areaInformationAdapter);
        getListByVIetBuyOwner();

    }

    private void event() {
        chatBtnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event for the chat button
            }
        });

        giuChoBtnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event for the giu cho button
            }
        });

        goiBtnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event for the goi button
            }
        });

        baoCaoBtnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event for the bao cao button
            }
        });
    }

    private  void init(){
        imagePhongTro = findViewById(R.id.imagephongtro);
        listImagePhongTroRecycle = findViewById(R.id.listimagephongtro_recycle);
        loaiPhongTro = findViewById(R.id.loaiphongtro);
        tieuDeBaiDang = findViewById(R.id.tieudebaidang);
        moTaBaiDang = findViewById(R.id.motabaidang_baidanginfo);
        giaTro = findViewById(R.id.giatro);

        diaChiBaiDangInfo = findViewById(R.id.diachi_baidang_info);
        sdtOwnerInfoBaiDang = findViewById(R.id.sdtOwner_infobaidang);
        statusPostInfoBaiDang = findViewById(R.id.statusPost_infobaidang);
        statusRoomInfoBaiDang = findViewById(R.id.statusRoom_infobaidang);
        tieuDePhongTroInfoBaiDang = findViewById(R.id.tieudephongtro_infobaidang);
        dienTichPhongBaiDangInfo = findViewById(R.id.dientichphong_baidanginfo);
        datCocBaiDangInfo = findViewById(R.id.datcoc_baidanginfo);
        soNguoiBaiDangInfo = findViewById(R.id.songuoi_baidanginfo);

        phiDichBaiDangInfoRecyclerView = findViewById(R.id.phidich_baidanginfo_recycleview);
        moTaPhongTroBaiDangInfo = findViewById(R.id.motaphongtro_baidanginfo);
        quyTacBaiDangInfoRecyclerView = findViewById(R.id.quytac_baidanginfo_recycleview);

        tenChuTro = findViewById(R.id.tenchutro);
        soBaiDangChuTro = findViewById(R.id.sobaidang_baidanginfo);

        tienNghiBaiDangInfoRecyclerView = findViewById(R.id.tiennghi_baidanginfo_recycleview);
        khuVucXungQuanhBaiDangInfoRecyclerView = findViewById(R.id.khuvucxungquanh_baidanginfo_recycleview);
        listBaiDangBaiDangInfoRecyclerView = findViewById(R.id.listbaidang_baidanginfo_recycleview);

        chatBtnBottom = findViewById(R.id.chat_btn_bottom);
        giuChoBtnBottom = findViewById(R.id.giucho_btn_bottom);
        goiBtnBottom = findViewById(R.id.goi_btn_bottom);
        baoCaoBtnBottom = findViewById(R.id.baocao_btn_bottom);
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