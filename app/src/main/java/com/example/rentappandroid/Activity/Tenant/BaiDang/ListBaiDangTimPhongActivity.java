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
import com.example.rentappandroid.Adapter.Landlord.RoomAdapter;
import com.example.rentappandroid.Model.FindRoomHouseResponse;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiPostFindHouse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBaiDangTimPhongActivity extends AppCompatActivity {

    private Button baidangTimTroDangHoatDongButton;
    private Button baidangTimTroKhongHoatDongButton;
    private RecyclerView baidangTimPhongTroRecyclerView;
    private FloatingActionButton menuAddBaidangTimPhongTroButton;

    private List<FindRoomHouseResponse> findRoomHouseResponseList;
    private FindRoomHouseAdapter findRoomHouseAdapter;

    private void init(){
        baidangTimTroDangHoatDongButton = findViewById(R.id.baidangtimtrodanghoatdong);
        baidangTimTroKhongHoatDongButton = findViewById(R.id.baidangtimtrokhonghoatdong);
        baidangTimPhongTroRecyclerView = findViewById(R.id.tenant_baidangtimphongtro_add_recycleview);
        menuAddBaidangTimPhongTroButton = findViewById(R.id.menu_add_baidangtimphongtro);
    }

    private void event(){
        baidangTimTroDangHoatDongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 List<FindRoomHouseResponse> temp = new ArrayList<>();
                 for(FindRoomHouseResponse t : findRoomHouseResponseList) {
                     if(t.getStatus().equals("Đang hoạt động")){
                         temp.add(t);
                     }
                 }
                findRoomHouseAdapter.setRoomList(temp);
                findRoomHouseAdapter.notifyDataSetChanged();
            }
        });

        baidangTimTroKhongHoatDongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<FindRoomHouseResponse> temp = new ArrayList<>();
                for(FindRoomHouseResponse t : findRoomHouseResponseList) {
                    if(t.getStatus().equals("Bị ẩn")){
                        temp.add(t);
                    }
                }

                findRoomHouseAdapter.setRoomList(temp);
                findRoomHouseAdapter.notifyDataSetChanged();
            }
        });

        menuAddBaidangTimPhongTroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListBaiDangTimPhongActivity.this, FormTimPhongActivity.class));
            }
        });
    }

    private void getData(){
        ApiPostFindHouse.apiApiPostFindHouse.getFindRoomHousesByUser(phoneOwner,token).enqueue(new Callback<List<FindRoomHouseResponse>>() {
            @Override
            public void onResponse(Call<List<FindRoomHouseResponse>> call, Response<List<FindRoomHouseResponse>> response) {
                findRoomHouseResponseList.addAll(response.body());
                findRoomHouseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FindRoomHouseResponse>> call, Throwable t) {

            }
        });
    }

    private String token;
    private String phoneOwner;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_dang_tim_phong);
        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        role = preferences.getString("role", "");  // Replace "" with the default value if not found

        init();
        event();
        findRoomHouseResponseList = new ArrayList<>();


        GridLayoutManager layoutManagerBaiVietTIMTRO = new GridLayoutManager(this, 1);
        baidangTimPhongTroRecyclerView.setLayoutManager(layoutManagerBaiVietTIMTRO);
        findRoomHouseAdapter = new FindRoomHouseAdapter(this,findRoomHouseResponseList,token, role, "Edit");
        baidangTimPhongTroRecyclerView.setAdapter(findRoomHouseAdapter);
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