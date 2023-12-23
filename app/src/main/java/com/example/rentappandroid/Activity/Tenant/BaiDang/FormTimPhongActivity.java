package com.example.rentappandroid.Activity.Tenant.BaiDang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rentappandroid.Activity.Landlord.FORMADD.FormAddRoomHouseActivity;
import com.example.rentappandroid.Adapter.DistrictAdapter;
import com.example.rentappandroid.Adapter.LoaiNhaAdapter;
import com.example.rentappandroid.Adapter.ProvincesAdapter;
import com.example.rentappandroid.Adapter.TienNghiAdapter;
import com.example.rentappandroid.Dto.District;
import com.example.rentappandroid.Dto.Provinces;
import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
import com.example.rentappandroid.Dto.Request.Add.ServiceChargeRE;
import com.example.rentappandroid.Dto.Request.Add.TimtroRequest;
import com.example.rentappandroid.Dto.Ward;
import com.example.rentappandroid.Model.FindRoomHouseResponse;
import com.example.rentappandroid.Model.LoaiNha;
import com.example.rentappandroid.Model.TienNghi;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiAddress;
import com.example.rentappandroid.api.ApiPostFindHouse;
import com.example.rentappandroid.api.ApiTienNghi;
import com.example.rentappandroid.api.ApiTypeHouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormTimPhongActivity extends AppCompatActivity {
    private String token = "";
    private String receivedId = "";
    private String iduser = "";
    private FindRoomHouseResponse findRoomHouseResponse;

    private EditText editTextTieuDe;
    private MultiAutoCompleteTextView editTextMoTa;
    private EditText editTextGiaTro;
    private EditText editTextSoLuongNguoiO;
    private Spinner spinnerProvinces;
    private Spinner spinnerDistrict;
    private EditText editTextDiaChiCuThe;
    private RecyclerView recyclerViewLoaiNha;
    private RecyclerView recyclerViewTienNghi;
    private Button buttonTimNguoiOGhep;


    private List<Provinces> listProvicense;
    private  List<District> districtList;
    private List<LoaiNha> loaiNhaList;
    private List<TienNghi> tienNghiList;
    private TienNghiAdapter tienNghiAdapter;
    private LoaiNhaAdapter loaiNhaAdapter;
    private void anhxa(){
        editTextTieuDe.setText(findRoomHouseResponse.getTitle());
        editTextMoTa.setText(findRoomHouseResponse.getDescription());
        editTextGiaTro.setText(findRoomHouseResponse.getMaxPrice() + "");
        editTextSoLuongNguoiO.setText(findRoomHouseResponse.getPeopeleNumber() + "");
        editTextDiaChiCuThe.setText(findRoomHouseResponse.getAddress());

        for (TienNghi tienNghi : findRoomHouseResponse.getAmenities()){
            for (TienNghi tienNghi1 : tienNghiList){
                if(tienNghi.get_id().equals(tienNghi1.get_id())){
                    tienNghi1.set__v(1);
                }
            }
        }
        for (LoaiNha loaiNha: loaiNhaList){
            if(loaiNha.get_id().equals(findRoomHouseResponse.getTypehouse().get_id())){
                loaiNha.set__v(1);
            }
        }
        loaiNhaAdapter.notifyDataSetChanged();
        tienNghiAdapter.notifyDataSetChanged();

    }

private String type = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tim_phong);

        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
        token = preferences.getString("token", "");
        iduser = preferences.getString("sdt", "");
        findRoomHouseResponse = new FindRoomHouseResponse();
        tienNghiList = new ArrayList<>();
        loaiNhaList = new ArrayList<>();
        listProvicense = new ArrayList<>();
        districtList = new ArrayList<>();
        districtList.add(new District("Chọn Quận", -1,-1));
        init();
        getdataa();



        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 3);
        recyclerViewLoaiNha.setLayoutManager(layoutManager1);
        loaiNhaAdapter = new LoaiNhaAdapter(this, loaiNhaList );
        recyclerViewLoaiNha.setAdapter(loaiNhaAdapter);

        int spanCount = 3; // Number of columns
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerViewTienNghi.setLayoutManager(layoutManager);
        tienNghiAdapter = new TienNghiAdapter(tienNghiList, this);
        recyclerViewTienNghi.setAdapter(tienNghiAdapter);


        Intent intent = getIntent();
        if (intent != null) {
            receivedId = intent.getStringExtra("ID_KEY");
            type = intent.getStringExtra("type");
            receivedId = (receivedId != null) ? receivedId : "";

// If type is null, set it to an empty string
            type = (type != null) ? type : "";
            if(type.equals("edit")){
                getDatae();
            }

        }
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
                ProvincesAdapter provincesAdapter = new ProvincesAdapter(FormTimPhongActivity.this, listProvicense);
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
                        DistrictAdapter prcesAdapter = new DistrictAdapter(FormTimPhongActivity.this, districtList);
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

    private  void getDatae(){
        ApiPostFindHouse.apiApiPostFindHouse.getFindRoomHousesByID(receivedId,token).enqueue(new Callback<FindRoomHouseResponse>() {
            @Override
            public void onResponse(Call<FindRoomHouseResponse> call, Response<FindRoomHouseResponse> response) {
                findRoomHouseResponse = response.body();
                anhxa();
            }

            @Override
            public void onFailure(Call<FindRoomHouseResponse> call, Throwable t) {

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


    private void init(){
        editTextTieuDe = findViewById(R.id.editText_tieudetimphong);
        editTextMoTa = findViewById(R.id.editText_MoTaPhongTromuontim);
        editTextGiaTro = findViewById(R.id.editText_giatromuontim);
        editTextSoLuongNguoiO = findViewById(R.id.editText_soluongnguoio);
        spinnerProvinces = findViewById(R.id.provinces_phongtromuontim);
        spinnerDistrict = findViewById(R.id.district_phongtromuontim);
        editTextDiaChiCuThe = findViewById(R.id.diachicuathe_phongtromuontim);
        recyclerViewLoaiNha = findViewById(R.id.loainhamongmuontim_recycleview);
        recyclerViewTienNghi = findViewById(R.id.tienmongmuontim_recycleview);
        buttonTimNguoiOGhep = findViewById(R.id.buttontimnguoioghep);
        handleButton();
    }

    private void handleButton(){
        buttonTimNguoiOGhep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int soluong = 0;
                    int maxprice = 0;
                     soluong = Integer.parseInt(editTextSoLuongNguoiO.getText().toString());
                     maxprice = Integer.parseInt(editTextGiaTro.getText().toString());
                    String status = "Đang hoạt động";
                    String title = editTextTieuDe.getText().toString();
                    String mota = editTextMoTa.getText().toString();
                    String date = LocalDate.now().toString();
                    String selectedProvince = spinnerProvinces.getSelectedItem().toString();
                    String selectedDistrict = spinnerDistrict.getSelectedItem().toString();
                    String address = selectedProvince + "-" + selectedDistrict;
                    if (soluong <= 0) {
                        showToast("Số lượng phải là một số dương lớn hơn 0");
                        return;
                    }
                    if (maxprice <= 0) {
                        showToast("Giá trọ phải là một số dương lớn hơn 0");
                        return;
                    }
                    if (title.isEmpty()) {
                        // Show an error message or handle the case where the title is empty
                        showToast("Vui lòng nhập tiêu đề");
                        return;
                    }

                    if (mota.isEmpty()) {
                        // Show an error message or handle the case where the description is empty
                        showToast("Vui lòng nhập mô tả");
                        return;
                    }

                    if (selectedProvince.equals("Chọn Tỉnh") || selectedDistrict.equals("Chọn Quận")) {
                        // Show an error message or handle the case where the province or district is not selected
                        showToast("Vui lòng chọn tỉnh/thành phố và quận/huyện");
                        return;
                    }

                    String typeHouse = "";
                    for (LoaiNha loaiNha: loaiNhaList){
                        if(loaiNha.get__v() == 1){
                            typeHouse = loaiNha.get_id();
                            break;
                        }
                    }

                    if (typeHouse.isEmpty()) {
                        // Show an error message or handle the case where the description is empty
                        showToast("Vui lòng nhập loại nhà");
                        return;
                    }

                    List<String> tiennghi1 = new ArrayList<>();
                    for (TienNghi tienNghi2 : tienNghiList){
                        if(tienNghi2.get__v() == 1){
                            tiennghi1.add(tienNghi2.get_id());
                        }
                    }



                    TimtroRequest timtroRequest = new TimtroRequest(iduser,date,
                            typeHouse, tiennghi1, soluong,address,maxprice,mota,title,status);

                    if(type.equals("")) {
                        ApiPostFindHouse.apiApiPostFindHouse.add(timtroRequest,token).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    showToast("Thêm Bài đăng Thành Công");
                                    Log.d("API Call Success", "API call was successful");
                                } else {
                                    showToast("Thêm Bài đăng Thất Bại");

                                    // Log information when the API call is not successful
                                    Log.e("API Call Error", "Error during API call. Response code: " + response.code());
                                    String errorMessage = "Thêm Nhà Trọ Thất Bại\n" + response.message();
                                    showToast(errorMessage);
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                showToast("Thêm Bài đăng Thất Bại");

                            }
                        });
                    }else {
                        ApiPostFindHouse.apiApiPostFindHouse.update(receivedId,timtroRequest,token).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    showToast("Chỉnh Bài đăng Thành Công");
                                    Log.d("API Call Success", "API call was successful");
                                } else {
                                    showToast("Chỉnh Bài đăng Thất Bại");

                                    // Log information when the API call is not successful
                                    Log.e("API Call Error", "Error during API call. Response code: " + response.code());
                                    String errorMessage = "Thêm Nhà Trọ Thất Bại\n" + response.message();
                                    showToast(errorMessage);
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                showToast("Chỉnh Bài đăng Thất Bại");

                            }
                        });
                    }


                } catch (NumberFormatException e) {
                    // Handle the case where parsing integer values fails
                    showToast("Vui lòng nhập số nguyên hợp lệ cho số lượng và giá trọ");
                }


            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}