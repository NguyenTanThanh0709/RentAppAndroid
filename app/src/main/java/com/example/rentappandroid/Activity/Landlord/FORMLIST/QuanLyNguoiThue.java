package com.example.rentappandroid.Activity.Landlord.FORMLIST;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rentappandroid.Adapter.Landlord.LeaseContractAdapter;
import com.example.rentappandroid.Adapter.Landlord.NguoiThueAdapter;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiHopDong;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLyNguoiThue extends AppCompatActivity {
    String phone ;
    String token ;
    String phoneOwner;
    private RecyclerView recyclerView;
    private NguoiThueAdapter leaseContractAdapter;
    private List<Leasecontracts> leasecontracts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nguoi_thue);
        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);
        leasecontracts =new ArrayList<>();

        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        recyclerView = findViewById(R.id.listNguoiThue_recycleview);


        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);
        leaseContractAdapter = new NguoiThueAdapter( this ,leasecontracts);
        recyclerView.setAdapter(leaseContractAdapter);


        ApiHopDong.apiHopDong.getallleasecontractByOwner(phoneOwner, token).enqueue(new Callback<List<Leasecontracts>>() {
            @Override
            public void onResponse(Call<List<Leasecontracts>> call, Response<List<Leasecontracts>> response) {
                if (response.isSuccessful()) {
                    // API call successful, add data to the list
                    leasecontracts.clear();
                    leasecontracts.addAll(response.body());
                    leaseContractAdapter.notifyDataSetChanged();
                } else {
                    // Log error if the API call is not successful
                    Log.e(TAG, "API call failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Leasecontracts>> call, Throwable t) {
                // Log error if the API call fails
                Log.e(TAG, "API call failed", t);
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