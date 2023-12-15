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

public class ListBaiDangTimOGhepActivity extends AppCompatActivity {

    private Button baidangTimOGhepDangHoatDongButton;
    private Button baidangTimOGhepKhongHoatDongButton;
    private RecyclerView baidangTimOGhepRecyclerView;
    private FloatingActionButton menuAddBaidangTimOGhepButton;

    private void inti(){
        baidangTimOGhepDangHoatDongButton = findViewById(R.id.baidangtimoghepdanghoatdong);
        baidangTimOGhepKhongHoatDongButton = findViewById(R.id.baidangtimoghepkhonghoatdong);
        baidangTimOGhepRecyclerView = findViewById(R.id.tenant_baidangtimphongoghep_add_recycleview);
        menuAddBaidangTimOGhepButton = findViewById(R.id.menu_add_baidangtimphongoghep);
    }

    private void event(){
        baidangTimOGhepDangHoatDongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        baidangTimOGhepKhongHoatDongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        menuAddBaidangTimOGhepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListBaiDangTimOGhepActivity.this, FormTimNguoiOGhepActivity.class));
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_dang_tim_oghep);
        inti();
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