package com.example.rentappandroid.Activity.Tenant.BaiDang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rentappandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListBaiDangTimPhongActivity extends AppCompatActivity {

    private Button baidangTimTroDangHoatDongButton;
    private Button baidangTimTroKhongHoatDongButton;
    private RecyclerView baidangTimPhongTroRecyclerView;
    private FloatingActionButton menuAddBaidangTimPhongTroButton;

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

            }
        });

        baidangTimTroKhongHoatDongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        menuAddBaidangTimPhongTroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListBaiDangTimPhongActivity.this, FormTimPhongActivity.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_dang_tim_phong);
        init();
        event();
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