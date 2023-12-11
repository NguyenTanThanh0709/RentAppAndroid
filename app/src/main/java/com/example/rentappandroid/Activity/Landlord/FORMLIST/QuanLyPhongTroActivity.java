package com.example.rentappandroid.Activity.Landlord.FORMLIST;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rentappandroid.Fragment.Landlord.QuanLyPhongTro.MainFragment;
import com.example.rentappandroid.Fragment.Landlord.TrangChuFragment;
import com.example.rentappandroid.R;

public class QuanLyPhongTroActivity extends AppCompatActivity {

    private static  final int FRAGMENT_MAIN = 0;

    private static  int FRAGMENT_CURRENT = FRAGMENT_MAIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phong_tro);
        rePlaceFragment(new MainFragment());
    }

    private  void rePlaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_quanlyphongtro,fragment);
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