package com.example.rentappandroid.Activity.Tenant.HopDong;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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
import android.widget.Button;
import android.widget.Toast;

import com.example.rentappandroid.Adapter.Landlord.LeaseContractAdapter;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.Model.TimNguoiOGhep;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiHopDong;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListHopDongActivity extends AppCompatActivity {

    private Button hopdongConHieuLucButton;
    private Button hopdongHetHieuLucButton;
    String id ;
    String phone ;
    String token ;
    String phoneOwner;
    String type;
    String receivedData = "";
    private RecyclerView recyclerView;
    private LeaseContractAdapter leaseContractAdapter;
    private List<Leasecontracts> leasecontracts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hop_dong);

        hopdongConHieuLucButton = findViewById(R.id.hopdong_conhieuluc);
        hopdongHetHieuLucButton = findViewById(R.id.hopdong_hethieuluc);
        recyclerView = findViewById(R.id.tenant_hopdong_recycleview);
        leasecontracts = new ArrayList<>();

        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);

        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        String role = preferences.getString("role", "");

        // Now you can use these variables as needed, for example, set click listeners
        hopdongConHieuLucButton.setOnClickListener(v -> {
            List<Leasecontracts> temp = new ArrayList<>();
            for(Leasecontracts t : leasecontracts) {
                if(t.isStatus()){
                    temp.add(t);
                }
            }

            leaseContractAdapter.setRoomList(temp);
            leaseContractAdapter.notifyDataSetChanged();
        });

        hopdongHetHieuLucButton.setOnClickListener(v -> {
            List<Leasecontracts> temp = new ArrayList<>();
            for(Leasecontracts t : leasecontracts) {
                if(!t.isStatus()){
                    temp.add(t);
                }
            }

            leaseContractAdapter.setRoomList(temp);
            leaseContractAdapter.notifyDataSetChanged();
        });
        receivedData = "tenantsee";
        Intent intent = getIntent();
        if (intent != null) {
            receivedData = intent.getStringExtra("type");
        }

        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);
        leaseContractAdapter = new LeaseContractAdapter( this ,leasecontracts,role, token, receivedData);
        recyclerView.setAdapter(leaseContractAdapter);

        getcData();


    }

    private void getcData() {
        ApiHopDong.apiHopDong.getallleasecontractBytenant(phoneOwner, token).enqueue(new Callback<List<Leasecontracts>>() {
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