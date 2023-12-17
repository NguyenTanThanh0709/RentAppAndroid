package com.example.rentappandroid.Activity.Tenant.BaiDang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rentappandroid.Activity.Landlord.FORMADD.FormAddRoomHouseActivity;
import com.example.rentappandroid.Adapter.DistrictAdapter;
import com.example.rentappandroid.Adapter.ProvincesAdapter;
import com.example.rentappandroid.Adapter.ServiceChargeAdapter;
import com.example.rentappandroid.Adapter.TienNghiAdapter;
import com.example.rentappandroid.Adapter.WardAdapter;
import com.example.rentappandroid.Dto.District;
import com.example.rentappandroid.Dto.Provinces;
import com.example.rentappandroid.Dto.Reponse.ServiceCharge;
import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
import com.example.rentappandroid.Dto.Ward;
import com.example.rentappandroid.Model.TienNghi;
import com.example.rentappandroid.Model.TimNguoiOGhep;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiAddress;
import com.example.rentappandroid.api.ApiPhiDichVu;
import com.example.rentappandroid.api.ApiTienNghi;
import com.example.rentappandroid.api.ApiTimNguoiOGhep;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormTimNguoiOGhepActivity extends AppCompatActivity implements ServiceChargeAdapter.ServiceChargeClickListener{
    private String token = "";
    private String receivedId = "";
    private TimNguoiOGhep timNguoiOGhep;

    EditText tenPhongTroEditText;
    MultiAutoCompleteTextView moTaPhongTroEditText;
    EditText giaTroEditText;
    EditText dienTichEditText;
    EditText sucChuaEditText;
    Spinner provinceSpinner, districtSpinner, wardSpinner;
    EditText tenDuongEditText, diaChiCuTheEditText;
    RecyclerView  tienNghiRecyclerView, imgRecyclerView, phiDichVuRecyclerView;
    TextView chooseImgTextView, addPhiDichVuTextView;
    Button themBaiDangButton;

    private void anhxa() {
        tenPhongTroEditText.setText(timNguoiOGhep.getTitle());
        moTaPhongTroEditText.setText(timNguoiOGhep.getDescription());
        giaTroEditText.setText(timNguoiOGhep.getPrice() + "");
        sucChuaEditText.setText(timNguoiOGhep.getPeopeleNumber() + "");
        diaChiCuTheEditText.setText(timNguoiOGhep.getAddress().getFullAddress());
        dienTichEditText.setText(timNguoiOGhep.getSquare_feet() + "");

        for (TienNghi tienNghi : timNguoiOGhep.getAmenities()){
            for (TienNghi tienNghi1 : tienNghiList){
                if(tienNghi.get_id().equals(tienNghi1.get_id())){
                    tienNghi1.set__v(1);
                }
            }
        }

        //ServireChareReponse
        for (ServiceCharge serviceCharge: timNguoiOGhep.getServiceCharge()){
            for (ServireChareReponse servireChareReponse : serviceChargeList){
                if(serviceCharge.getServiceChargeId().get_id().equals(servireChareReponse.get_id())){
                    servireChareReponse.setPhi(serviceCharge.getPrice() + "");
                }
            }
        }
        tienNghiAdapter.notifyDataSetChanged();
        serviceChargeAdapter.notifyDataSetChanged();

    }

    private void init(){
        tenPhongTroEditText = findViewById(R.id._timnguoioghepeditText_TenPhongtro);
        moTaPhongTroEditText = findViewById(R.id._timnguoioghepeditText_MoTaPhongTro);
        giaTroEditText = findViewById(R.id._timnguoioghepeditText_giatro);
        dienTichEditText = findViewById(R.id._timnguoioghepeditText_dienTich);
        sucChuaEditText = findViewById(R.id._timnguoioghepeditText_SucChua);
        provinceSpinner = findViewById(R.id._timnguoioghepprovinces_phongtro);
        districtSpinner = findViewById(R.id._timnguoioghepdistrict_phongtro);
        wardSpinner = findViewById(R.id._timnguoioghepwards_phongtro);
        tenDuongEditText = findViewById(R.id._timnguoiogheptenDuong_phongtro);
        diaChiCuTheEditText = findViewById(R.id._timnguoioghepdiachicuathe_phongtro);
        tienNghiRecyclerView = findViewById(R.id._timnguoiogheptiennghi_recycleview);
        chooseImgTextView = findViewById(R.id._timnguoioghepchooseIMG);
        imgRecyclerView = findViewById(R.id._timnguoioghepimg_recycleview);
        addPhiDichVuTextView = findViewById(R.id._timnguoioghepaddPhiDichVu);
        phiDichVuRecyclerView = findViewById(R.id._timnguoioghepphidich_recycleview);
        themBaiDangButton = findViewById(R.id._timnguoioghepbuttonthembaidangtimnguoioghep);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tim_nguoi_oghep);
        timNguoiOGhep =new TimNguoiOGhep();
        tienNghiList = new ArrayList<>();
        serviceChargeList = new ArrayList<>();
        listProvicense = new ArrayList<>();
        districtList = new ArrayList<>();
        districtList.add(new District("Chọn Quận", -1,-1));
        wardList = new ArrayList<>();
        wardList.add(new Ward("Chọn Phường", -1,-1));

        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        init();
        getData();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ID_KEY") && intent.hasExtra("token")) {
            receivedId = intent.getStringExtra("ID_KEY");
            getDatae();
        }


        int spanCount = 3; // Number of columns
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        tienNghiRecyclerView.setLayoutManager(layoutManager);
        tienNghiAdapter = new TienNghiAdapter(tienNghiList, this);
        tienNghiRecyclerView.setAdapter(tienNghiAdapter);


        GridLayoutManager gridLayoutManager___ = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL,false);
        phiDichVuRecyclerView.setLayoutManager(gridLayoutManager___);
        serviceChargeAdapter = new ServiceChargeAdapter(this, serviceChargeList,this);
        phiDichVuRecyclerView.setAdapter(serviceChargeAdapter);
    }

    private void getData() {
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
        ApiPhiDichVu.apiPhiDichVu.getListPhiDichVu(token).enqueue(new Callback<List<ServireChareReponse>>() {
            @Override
            public void onResponse(Call<List<ServireChareReponse>> call, Response<List<ServireChareReponse>> response) {
                serviceChargeList.addAll(response.body());
                serviceChargeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ServireChareReponse>> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });
        ApiAddress.apiDriverTrip.getListProvices().enqueue(new Callback<List<Provinces>>() {
            @Override
            public void onResponse(Call<List<Provinces>> call, Response<List<Provinces>> response) {
                listProvicense =  response.body();
                listProvicense.add(0, new Provinces("Chọn Tỉnh", -1));
                ProvincesAdapter provincesAdapter = new ProvincesAdapter(FormTimNguoiOGhepActivity.this, listProvicense);
                provinceSpinner.setAdapter(provincesAdapter);
                Log.d("OK","OK");
            }

            @Override
            public void onFailure(Call<List<Provinces>> call, Throwable t) {

            }
        });
        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                        DistrictAdapter prcesAdapter = new DistrictAdapter(FormTimNguoiOGhepActivity.this, districtList);
                        districtSpinner.setAdapter(prcesAdapter);
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
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                District district_ = districtList.get(i);
                ApiAddress.apiDriverTrip.getListWard().enqueue(new Callback<List<Ward>>() {
                    @Override
                    public void onResponse(Call<List<Ward>> call, Response<List<Ward>> response) {
                        List<Ward> listtemp = new ArrayList<>();
                        listtemp = response.body();
                        wardList.clear();
                        wardList.add(new Ward("Chọn Phường", -1,-1));
                        for (Ward district: listtemp){
                            if(district.getDistrict_code() == district_.getCode()){
                                wardList.add(district);
                            }
                        }
                        WardAdapter prceswAdapter = new WardAdapter(FormTimNguoiOGhepActivity.this, wardList);
                        wardSpinner.setAdapter(prceswAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Ward>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getDatae() {
        ApiTimNguoiOGhep.apiApiTimNguoiOGhep.getTimNguoiOGhepByID(receivedId,token).enqueue(new Callback<TimNguoiOGhep>() {
            @Override
            public void onResponse(Call<TimNguoiOGhep> call, Response<TimNguoiOGhep> response) {
                timNguoiOGhep = response.body();
                anhxa();
            }

            @Override
            public void onFailure(Call<TimNguoiOGhep> call, Throwable t) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private List<Provinces> listProvicense;
    private  List<District> districtList;
    private  List<Ward> wardList;
    private List<ServireChareReponse> serviceChargeList;
    private ServiceChargeAdapter serviceChargeAdapter;
    private List<TienNghi> tienNghiList;
    private TienNghiAdapter tienNghiAdapter;



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

    @Override
    public void onServiceChargeClick(ServireChareReponse servireChareReponse) {
        openAddRuleModal(servireChareReponse);
    }

    private void openAddRuleModal(ServireChareReponse servireChareReponse) {
        // Inflate the modal layout
        View modalView = getLayoutInflater().inflate(R.layout.modal1_layout, null);

        // Create the AlertDialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(modalView);

        // Find components in the modal layout
        TextView textViewModal = modalView.findViewById(R.id.text_dichvuname);
        EditText editTextModal = modalView.findViewById(R.id.text_dichvuphi);
        Button btnConfirm = modalView.findViewById(R.id.btnConfirm_);
        Button btnCancel = modalView.findViewById(R.id.btnCancel_);

        // Set data from servireChareReponse
        textViewModal.setText(servireChareReponse.getServicecharge_name());

        // Create the AlertDialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // Set up click listener for the confirm button
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered text
                String newPhi = editTextModal.getText().toString();
                serviceChargeAdapter.updatePhi(servireChareReponse.get_id(), newPhi);
                alertDialog.dismiss();
            }
        });

        // Set up click listener for the cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the AlertDialog
                alertDialog.dismiss();
            }
        });

        // Show the AlertDialog
        alertDialog.show();
    }


}