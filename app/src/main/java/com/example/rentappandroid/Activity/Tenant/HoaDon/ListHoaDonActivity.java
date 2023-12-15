package com.example.rentappandroid.Activity.Tenant.HoaDon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.rentappandroid.R;

public class ListHoaDonActivity extends AppCompatActivity {

    private Button hoadonchuathanhtoanButton;
    private Button hoadondahanhtoanButton;
    private RecyclerView tenantHoadonRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don2);
        hoadonchuathanhtoanButton = findViewById(R.id.hoadonchuathanhtoan);
        hoadondahanhtoanButton = findViewById(R.id.hoadondahanhtoan);
        tenantHoadonRecyclerView = findViewById(R.id.tenant_hoadon_recycleview);

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