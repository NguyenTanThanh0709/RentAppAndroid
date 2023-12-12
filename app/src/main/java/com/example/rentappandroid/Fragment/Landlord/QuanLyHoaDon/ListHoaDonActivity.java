package com.example.rentappandroid.Fragment.Landlord.QuanLyHoaDon;

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

import com.example.rentappandroid.Adapter.HoaDonAdapter;
import com.example.rentappandroid.Adapter.Landlord.LeaseContractAdapter;
import com.example.rentappandroid.Model.HoaDon;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiHoadon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListHoaDonActivity extends AppCompatActivity {


    private List<HoaDon> listhoadon;
    private HoaDonAdapter hoaDonAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);
        listhoadon   = new ArrayList<>();
        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");
        String role = preferences.getString("role", "");
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.listhoadon_recycleview);
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        hoaDonAdapter = new HoaDonAdapter( listhoadon, this,role,token);
        recyclerView.setAdapter(hoaDonAdapter);


        if (intent.hasExtra("idContract")) {

            String id = intent.getStringExtra("idContract");
            Log.d("ok","OK");
            ApiHoadon.apiHoadon.getAllHoaDonByContract(id,token).enqueue(new Callback<List<HoaDon>>() {
                @Override
                public void onResponse(Call<List<HoaDon>> call, Response<List<HoaDon>> response) {
                    listhoadon.addAll(response.body());
                    hoaDonAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<HoaDon>> call, Throwable t) {

                }
            });
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