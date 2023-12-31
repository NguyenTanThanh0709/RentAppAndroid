package com.example.rentappandroid.Activity.Landlord.FORMLIST;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rentappandroid.Fragment.Landlord.QuanLyBaiDang.Main_BaiDang_Fragment;
import com.example.rentappandroid.Fragment.Landlord.QuanLyHopDong.MainQuanLyHopDongFragment;
import com.example.rentappandroid.R;

public class QuanLyHopDongActivity extends AppCompatActivity {

    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_hop_dong);
        Intent intent = getIntent();
        if (intent.hasExtra("type")) {
            type = intent.getStringExtra("type");
        } else {
            // Không có dữ liệu được truyền
            Log.d("HopDongId", "Không có dữ liệu được truyền");
        }
        rePlaceFragment(new MainQuanLyHopDongFragment());

    }

    private  void rePlaceFragment(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        // Đặt Bundle vào Fragment
        fragment.setArguments(bundle);

        // Thực hiện transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_quanlyhopdong, fragment);
        transaction.commit();
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