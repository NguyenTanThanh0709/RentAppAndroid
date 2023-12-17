package com.example.rentappandroid.Activity.Tenant.BaiYeuThich;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rentappandroid.Adapter.BaiVietAdapter;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.Model.FavouritesRoom;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiFavouriteRoom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBaiYeuThichActivity extends AppCompatActivity {

    private RecyclerView tenant_listbaiyeuthich_recycleview;
    private FavouritesRoom favouritesRoom;

    private String token;
    private String phoneOwner;
    private String role;
    private BaiVietAdapter baiVietAdapter;
    private List<BaiViet> list ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_yeu_thich);
        favouritesRoom = new FavouritesRoom();
        list = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        role = preferences.getString("role", "");
        tenant_listbaiyeuthich_recycleview = findViewById(R.id.tenant_listbaiyeuthich_recycleview);


        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        tenant_listbaiyeuthich_recycleview.setLayoutManager(layoutManager);
        baiVietAdapter = new BaiVietAdapter(  list,this,role, token, "like", phoneOwner);
        tenant_listbaiyeuthich_recycleview.setAdapter(baiVietAdapter);

        getdata();
    }

    private void getdata() {
        ApiFavouriteRoom.apiApiFavouriteRoom.getFavouritesRoomByUser(phoneOwner, token).enqueue(new Callback<FavouritesRoom>() {
            @Override
            public void onResponse(Call<FavouritesRoom> call, Response<FavouritesRoom> response) {
                if (response.isSuccessful()) {
                    favouritesRoom = response.body();
                    list.addAll(response.body().getPost());
                    baiVietAdapter.notifyDataSetChanged();
                } else {
                    // Log the error if the response is not successful
                    Log.e("API Response", "Error: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<FavouritesRoom> call, Throwable t) {
                // Log the failure
                Log.e("API Call", "Failed to get data", t);
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