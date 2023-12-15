package com.example.rentappandroid.Activity.Tenant.TieuChiTim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rentappandroid.R;

public class FormTieuChiTimActivity extends AppCompatActivity {

    private Spinner spinnerProvince, spinnerDistrict;
    private TextView Tieuchitimphong_idPhongHienTai;
    private EditText editTextGiaTro, editTextSoNguoi;
    private RecyclerView recyclerViewLoaiPhong, recyclerViewTienNghi;
    private Button buttonthemtieuchi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tieu_chi_tim);
        buttonthemtieuchi = findViewById(R.id.buttonthemtieuchi);
        spinnerProvince = findViewById(R.id.Tieuchitimphong_modal_timphong_provinces_phongtromuontim);
        spinnerDistrict = findViewById(R.id.Tieuchitimphong_modal_timphong_district_phongtromuontim);
        Tieuchitimphong_idPhongHienTai = findViewById(R.id.Tieuchitimphong_idPhongHienTai);

        editTextGiaTro = findViewById(R.id.Tieuchitimphong_modal_timphong_editText_giatromuontim);
        editTextSoNguoi = findViewById(R.id.Tieuchitimphong_modal_timphong_editText_songuoi);

        recyclerViewLoaiPhong = findViewById(R.id.Tieuchitimphong_modal_timphong_loaiphongmuontim_recycleview);
        recyclerViewTienNghi = findViewById(R.id.Tieuchitimphong_tieuchitimphong_tiennghi);
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