package com.example.rentappandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rentappandroid.Adapter.BaiVietAdapter;
import com.example.rentappandroid.Adapter.FindRoomHouseAdapter;
import com.example.rentappandroid.Adapter.TimNguoiOGhepAdapter;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.Model.FindRoomHouseResponse;
import com.example.rentappandroid.Model.TimNguoiOGhep;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiBaiDang;
import com.example.rentappandroid.api.ApiPostFindHouse;
import com.example.rentappandroid.api.ApiTimNguoiOGhep;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<BaiViet> baiVietList;
    private BaiVietAdapter baiVietAdapter;

    private List<FindRoomHouseResponse> findRoomHouseResponseList;
    private FindRoomHouseAdapter findRoomHouseAdapter;

    private List<TimNguoiOGhep> timNguoiOGhepList;
    private TimNguoiOGhepAdapter timNguoiOGhepAdapter;

    private void getData1(){
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManagerBaiViet = new GridLayoutManager(ListInfoActivity.this, spanCount);
        recyclerView.setLayoutManager(layoutManagerBaiViet);
        baiVietAdapter = new BaiVietAdapter(  baiVietList,this,role, token, "", phoneOwner, "");
        recyclerView.setAdapter(baiVietAdapter);
        ApiBaiDang.apiBaiDang.getallBaiDang(token).enqueue(new Callback<List<BaiViet>>() {
            @Override
            public void onResponse(Call<List<BaiViet>> call, Response<List<BaiViet>> response) {
                if (response.isSuccessful()) {
                    // Xử lý khi response thành công
                    for(BaiViet baiViet : response.body()){
                        if(baiViet.getRoom().getAddress().getCity().equals(tinh)){
                            baiVietList.add(baiViet);
                        }
                    }
                    baiVietAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<BaiViet>> call, Throwable t) {
                // Xử lý khi request thất bại (ví dụ: không có kết nối internet)
                Log.e("API Request", "Failure: " + t.getMessage(), t);
            }
        });
    }
    private void getData2(){
        GridLayoutManager layoutManagerBaiVietTIMTRO = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManagerBaiVietTIMTRO);
        findRoomHouseAdapter = new FindRoomHouseAdapter(this,findRoomHouseResponseList,token, role, "");
        recyclerView.setAdapter(findRoomHouseAdapter);
        ApiPostFindHouse.apiApiPostFindHouse.getAllFindRoomHouses(token).enqueue(new Callback<List<FindRoomHouseResponse>>() {
            @Override
            public void onResponse(Call<List<FindRoomHouseResponse>> call, Response<List<FindRoomHouseResponse>> response) {
                for (FindRoomHouseResponse findRoomHouseResponse : response.body()){
                    if(findRoomHouseResponse.getAddress().contains(tinh)){
                        findRoomHouseResponseList.add(findRoomHouseResponse);
                    }
                }
                findRoomHouseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FindRoomHouseResponse>> call, Throwable t) {

            }
        });
    }

    private void getData3(){
        GridLayoutManager layoutManagerBaiVietTIMnguoighep = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManagerBaiVietTIMnguoighep);
// Use timNguoiOGhepAdapter instead of findRoomHouseAdapter
        timNguoiOGhepAdapter = new TimNguoiOGhepAdapter(this, timNguoiOGhepList, token, role, "");
        recyclerView.setAdapter(timNguoiOGhepAdapter);
        ApiTimNguoiOGhep.apiApiTimNguoiOGhep.getAllTimNguoiOGheps(token).enqueue(new Callback<List<TimNguoiOGhep>>() {
            @Override
            public void onResponse(Call<List<TimNguoiOGhep>> call, Response<List<TimNguoiOGhep>> response) {
                for (TimNguoiOGhep findRoomHouseResponse : response.body()){
                    if(findRoomHouseResponse.getAddress().getCity().equals(tinh)){
                        timNguoiOGhepList.add(findRoomHouseResponse);
                    }
                }
                timNguoiOGhepAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TimNguoiOGhep>> call, Throwable t) {

            }
        });
    }

    private void getData4(String quan){
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManagerBaiViet = new GridLayoutManager(ListInfoActivity.this, spanCount);
        recyclerView.setLayoutManager(layoutManagerBaiViet);
        baiVietAdapter = new BaiVietAdapter(  baiVietList,this,role, token, "", phoneOwner, "");
        recyclerView.setAdapter(baiVietAdapter);
        ApiBaiDang.apiBaiDang.getallBaiDang(token).enqueue(new Callback<List<BaiViet>>() {
            @Override
            public void onResponse(Call<List<BaiViet>> call, Response<List<BaiViet>> response) {
                if (response.isSuccessful()) {
                    for(BaiViet baiViet : response.body()){
                        if(baiViet.getRoom().getAddress().getDistrict().equals(quan)){
                            baiVietList.add(baiViet);
                        }
                    }
                    baiVietAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<BaiViet>> call, Throwable t) {
                // Xử lý khi request thất bại (ví dụ: không có kết nối internet)
                Log.e("API Request", "Failure: " + t.getMessage(), t);
            }
        });
    }

    private String token = "";
    private String address = "";
    private String type = "";
    private String tinh = "";
    private String quan = "";
    private String role;
    private String phoneOwner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_info);

        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        role = preferences.getString("role", "");
        phoneOwner = preferences.getString("sdt", "");
        address = preferences.getString("address", "");
        String[] split = address.split("-");
        tinh = split[0];
        quan = split[1];

        recyclerView = findViewById(R.id.List_info_recycleview);
        baiVietList = new ArrayList<>();
        findRoomHouseResponseList = new ArrayList<>();
        timNguoiOGhepList = new ArrayList<>();


        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getStringExtra("type");
            type = (type != null) ? type : "";
            if(type.equals("baiviet")){
                getData1();
            }else if(type.equals("timtro")){
                getData2();
            }else if(type.equals("oghep")){
                getData3();
            }else if(type.equals("address")){
                String value = intent.getStringExtra("quan");
                getData4(value);
            }



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