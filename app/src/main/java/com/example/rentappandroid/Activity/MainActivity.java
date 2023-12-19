package com.example.rentappandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rentappandroid.Activity.Landlord.FORMADD.FormPostActivity;
import com.example.rentappandroid.Activity.Landlord.FORMLIST.QuanLtBaiDangActivity;
import com.example.rentappandroid.Activity.Tenant.BaiDang.MainBaiDangActivity;
import com.example.rentappandroid.Fragment.Landlord.CaNhanFragment;
import com.example.rentappandroid.Fragment.Landlord.DichVuFragment;
import com.example.rentappandroid.Fragment.Landlord.TinNhanFragment;
import com.example.rentappandroid.Fragment.Landlord.TrangChuFragment;
import com.example.rentappandroid.Fragment.Tenant.CaNhanTenantFragment;
import com.example.rentappandroid.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static  final int FRAGMENT_TRANGCHU = 0;
    private static  final int FRAGMENT_DICHVU = 1;
    private static  final int FRAGMENT_TINNHAN = 2;
    private static  final int FRAGMENT_CANHAN = 3;

    private static  int FRAGMENT_CURRENT = FRAGMENT_TRANGCHU;
    private BottomNavigationView bottomNavigationView;

    private  void init(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void event(){
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                rePlaceFragment(new TrangChuFragment());
                FRAGMENT_CURRENT = FRAGMENT_TRANGCHU;
                return true;
            } else if (item.getItemId() == R.id.shorts) {
                rePlaceFragment(new DichVuFragment());
                FRAGMENT_CURRENT = FRAGMENT_DICHVU;
                return true;
            } else if (item.getItemId() == R.id.subscriptions) {
                rePlaceFragment(new TinNhanFragment());
                FRAGMENT_CURRENT = FRAGMENT_TINNHAN;
                return true;
            } else if (item.getItemId() == R.id.library) {
                if(role.equals("ADMIN")){
                    rePlaceFragment(new CaNhanFragment());
                }else {
                    rePlaceFragment(new CaNhanTenantFragment());
                }

                FRAGMENT_CURRENT = FRAGMENT_CANHAN;
                return true;
            } else {
                return false;
            }
        });
    }

    private String token;
    private String phoneOwner;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        role = preferences.getString("role", "");

        FloatingActionButton fabButton = findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "BottomAppBar Clicked", Toast.LENGTH_SHORT).show();
                if(role.equals("ADMIN")){
                    Intent intent = new Intent(MainActivity.this, QuanLtBaiDangActivity.class);
                    startActivity(intent);
                }else {
                    startActivity(new Intent(MainActivity.this, MainBaiDangActivity.class));
                }
            }
        });


        rePlaceFragment(new TrangChuFragment());
        init();
        event();



    }

    private  void rePlaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();
    }
}