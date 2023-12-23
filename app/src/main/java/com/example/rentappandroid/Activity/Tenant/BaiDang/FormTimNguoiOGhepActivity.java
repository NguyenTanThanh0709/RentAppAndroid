package com.example.rentappandroid.Activity.Tenant.BaiDang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.rentappandroid.Activity.Landlord.FORMADD.FormAddRoomHouseActivity;
import com.example.rentappandroid.Adapter.DistrictAdapter;
import com.example.rentappandroid.Adapter.ImageAdapter;
import com.example.rentappandroid.Adapter.ProvincesAdapter;
import com.example.rentappandroid.Adapter.ServiceChargeAdapter;
import com.example.rentappandroid.Adapter.TienNghiAdapter;
import com.example.rentappandroid.Adapter.TimNguoiOGhepAdapter;
import com.example.rentappandroid.Adapter.WardAdapter;
import com.example.rentappandroid.Dto.District;
import com.example.rentappandroid.Dto.Provinces;
import com.example.rentappandroid.Dto.Reponse.ServiceCharge;
import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
import com.example.rentappandroid.Dto.Request.Add.ServiceChargeRE;
import com.example.rentappandroid.Dto.Request.Add.timNguoiOGhepRequest;
import com.example.rentappandroid.Dto.Request.Schema.Address;
import com.example.rentappandroid.Dto.Ward;
import com.example.rentappandroid.Model.LoaiNha;
import com.example.rentappandroid.Model.TienNghi;
import com.example.rentappandroid.Model.TimNguoiOGhep;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiAddress;
import com.example.rentappandroid.api.ApiPhiDichVu;
import com.example.rentappandroid.api.ApiTienNghi;
import com.example.rentappandroid.api.ApiTimNguoiOGhep;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormTimNguoiOGhepActivity extends AppCompatActivity implements ServiceChargeAdapter.ServiceChargeClickListener{
    private String token = "";
    private String idu = "";
    private String receivedId = "";
    private TimNguoiOGhep timNguoiOGhep;
    StorageReference storageReference;
    private List<String> list;
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


        for (String imageUrl : timNguoiOGhep.getImage_url()) {
            Log.d("Image URL", imageUrl);
            Uri imageUri = Uri.parse(imageUrl);
            selectedImages.add(imageUri);
        }
        updateImageAdapter();

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
    private void handelButton(){
        themBaiDangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload(selectedImages);
            }
        });
    }
    private void upload(ArrayList<Uri> selectedImages) {
        if(selectedImages.size() == 0){
            Toast.makeText(FormTimNguoiOGhepActivity.this, "Vui Lòng Chọn Ảnh", Toast.LENGTH_SHORT).show();
            return;
        }

        int totalImages = selectedImages.size();
        final int[] uploadedImages = {0};

        for (Uri imageUri : selectedImages) {
            StorageReference storageReference1 = storageReference.child("image/" + UUID.randomUUID().toString());
            storageReference1.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Increment the counter for successful uploads
                    uploadedImages[0]++;
                    Toast.makeText(FormTimNguoiOGhepActivity.this, "OK", Toast.LENGTH_SHORT).show();

                    storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri downloadUri) {
                            // Handle the download URL
                            String imageUrl = downloadUri.toString();
                            Log.d("URL", imageUrl);
                            list.add(imageUrl);

                            // Check if all images are uploaded
                            if (uploadedImages[0] == totalImages) {
                                // All images are uploaded, now you can trigger the API call
                                Add();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle any errors getting the download URL
                            Toast.makeText(FormTimNguoiOGhepActivity.this, "Error getting download URL", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Handle upload failure
                    Toast.makeText(FormTimNguoiOGhepActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void Add(){
        if(list.size() != selectedImages.size()){
            return;
        }
        try {
            String title = tenPhongTroEditText.getText().toString();
            String mota = moTaPhongTroEditText.getText().toString();
            int gia = Integer.parseInt(giaTroEditText.getText().toString());
            int dientich = Integer.parseInt(dienTichEditText.getText().toString());
            int songuoi = Integer.parseInt(sucChuaEditText.getText().toString());
            String selectedProvince = provinceSpinner.getSelectedItem().toString();
            String selectedDistrict = districtSpinner.getSelectedItem().toString();
            String selectedWard = wardSpinner.getSelectedItem().toString();
            String tenduong= tenDuongEditText.getText().toString();
            List<String> tiennghi1 = new ArrayList<>();
            for (TienNghi tienNghi2 : tienNghiList){
                if(tienNghi2.get__v() == 1){
                    tiennghi1.add(tienNghi2.get_id());
                }
            }
            List<ServiceChargeRE> listServiceChargeRE = new ArrayList<>();
            for (ServireChareReponse servireChareReponse: serviceChargeList){
                ServiceChargeRE s = new ServiceChargeRE(servireChareReponse.get_id(), Double.parseDouble(servireChareReponse.getPhi()));
                listServiceChargeRE.add(s);
            }




            if (songuoi <= 0) {
                showToast("Số lượng phải là một số dương lớn hơn 0");
                return;
            }
            if (gia <= 0) {
                showToast("Giá trọ phải là một số dương lớn hơn 0");
                return;
            }
            if (dientich <= 0) {
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

            if (tenduong.isEmpty()) {
                // Show an error message or handle the case where the description is empty
                showToast("Vui lòng Lại địa chỉ");
                return;
            }

            Address address = new Address(selectedProvince,selectedDistrict,selectedWard,tenduong);

            if (selectedWard.equals("Chọn Phường") || selectedProvince.equals("Chọn Tỉnh") || selectedDistrict.equals("Chọn Quận")) {
                // Show an error message or handle the case where the province or district is not selected
                showToast("Vui lòng chọn tỉnh/thành phố và quận/huyện");
                return;
            }

            String date = LocalDate.now().toString();
            timNguoiOGhepRequest t = new timNguoiOGhepRequest(
                    title,idu, mota,songuoi,gia,list, dientich,"Đang hoạt động",date,
                    tiennghi1,address,listServiceChargeRE
            );


            if(type.equals("")){
                ApiTimNguoiOGhep.apiApiTimNguoiOGhep.add(t, token).enqueue(new Callback<Void>() {
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
                ApiTimNguoiOGhep.apiApiTimNguoiOGhep.put(receivedId,t,token).enqueue(new Callback<Void>() {
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


        }catch (NumberFormatException e) {
            // Handle the case where parsing integer values fails
            showToast("Vui lòng nhập số nguyên hợp lệ cho số lượng và giá trọ");
        }
    }

    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tim_nguoi_oghep);

        FirebaseApp.initializeApp(getApplicationContext());
        storageReference = FirebaseStorage.getInstance().getReference();
        list = new ArrayList<>();

        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
        token = preferences.getString("token", "");
        idu = preferences.getString("sdt", "");


        timNguoiOGhep =new TimNguoiOGhep();
        tienNghiList = new ArrayList<>();
        serviceChargeList = new ArrayList<>();
        listProvicense = new ArrayList<>();
        districtList = new ArrayList<>();
        districtList.add(new District("Chọn Quận", -1,-1));
        wardList = new ArrayList<>();
        wardList.add(new Ward("Chọn Phường", -1,-1));

        init();
        getData();
        handelButton();
        Intent intent = getIntent();
        if (intent != null) {
            receivedId = intent.getStringExtra("ID_KEY");
            type = intent.getStringExtra("type");
            receivedId = (receivedId != null) ? receivedId : "";

            type = (type != null) ? type : "";
            if(type.equals("edit")){
                getDatae();
            }
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL,false);
        imgRecyclerView.setLayoutManager(gridLayoutManager);
        imageAdapter = new ImageAdapter(this, selectedImages);
        imgRecyclerView.setAdapter(imageAdapter);
        chooseImgTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissionAndOpenGallery();
            }
        });
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
    private ArrayList<Uri> selectedImages = new ArrayList<>();
    private ImageAdapter imageAdapter;

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

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    private void requestPermissionAndOpenGallery() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {
            openGallery();
        }
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // Allow multiple image selection
        startActivityForResult(galleryIntent, 102);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission denied. Cannot open gallery.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 102) {
                if (data.getClipData() != null) {
                    // Multiple images selected
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        selectedImages.add(imageUri);
                    }
                    updateImageAdapter();
                } else if (data.getData() != null) {
                    // Single image selected
                    Uri imageUri = data.getData();
                    selectedImages.add(imageUri);
                    updateImageAdapter();
                }
            }
        }
    }
    private void updateImageAdapter() {
        imageAdapter = new ImageAdapter(this, selectedImages);
        imgRecyclerView.setAdapter(imageAdapter);
    }
}