package com.example.rentappandroid.Activity.Tenant.BaiDang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.rentappandroid.Activity.LoginActivity;
import com.example.rentappandroid.Activity.RegisterActivity;
import com.example.rentappandroid.R;

public class MainBaiDangActivity extends AppCompatActivity {

    private CardView baidangtimtroClick, baidangtimoghepClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bai_dang);
        baidangtimtroClick = findViewById(R.id.tenant_baidangtimtro_click);
        baidangtimoghepClick = findViewById(R.id.tenant_baidangtimoghep_click);

        baidangtimtroClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainBaiDangActivity.this, ListBaiDangTimPhongActivity.class));
            }
        });
        baidangtimoghepClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainBaiDangActivity.this, ListBaiDangTimOGhepActivity.class));
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