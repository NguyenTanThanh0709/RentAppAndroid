package com.example.rentappandroid.Activity.Tenant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiBaiDang;

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
    private RecyclerView quyTacBaiDangInfoRecyclerView;

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
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info_room);
        init();
        baiViet = new BaiViet();
        event();
        Intent intent = getIntent();
        if (intent != null) {
            String postId = intent.getStringExtra("ID_POST");
            String token = intent.getStringExtra("token");
            fillData(postId, token);
        }

//        List<String> imageUrls = Arrays.asList("url1", "url2", "url3"); // Replace with your actual image URLs
//        RecyclerView recyclerView = findViewById(R.id.listimagephongtro_recycle);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        ImageAdapter imageAdapter = new ImageAdapter(this, imageUrls);
//        recyclerView.setAdapter(imageAdapter);
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