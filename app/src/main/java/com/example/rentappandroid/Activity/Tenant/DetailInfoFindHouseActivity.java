package com.example.rentappandroid.Activity.Tenant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rentappandroid.Activity.MessageActivity;
import com.example.rentappandroid.Adapter.TienNghiAdapter;
import com.example.rentappandroid.Model.FindRoomHouseResponse;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiPostFindHouse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailInfoFindHouseActivity extends AppCompatActivity {
    private String postId  = "";
    private String token = "";
    private String role = "";

    private void fillData1(){
        tieuDeBaiDangTextView.setText(findRoomHouseResponse.getTitle());
        giaTroTextView.setText("Giá trọ lớn nhất có thể trả: " +findRoomHouseResponse.getMaxPrice());
        diaChiTextView.setText("Địa chỉ mong muốn: " + findRoomHouseResponse.getAddress());
        ngayUpTextView.setText("Ngày up: " + findRoomHouseResponse.getDay_up());
        loaiNhaTextView.setText("Loại nhà mong muốn: " + findRoomHouseResponse.getTypehouse().getTypehouse_name());
        soNguoiTextView.setText("Số người ở: " + findRoomHouseResponse.getPeopeleNumber());
        sdtOwnerTextView.setText("SĐT: " + findRoomHouseResponse.getUser().getPhoneNumber());
        statusPostTextView.setText("Trạng thái bài đăng: " + findRoomHouseResponse.getStatus());
        moTaPhongTroTextView.setText(findRoomHouseResponse.getDescription());
        tenChuTroTextView.setText(findRoomHouseResponse.getUser().getName());
        soBaiDangTextView.setText(findRoomHouseResponse.getUser().getEmail());
        int spanCount = 3; // Number of columns
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        tienNghiRecyclerView.setLayoutManager(layoutManager);
        tienNghiAdapter = new TienNghiAdapter(findRoomHouseResponse.getAmenities(), this);
        tienNghiRecyclerView.setAdapter(tienNghiAdapter);
    }

    private TextView tieuDeBaiDangTextView;
    private TextView giaTroTextView;
    private TextView diaChiTextView;
    private TextView ngayUpTextView;
    private TextView loaiNhaTextView;
    private TextView soNguoiTextView;
    private TextView sdtOwnerTextView;
    private TextView statusPostTextView;
    private TextView moTaPhongTroTextView;
    private TextView tenChuTroTextView;
    private TextView soBaiDangTextView;

    private RecyclerView tienNghiRecyclerView;
    private TienNghiAdapter tienNghiAdapter;

    private Button chatButton;
    private Button goiButton;

    private void init(){
        tieuDeBaiDangTextView = findViewById(R.id.info_timtro_tieudebaidang);
        giaTroTextView = findViewById(R.id.info_timtro_giatro);
        diaChiTextView = findViewById(R.id.info_timtro_diachi_baidang_info);
        ngayUpTextView = findViewById(R.id.info_timtro_ngayup_baidang_info);
        loaiNhaTextView = findViewById(R.id.info_timtro_loainha_baidang_info);
        soNguoiTextView = findViewById(R.id.info_timtro_songuoi_baidang_info);
        sdtOwnerTextView = findViewById(R.id.info_timtro_sdtOwner_infobaidang);
        statusPostTextView = findViewById(R.id.info_timtro_statusPost_infobaidang);
        moTaPhongTroTextView = findViewById(R.id.info_timtro_motaphongtro_baidanginfo);
        tenChuTroTextView = findViewById(R.id.info_timtro_tenchutro);
        soBaiDangTextView = findViewById(R.id.info_timtro_sobaidang_baidanginfo);

        tienNghiRecyclerView = findViewById(R.id.info_timtro_tiennghi_baidanginfo_recycleview);

        chatButton = findViewById(R.id.info_timtro_chat_btn_bottom);
        goiButton = findViewById(R.id.info_timtro_goi_btn_bottom);

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailInfoFindHouseActivity.this, MessageActivity.class);
                intent.putExtra("owner", findRoomHouseResponse.getUser().get_id());
                intent.putExtra("tenant", phoneOwner);
                startActivity(intent);
            }
        });

        goiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = findRoomHouseResponse.getUser().getPhoneNumber();
                if (ContextCompat.checkSelfPermission(DetailInfoFindHouseActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(DetailInfoFindHouseActivity.this,new String[]{Manifest.permission.CALL_PHONE},100);
                }
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+phone));
                startActivity(i);
            }
        });
    }

    String phoneOwner;
    private FindRoomHouseResponse findRoomHouseResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info_find_house);
        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);

        phoneOwner = preferences.getString("sdt", "");
        findRoomHouseResponse = new FindRoomHouseResponse();
        init();
        Intent intent = getIntent();
        if (intent != null) {
            postId = intent.getStringExtra("ID_POST");
            token = intent.getStringExtra("token");
            String role = intent.getStringExtra("role");
        }

        fillData();
    }

    private void fillData() {
        ApiPostFindHouse.apiApiPostFindHouse.getFindRoomHousesByID(postId,token).enqueue(new Callback<FindRoomHouseResponse>() {
            @Override
            public void onResponse(Call<FindRoomHouseResponse> call, Response<FindRoomHouseResponse> response) {
                findRoomHouseResponse = response.body();
                fillData1();
            }

            @Override
            public void onFailure(Call<FindRoomHouseResponse> call, Throwable t) {

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