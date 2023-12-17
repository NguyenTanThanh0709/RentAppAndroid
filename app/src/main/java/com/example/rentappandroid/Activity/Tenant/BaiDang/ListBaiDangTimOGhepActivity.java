package com.example.rentappandroid.Activity.Tenant.BaiDang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rentappandroid.Adapter.FindRoomHouseAdapter;
import com.example.rentappandroid.Adapter.TimNguoiOGhepAdapter;
import com.example.rentappandroid.Model.FindRoomHouseResponse;
import com.example.rentappandroid.Model.TimNguoiOGhep;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiPostFindHouse;
import com.example.rentappandroid.api.ApiTimNguoiOGhep;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBaiDangTimOGhepActivity extends AppCompatActivity {

    private Button baidangTimOGhepDangHoatDongButton;
    private Button baidangTimOGhepKhongHoatDongButton;
    private RecyclerView baidangTimOGhepRecyclerView;
    private FloatingActionButton menuAddBaidangTimOGhepButton;

    private String token;
    private String phoneOwner;
    private String role;

    private List<TimNguoiOGhep> timNguoiOGhepList;
    private TimNguoiOGhepAdapter timNguoiOGhepAdapter;

    private void getData(){
        ApiTimNguoiOGhep.apiApiTimNguoiOGhep.getTimNguoiOGhepsByUser(phoneOwner,token).enqueue(new Callback<List<TimNguoiOGhep>>() {
            @Override
            public void onResponse(Call<List<TimNguoiOGhep>> call, Response<List<TimNguoiOGhep>> response) {
                timNguoiOGhepList.addAll(response.body());
                timNguoiOGhepAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TimNguoiOGhep>> call, Throwable t) {

            }
        });
    }

    private void event(){
        baidangTimOGhepDangHoatDongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<TimNguoiOGhep> temp = new ArrayList<>();
                for(TimNguoiOGhep t : timNguoiOGhepList) {
                    if(t.getStatus().equals("Đang hoạt động")){
                        temp.add(t);
                    }
                }

                timNguoiOGhepAdapter.setRoomList(temp);
                timNguoiOGhepAdapter.notifyDataSetChanged();
            }
        });
        baidangTimOGhepKhongHoatDongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<TimNguoiOGhep> temp = new ArrayList<>();
                for(TimNguoiOGhep t : timNguoiOGhepList) {
                    if(t.getStatus().equals("Bị ẩn")){
                        temp.add(t);
                    }
                }

                timNguoiOGhepAdapter.setRoomList(temp);
                timNguoiOGhepAdapter.notifyDataSetChanged();
            }
        });
        menuAddBaidangTimOGhepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListBaiDangTimOGhepActivity.this, FormTimNguoiOGhepActivity.class));
            }
        });

    }

    private void inti(){
        baidangTimOGhepDangHoatDongButton = findViewById(R.id.baidangtimoghepdanghoatdong);
        baidangTimOGhepKhongHoatDongButton = findViewById(R.id.baidangtimoghepkhonghoatdong);
        baidangTimOGhepRecyclerView = findViewById(R.id.tenant_baidangtimphongoghep_add_recycleview);
        menuAddBaidangTimOGhepButton = findViewById(R.id.menu_add_baidangtimphongoghep);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_dang_tim_oghep);
        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        role = preferences.getString("role", "");
        timNguoiOGhepList = new ArrayList<>();
        inti();
        event();


        GridLayoutManager layoutManagerBaiVietTIMTRO = new GridLayoutManager(this, 1);
        baidangTimOGhepRecyclerView.setLayoutManager(layoutManagerBaiVietTIMTRO);
        timNguoiOGhepAdapter = new TimNguoiOGhepAdapter(    this,timNguoiOGhepList,token, role, "Edit");
        baidangTimOGhepRecyclerView.setAdapter(timNguoiOGhepAdapter);

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