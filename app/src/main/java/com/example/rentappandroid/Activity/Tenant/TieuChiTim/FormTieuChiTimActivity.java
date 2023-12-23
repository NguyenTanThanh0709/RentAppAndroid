package com.example.rentappandroid.Activity.Tenant.TieuChiTim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Adapter.DistrictAdapter;
import com.example.rentappandroid.Adapter.LoaiNhaAdapter;
import com.example.rentappandroid.Adapter.ProvincesAdapter;
import com.example.rentappandroid.Adapter.TienNghiAdapter;
import com.example.rentappandroid.Dto.District;
import com.example.rentappandroid.Dto.Provinces;
import com.example.rentappandroid.Dto.Request.Add.TieuChiChonPhongRequest;
import com.example.rentappandroid.Model.LoaiNha;
import com.example.rentappandroid.Model.TienNghi;
import com.example.rentappandroid.Model.searchcriterias;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiAddress;
import com.example.rentappandroid.api.ApiTienNghi;
import com.example.rentappandroid.api.ApiTieuChiChonPhong;
import com.example.rentappandroid.api.ApiTypeHouse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormTieuChiTimActivity extends AppCompatActivity {

    private Spinner spinnerProvinces, spinnerDistrict;
    private TextView Tieuchitimphong_idPhongHienTai;
    private EditText editTextGiaTro, editTextSoNguoi;
    private RecyclerView recyclerViewLoaiNha, recyclerViewTienNghi;
    private Button buttonthemtieuchi;
    private TieuChiChonPhongRequest tieuChiChonPhongRequest;
    private searchcriterias searchcriterias_ = null;


    private List<Provinces> listProvicense;
    private  List<District> districtList;
    private List<LoaiNha> loaiNhaList;
    private List<TienNghi> tienNghiList;
    private TienNghiAdapter tienNghiAdapter;
    private LoaiNhaAdapter loaiNhaAdapter;
    private String token;
    private String idUser;

    private void getData_(){
        ApiTieuChiChonPhong.apiApiTieuChiChonPhong.getTieuChiPhong(idUser,token).enqueue(new Callback<searchcriterias>() {
            @Override
            public void onResponse(Call<searchcriterias> call, Response<searchcriterias> response) {
                searchcriterias_ = new searchcriterias();
                searchcriterias_ = response.body();
                if(response.body() != null){
                    Tieuchitimphong_idPhongHienTai.setText(searchcriterias_.getLocation());
                    editTextGiaTro.setText(searchcriterias_.getMaxPrice() + "") ;
                    editTextSoNguoi.setText(searchcriterias_.getPeopleNumber() + "");


                    for (TienNghi tienNghi : searchcriterias_.getAmenities()){
                        for (TienNghi tienNghi1 : tienNghiList){
                            if(tienNghi.get_id().equals(tienNghi1.get_id())){
                                tienNghi1.set__v(1);
                            }
                        }
                    }
                    for (LoaiNha loaiNha: loaiNhaList){
                        if(loaiNha.get_id().equals(searchcriterias_.getTypehouse().get_id())){
                            loaiNha.set__v(1);
                        }
                    }
                    loaiNhaAdapter.notifyDataSetChanged();
                    tienNghiAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<searchcriterias> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tieu_chi_tim);

        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
        token = preferences.getString("token", "");
        idUser = preferences.getString("sdt", "");


        buttonthemtieuchi = findViewById(R.id.buttonthemtieuchi);
        spinnerProvinces = findViewById(R.id.Tieuchitimphong_modal_timphong_provinces_phongtromuontim);
        spinnerDistrict = findViewById(R.id.Tieuchitimphong_modal_timphong_district_phongtromuontim);
        Tieuchitimphong_idPhongHienTai = findViewById(R.id.Tieuchitimphong_idPhongHienTai);

        editTextGiaTro = findViewById(R.id.Tieuchitimphong_modal_timphong_editText_giatromuontim);
        editTextSoNguoi = findViewById(R.id.Tieuchitimphong_modal_timphong_editText_songuoi);

        recyclerViewLoaiNha = findViewById(R.id.Tieuchitimphong_modal_timphong_loaiphongmuontim_recycleview);
        recyclerViewTienNghi = findViewById(R.id.Tieuchitimphong_tieuchitimphong_tiennghi);


        tienNghiList = new ArrayList<>();
        loaiNhaList = new ArrayList<>();
        listProvicense = new ArrayList<>();
        districtList = new ArrayList<>();
        districtList.add(new District("Chọn Quận", -1,-1));

        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 3);
        recyclerViewLoaiNha.setLayoutManager(layoutManager1);
        loaiNhaAdapter = new LoaiNhaAdapter(this, loaiNhaList );
        recyclerViewLoaiNha.setAdapter(loaiNhaAdapter);

        int spanCount = 3; // Number of columns
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerViewTienNghi.setLayoutManager(layoutManager);
        tienNghiAdapter = new TienNghiAdapter(tienNghiList, this);
        recyclerViewTienNghi.setAdapter(tienNghiAdapter);


        getdataa();
        getData_();
        
        event();
    }

    private void event() {
        buttonthemtieuchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int giatro = Integer.parseInt(editTextGiaTro.getText().toString());
                int songuoi = Integer.parseInt(editTextSoNguoi.getText().toString());
                String selectedProvince = spinnerProvinces.getSelectedItem().toString();
                String selectedDistrict = spinnerDistrict.getSelectedItem().toString();
                String diachi = selectedProvince + "-" + selectedDistrict;
                String typeHouse = "";
                for (LoaiNha loaiNha: loaiNhaList){
                    if(loaiNha.get__v() == 1){
                        typeHouse = loaiNha.get_id();
                        break;
                    }
                }

                List<String> list = new ArrayList<>();
                for (TienNghi tienNghi : tienNghiList){
                    if(tienNghi.get__v() == 1){
                        list.add(tienNghi.get_id());
                    }
                }
                tieuChiChonPhongRequest = new TieuChiChonPhongRequest(idUser,diachi,giatro,songuoi,typeHouse,list);
                ApiTieuChiChonPhong.apiApiTieuChiChonPhong.updateTieuChi(idUser,tieuChiChonPhongRequest,token).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(FormTieuChiTimActivity.this,"Upload Thành Công!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });




            }
        });
    }

    private void getdataa(){
        ApiTypeHouse.apiTypeHouse.getListTypeHouse(token).enqueue(new Callback<List<LoaiNha>>() {
            @Override
            public void onResponse(Call<List<LoaiNha>> call, Response<List<LoaiNha>> response) {
                loaiNhaList.addAll(response.body());
                loaiNhaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<LoaiNha>> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });
        ApiTienNghi.apiTienNghi.getListTienNghi(token).enqueue(new Callback<List<TienNghi>>() {
            @Override
            public void onResponse(Call<List<TienNghi>> call, Response<List<TienNghi>> response) {
                tienNghiList.addAll(response.body());
                tienNghiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TienNghi>> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });


        ApiAddress.apiDriverTrip.getListProvices().enqueue(new Callback<List<Provinces>>() {
            @Override
            public void onResponse(Call<List<Provinces>> call, Response<List<Provinces>> response) {
                listProvicense =  response.body();
                listProvicense.add(0, new Provinces("Chọn Tỉnh", -1));
                ProvincesAdapter provincesAdapter = new ProvincesAdapter(FormTieuChiTimActivity.this, listProvicense);
                spinnerProvinces.setAdapter(provincesAdapter);
                Log.d("OK","OK");
            }

            @Override
            public void onFailure(Call<List<Provinces>> call, Throwable t) {

            }
        });
        spinnerProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Lấy thông tin của mục được chọn từ danh sách Provinces
                Provinces selectedProvince = listProvicense.get(position);
                ApiAddress.apiDriverTrip.getListDistricts().enqueue(new Callback<List<District>>() {
                    @Override
                    public void onResponse(Call<List<District>> call, Response<List<District>> response) {
                        List<District> listtemp = new ArrayList<>();
                        listtemp = response.body();
                        districtList.clear();
                        districtList.add(new District("Chọn Quận", -1,-1));
                        for (District district: listtemp){
                            if(district.getProvince_code() == selectedProvince.getCode()){
                                districtList.add(district);
                            }
                        }
                        DistrictAdapter prcesAdapter = new DistrictAdapter(FormTieuChiTimActivity.this, districtList);
                        spinnerDistrict.setAdapter(prcesAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<District>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
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