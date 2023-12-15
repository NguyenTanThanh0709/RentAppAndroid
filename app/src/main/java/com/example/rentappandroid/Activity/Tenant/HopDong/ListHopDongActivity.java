package com.example.rentappandroid.Activity.Tenant.HopDong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.rentappandroid.R;

public class ListHopDongActivity extends AppCompatActivity {

    private Button hopdongConHieuLucButton;
    private Button hopdongHetHieuLucButton;
    private RecyclerView tenant_hopdong_recycleview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hop_dong);

        hopdongConHieuLucButton = findViewById(R.id.hopdong_conhieuluc);
        hopdongHetHieuLucButton = findViewById(R.id.hopdong_hethieuluc);
        tenant_hopdong_recycleview = findViewById(R.id.tenant_hopdong_recycleview);

        // Now you can use these variables as needed, for example, set click listeners
        hopdongConHieuLucButton.setOnClickListener(v -> {
            // Handle the click event for hopdong_conhieuluc
        });

        hopdongHetHieuLucButton.setOnClickListener(v -> {
            // Handle the click event for hopdong_hethieuluc
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