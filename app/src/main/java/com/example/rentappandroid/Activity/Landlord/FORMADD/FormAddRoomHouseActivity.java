    package com.example.rentappandroid.Activity.Landlord.FORMADD;

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
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.MultiAutoCompleteTextView;
    import android.widget.RadioButton;
    import android.widget.RadioGroup;
    import android.widget.Spinner;
    import android.widget.TextView;
    import android.widget.Toast;
    import android.Manifest;
    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.app.ActivityCompat;
    import androidx.core.content.ContextCompat;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.rentappandroid.Adapter.AreaInformationAdapter;
    import com.example.rentappandroid.Adapter.DistrictAdapter;
    import com.example.rentappandroid.Adapter.ImageAdapter;
    import com.example.rentappandroid.Adapter.LoaiNhaAdapter;
    import com.example.rentappandroid.Adapter.ProvincesAdapter;
    import com.example.rentappandroid.Adapter.RuleAdapter;
    import com.example.rentappandroid.Adapter.ServiceChargeAdapter;
    import com.example.rentappandroid.Adapter.TienNghiAdapter;
    import com.example.rentappandroid.Adapter.WardAdapter;
    import com.example.rentappandroid.Dto.District;
    import com.example.rentappandroid.Dto.Provinces;
    import com.example.rentappandroid.Dto.Reponse.AreaInformation;
    import com.example.rentappandroid.Dto.Reponse.AreaInformationReponse;
    import com.example.rentappandroid.Dto.Reponse.Room;
    import com.example.rentappandroid.Dto.Reponse.RoomingHouseComplex;
    import com.example.rentappandroid.Dto.Reponse.ServiceCharge;
    import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
    import com.example.rentappandroid.Dto.Request.Add.AreaInformationRequest;
    import com.example.rentappandroid.Dto.Request.Add.RoomingHouseRequest;
    import com.example.rentappandroid.Dto.Request.Add.ServiceChargeRE;
    import com.example.rentappandroid.Dto.Request.Schema.Address;
    import com.example.rentappandroid.Dto.Ward;
    import com.example.rentappandroid.Model.LoaiNha;
    import com.example.rentappandroid.Model.TienNghi;
    import com.example.rentappandroid.R;
    import com.example.rentappandroid.api.ApiAddress;
    import com.example.rentappandroid.api.ApiArea;
    import com.example.rentappandroid.api.ApiPhiDichVu;
    import com.example.rentappandroid.api.ApiRoomHouse;
    import com.example.rentappandroid.api.ApiRoomingHouseComplex;
    import com.example.rentappandroid.api.ApiTienNghi;
    import com.example.rentappandroid.api.ApiTypeHouse;
    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.firebase.FirebaseApp;
    import com.google.firebase.storage.FirebaseStorage;
    import com.google.firebase.storage.StorageReference;
    import com.google.firebase.storage.UploadTask;

    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.UUID;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class FormAddRoomHouseActivity extends AppCompatActivity implements ServiceChargeAdapter.ServiceChargeClickListener, AreaInformationAdapter.AreaClickListener{
        private static final int PERMISSION_REQUEST_CODE = 100;
        private static final int GALLERY_REQUEST_CODE = 102;
        private EditText editText_TenPhongtro;
        private MultiAutoCompleteTextView editText_MoTaPhongTro;
        private EditText editText_giatro;
        private EditText editText_dienTich;
        private EditText editText_SucChua;
        private EditText editText_TenToaNha;
        private Spinner spinner_provinces_phongtro;
        private Spinner spinner_district_phongtro, toanha_phongtro;
        private Spinner spinner_wards_phongtro;
        private EditText tenDuong_phongtro;
        private EditText diachicuathe_phongtro;
        private RadioGroup radioGroupStatus;
        private RadioButton radioButtonRented;
        private RadioButton radioButtonEmptyRoom;
        private RadioButton radioButtonMaintenance;
        private String type = "1";

        private void init(){
            buttonAddRoom = findViewById(R.id.buttonAddRoom);
            editText_TenPhongtro = findViewById(R.id.editText_TenPhongtro);
            editText_MoTaPhongTro = findViewById(R.id.editText_MoTaPhongTro);
            editText_giatro = findViewById(R.id.editText_giatro);
            editText_dienTich = findViewById(R.id.editText_dienTich);
            editText_SucChua = findViewById(R.id.editText_SucChua);
            editText_TenToaNha = findViewById(R.id.editText_TenToaNha);
            spinner_provinces_phongtro = findViewById(R.id.provinces_phongtro);
            spinner_district_phongtro = findViewById(R.id.district_phongtro);
            spinner_wards_phongtro = findViewById(R.id.wards_phongtro);
            tenDuong_phongtro = findViewById(R.id.tenDuong_phongtro);
            diachicuathe_phongtro = findViewById(R.id.diachicuathe_phongtro);
            radioGroupStatus = findViewById(R.id.radioGroupStatus);
            radioButtonRented = findViewById(R.id.rented);
            radioButtonEmptyRoom = findViewById(R.id.emptyroom);
            radioButtonMaintenance = findViewById(R.id.maintenance);
            recyclerView = findViewById(R.id.tiennghi_recycleview);
            khuvucxungquanh_recycleview = findViewById(R.id.khuvucxungquanh_recycleview);
            phidich_recycleview = findViewById(R.id.phidich_recycleview);
            recyclerViewLoaiNha = findViewById(R.id.loainha_recycleview);
            imgRecyclerView = findViewById(R.id.img_recycleview);
            toanha_phongtro =  findViewById(R.id.toanha_phongtro);
        }
        private List<String> tenToaNha;
        private RecyclerView recyclerView;
        private RecyclerView recyclerViewLoaiNha;
        private LoaiNhaAdapter loaiNhaAdapter;

        private ArrayList<Uri> selectedImages = new ArrayList<>();
        StorageReference storageReference;
        private List<String> list;
        private RecyclerView imgRecyclerView;
        private ImageAdapter imageAdapter;
        private List<String> rules;
        private RuleAdapter ruleAdapter;
        private RecyclerView rule_recycleview;
        private RecyclerView phidich_recycleview;
        private  List<AreaInformationReponse> areaInformationList;
        private AreaInformationAdapter areaInformationAdapter;
        private RecyclerView khuvucxungquanh_recycleview;
        private List<Provinces> listProvicense;
        private  List<District> districtList;
        private  List<Ward> wardList;
        private List<ServireChareReponse> serviceChargeList;
        private ServiceChargeAdapter serviceChargeAdapter;
        private List<TienNghi> tienNghiList;
        private TienNghiAdapter tienNghiAdapter;
        private Room room;
        private List<LoaiNha> loaiNhaList;
        private String token;
        private String phoneOwner;
        private String nameOwner;

        private Button buttonAddRoom;
        private String roomingHouse = "";

        private void upload(ArrayList<Uri> selectedImages) {
            if(selectedImages.size() == 0){
                Toast.makeText(FormAddRoomHouseActivity.this, "Vui Lòng Chọn Ảnh", Toast.LENGTH_SHORT).show();
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

                        Toast.makeText(FormAddRoomHouseActivity.this, "OK", Toast.LENGTH_SHORT).show();
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
                                    AddHouse();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle any errors getting the download URL
                                Toast.makeText(FormAddRoomHouseActivity.this, "Error getting download URL", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle upload failure
                        Toast.makeText(FormAddRoomHouseActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        private void AddHouse() {

            if(list.size() != selectedImages.size()){
                return;
            }

            String title = editText_TenPhongtro.getText().toString();
            String mota = editText_MoTaPhongTro.getText().toString();
            if (title.trim().isEmpty()) {
                showToast("Title cannot be empty");
                return;
            }

            if (mota.trim().isEmpty()) {
                showToast("Description cannot be empty");
                return;
            }

            int giatro;
            try {
                giatro = Integer.parseInt(editText_giatro.getText().toString());
            } catch (NumberFormatException e) {
                showToast("Invalid GiaTro value");
                return;
            }

            int dientich;
            try {
                dientich = Integer.parseInt(editText_dienTich.getText().toString());
            } catch (NumberFormatException e) {
                showToast("Invalid DienTich value");
                return;
            }

            int succhua;
            try {
                succhua = Integer.parseInt(editText_SucChua.getText().toString());
            } catch (NumberFormatException e) {
                showToast("Invalid SucChua value");
                return;
            }

            String selectedProvince = spinner_provinces_phongtro.getSelectedItem().toString();
            String selectedDistrict = spinner_district_phongtro.getSelectedItem().toString();
            String selectedWard = spinner_wards_phongtro.getSelectedItem().toString();
            String tenduong= tenDuong_phongtro.getText().toString();

            if (selectedProvince.equals("Chọn Tỉnh")) {
                showToast("Please select a province");
                return;
            }

// Check if a district is selected
            if (selectedDistrict.equals("Chọn Quận")) {
                showToast("Please select a district");
                return;
            }

// Check if a ward is selected
            if (selectedWard.equals("Chọn Phường")) {
                showToast("Please select a ward");
                return;
            }

// Validate EditText value (assuming it cannot be empty)
            if (tenduong.trim().isEmpty()) {
                showToast("Street name cannot be empty");
                return;
            }

            Address address = new Address(selectedProvince,selectedDistrict,selectedWard,tenduong);
            String status = "";

            String tentoanha = null;
            if(!toanha_phongtro.getSelectedItem().toString().equals("")){
                String[] split = toanha_phongtro.getSelectedItem().toString().split("-");
                tentoanha = split[split.length-1];
            }
            if(radioButtonRented.isChecked()){
                status = "RENTED";
            } else if (radioButtonEmptyRoom.isChecked()) {
                status = "EMPTYROOM";
            }else {
                status = "MAINTENANCE";
            }

            String loaitro = "";
            for (LoaiNha loaiNha: loaiNhaList){
                if(loaiNha.get__v() == 1){
                    loaitro = loaiNha.get_id();
                    break;
                }
            }

            List<String> listTienNghi = new ArrayList<>();
            for (TienNghi tienNghi: tienNghiList){
                if(tienNghi.get__v() == 1){
                    listTienNghi.add(tienNghi.get_id());
                }
            }

            // rules

            List<ServiceChargeRE> listServiceChargeRE = new ArrayList<>();
            for (ServireChareReponse servireChareReponse: serviceChargeList){
                ServiceChargeRE s = new ServiceChargeRE(servireChareReponse.get_id(), Double.parseDouble(servireChareReponse.getPhi()));
                listServiceChargeRE.add(s);
            }

            List<AreaInformationRequest> areaInformationRequests = new ArrayList<>();
            for (AreaInformationReponse areaInformationReponse: areaInformationList){
                AreaInformationRequest areaInformationRequest = new AreaInformationRequest(areaInformationReponse.get_id(),areaInformationReponse.getDistance(),areaInformationReponse.getDescription());
                areaInformationRequests.add(areaInformationRequest);
            }




            String currentDate = LocalDate.now().toString();
//String title, String description, int price, int squareFeet, String status, String roomingHouseComplex,
// String availableDates, int peopleNumber, String owner, String typeHouse, List<String> amenities,
// Address address, List<String> imageURL, List<String> rules, List<ServiceChargeRE> serviceCharge, List<AreaInformationRequest> areaInformation
            RoomingHouseRequest roomingHouseRequest = new RoomingHouseRequest(title,mota
                ,giatro, dientich,status, tentoanha, currentDate, succhua,phoneOwner,loaitro,listTienNghi,
                    address, list, rules, listServiceChargeRE,areaInformationRequests
            );


            if(type.equals("edit")){
                ApiRoomHouse.apiRoom.Put(roomingHouse,roomingHouseRequest, token).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            showToast("Chỉnh sửa Nhà Trọ Thành Công");
                            Log.d("API Call Success", "API call was successful");
                        } else {
                            showToast("Chỉnh sửa Nhà Trọ Thất Bại");

                            // Log information when the API call is not successful
                            Log.e("API Call Error", "Error during API call. Response code: " + response.code());
                            String errorMessage = "Chỉnh sửa Nhà Trọ Thất Bại\n" + response.message();
                            showToast(errorMessage);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        showToast("Chỉnh sửa Nhà Trọ Thất Bại");

                        // Log the error
                        Log.e("API Call Error", "Error during API call", t);

                        // You can also log the error message
                        // Log.e("API Call Error", "Error message: " + t.getMessage());

                        // If you want to display the error message in the toast, you can do something like this:
                        String errorMessage = "Chỉnh sửa Nhà Trọ Thất Bại\n" + t.getMessage();
                        showToast(errorMessage);
                    }
                });
            }else {


            ApiRoomHouse.apiRoom.add(roomingHouseRequest, token).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        showToast("Thêm Nhà Trọ Thành Công");
                        Log.d("API Call Success", "API call was successful");
                    } else {
                        showToast("Thêm Nhà Trọ Thất Bại");

                        // Log information when the API call is not successful
                        Log.e("API Call Error", "Error during API call. Response code: " + response.code());
                        String errorMessage = "Thêm Nhà Trọ Thất Bại\n" + response.message();
                        showToast(errorMessage);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    showToast("Thêm Nhà Trọ Thất Bại");

                    // Log the error
                    Log.e("API Call Error", "Error during API call", t);

                    // You can also log the error message
                    // Log.e("API Call Error", "Error message: " + t.getMessage());

                    // If you want to display the error message in the toast, you can do something like this:
                    String errorMessage = "Thêm Nhà Trọ Thất Bại\n" + t.getMessage();
                    showToast(errorMessage);
                }
            });
            }


        }

        private void showToast(String message) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        private void event(){

            buttonAddRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.clear();
                    upload(selectedImages);

                }
            });


            ApiRoomingHouseComplex.apiRoomingHouseComplex.getListRoomingHouseComplexByOwer(phoneOwner, token).enqueue(new Callback<List<RoomingHouseComplex>>() {
                @Override
                public void onResponse(Call<List<RoomingHouseComplex>> call, Response<List<RoomingHouseComplex>> response) {
                    tenToaNha = new ArrayList<>();
                    tenToaNha.add("");
                    for(RoomingHouseComplex roomingHouseComplex : response.body()) {
                        String id = roomingHouseComplex.getAddress().getFullAddress() + "-" + roomingHouseComplex.get_id();
                        tenToaNha.add(id);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(FormAddRoomHouseActivity.this, android.R.layout.simple_spinner_item, tenToaNha);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    toanha_phongtro.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<List<RoomingHouseComplex>> call, Throwable t) {
                    Log.e("OK", t.toString());
                }
            });



            ApiArea.apiArea.getListArea(token).enqueue(new Callback<List<AreaInformationReponse>>() {
                @Override
                public void onResponse(Call<List<AreaInformationReponse>> call, Response<List<AreaInformationReponse>> response) {
                    if (response.isSuccessful()) {
                        // Process the successful response
                        areaInformationList.addAll(response.body());
                        areaInformationAdapter.notifyDataSetChanged();
                    } else {
                        // Log the error if the response is not successful
                        Log.e("API Call", "Error: " + response.code() + " - " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<AreaInformationReponse>> call, Throwable t) {
                    Log.e("API Call", "Failure: " + t.getMessage(), t);
                }
            });
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
                    ProvincesAdapter provincesAdapter = new ProvincesAdapter(FormAddRoomHouseActivity.this, listProvicense);
                    spinner_provinces_phongtro.setAdapter(provincesAdapter);
                    Log.d("OK","OK");
                }

                @Override
                public void onFailure(Call<List<Provinces>> call, Throwable t) {

                }
            });
            spinner_provinces_phongtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            DistrictAdapter prcesAdapter = new DistrictAdapter(FormAddRoomHouseActivity.this, districtList);
                            spinner_district_phongtro.setAdapter(prcesAdapter);
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
            spinner_district_phongtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            WardAdapter prceswAdapter = new WardAdapter(FormAddRoomHouseActivity.this, wardList);
                            spinner_wards_phongtro.setAdapter(prceswAdapter);
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

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form_add_room_house);

            FirebaseApp.initializeApp(getApplicationContext());
            storageReference = FirebaseStorage.getInstance().getReference();
            list = new ArrayList<>();
            SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
             token = preferences.getString("token", "");  // Replace "" with the default value if not found
             phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
             nameOwner = preferences.getString("name", "");  // Replace "" with the default value if not found
            loaiNhaList = new ArrayList<>();
            areaInformationList = new ArrayList<>();
            room = new Room();
            rules = new ArrayList<>();

            tienNghiList = new ArrayList<>();
            serviceChargeList = new ArrayList<>();
            listProvicense = new ArrayList<>();
            districtList = new ArrayList<>();
            districtList.add(new District("Chọn Quận", -1,-1));
            wardList = new ArrayList<>();
            wardList.add(new Ward("Chọn Phường", -1,-1));

            init();



            int spanCount = 3; // Number of columns
            GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
            recyclerView.setLayoutManager(layoutManager);
            tienNghiAdapter = new TienNghiAdapter(tienNghiList, this);
            recyclerView.setAdapter(tienNghiAdapter);



            GridLayoutManager layoutManager1 = new GridLayoutManager(this, spanCount);
            recyclerViewLoaiNha.setLayoutManager(layoutManager1);
            loaiNhaAdapter = new LoaiNhaAdapter(this, loaiNhaList );
            recyclerViewLoaiNha.setAdapter(loaiNhaAdapter);


            TextView chooseImgTextView = findViewById(R.id.chooseIMG);
            chooseImgTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestPermissionAndOpenGallery();
                }
            });
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL,false);
            imgRecyclerView.setLayoutManager(gridLayoutManager);
            imageAdapter = new ImageAdapter(this, selectedImages);
            imgRecyclerView.setAdapter(imageAdapter);


            TextView addRuleTextView = findViewById(R.id.addRule);
            addRuleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openAddRuleModal();
                }
            });
            rule_recycleview = findViewById(R.id.rule_recycleview);
            GridLayoutManager gridLayoutManager__ = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL,false);
            rule_recycleview.setLayoutManager(gridLayoutManager__);
            ruleAdapter = new RuleAdapter(this, rules);
            rule_recycleview.setAdapter(ruleAdapter);


            GridLayoutManager gridLayoutManager___ = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL,false);
            phidich_recycleview.setLayoutManager(gridLayoutManager___);
            serviceChargeAdapter = new ServiceChargeAdapter(this, serviceChargeList, this);
            phidich_recycleview.setAdapter(serviceChargeAdapter);


            GridLayoutManager gridLayoutManager____ = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL,false);
            khuvucxungquanh_recycleview.setLayoutManager(gridLayoutManager____);
            areaInformationAdapter = new AreaInformationAdapter(this, areaInformationList, this);
            khuvucxungquanh_recycleview.setAdapter(areaInformationAdapter);


            event();
            Intent intent = getIntent();
            if (intent != null) {
                roomingHouse = intent.getStringExtra("roomingHouse");
                type= intent.getStringExtra("type");
                roomingHouse = (roomingHouse != null) ? roomingHouse : "";

// If type is null, set it to an empty string
                type = (type != null) ? type : "";
                if(type.equals("edit")){
                    fillData(roomingHouse);
                }
            }

        }

        private void fillData(String roomingHouse) {
            ApiRoomHouse.apiRoom.getListRoomByID(roomingHouse,token).enqueue(new Callback<Room>() {
                @Override
                public void onResponse(Call<Room> call, Response<Room> response) {
                    room = response.body();
                    editText_TenPhongtro.setText(room.getTitle());
                    editText_MoTaPhongTro.setText(room.getDescription());
                    editText_giatro.setText(room.getPrice() + "");
                    editText_dienTich.setText(room.getSquare_feet() + "");
                    editText_SucChua.setText(room.getPeopeleNumber() + "");
                    editText_TenToaNha.setText(room.getRoominghousecomplex());
                    diachicuathe_phongtro.setText(room.getAddress().getFullAddress());
                    if(room.getStatus().equals("RENTED")){
                        radioButtonRented.setChecked(true);
                    } else if (room.getStatus().equals("EMPTYROOM")) {
                        radioButtonEmptyRoom.setChecked(true);
                    }else {
                        radioButtonMaintenance.setChecked(true);
                    }
                    for(LoaiNha in: loaiNhaList){
                        if(room.getTypehouse().get_id().equals(in.get_id())){
                            in.set__v(1);
                        }
                    }
                    loaiNhaAdapter.notifyDataSetChanged();

                    for(TienNghi in : room.getAmenities()){
                        for(TienNghi on : tienNghiList){
                            if(in.get_id().equals(on.get_id())){
                                on.set__v(1);
                                break;
                            }
                        }
                    }
                    tienNghiAdapter.notifyDataSetChanged();

                    rules.clear();
                    rules.addAll(room.getRules());
                    ruleAdapter.notifyDataSetChanged();
                    for (String imageUrl : room.getImage_url()) {
                        Log.d("Image URL", imageUrl);
                        Uri imageUri = Uri.parse(imageUrl);
                        selectedImages.add(imageUri);
                    }
                    updateImageAdapter();

                    Map<String, AreaInformationReponse> areaInformationMap = new HashMap<>();
                    for (AreaInformationReponse areaInformationReponse : areaInformationList) {
                        areaInformationMap.put(areaInformationReponse.get_id(), areaInformationReponse);
                    }
// Update AreaInformationReponse objects using the map
                    for (AreaInformation areaInformation : room.getAreaInformation()) {
                        String areaInformationId = areaInformation.getAreaInformationID().get_id();
                        if (areaInformationMap.containsKey(areaInformationId)) {
                            AreaInformationReponse areaInformationReponse = areaInformationMap.get(areaInformationId);
                            areaInformationReponse.setDistance(areaInformation.getDistance());
                            areaInformationReponse.setDescription(areaInformation.getDescription());
                        }
                    }
                    areaInformationAdapter.notifyDataSetChanged();


                    for (ServireChareReponse in: serviceChargeList){

                        for (ServiceCharge serviceCharge : room.getServiceCharge()){
                            if(in.get_id().equals(serviceCharge.getServiceChargeId().get_id())){
                                in.setPhi(serviceCharge.getPrice()+"");
                            }

                        }
                    }
                    serviceChargeAdapter.notifyDataSetChanged();


                }

                @Override
                public void onFailure(Call<Room> call, Throwable t) {

                }
            });
        }


        private void requestPermissionAndOpenGallery() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            } else {
                openGallery();
            }
        }
        private void openGallery() {
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // Allow multiple image selection
            startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            if (requestCode == PERMISSION_REQUEST_CODE) {
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
                if (requestCode == GALLERY_REQUEST_CODE) {
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


        private void openAddRuleModal() {
            // Inflate the modal layout
            View modalView = getLayoutInflater().inflate(R.layout.modal_layout, null);

            // Create the AlertDialog
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setView(modalView);

            // Find components in the modal layout
            EditText editTextRule = modalView.findViewById(R.id.editTextRule);
            Button btnConfirm = modalView.findViewById(R.id.btnConfirm);
            Button btnCancel = modalView.findViewById(R.id.btnCancel);

            // Create the AlertDialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // Set up click listener for the confirm button
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the entered text
                    String newRule = editTextRule.getText().toString();
                    rules.add(newRule);
                    ruleAdapter.notifyDataSetChanged();

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





        @Override
        public void onAreaClick(AreaInformationReponse servireChareReponse) {
            openAddRuleModalArea(servireChareReponse);
        }
        private void openAddRuleModalArea(AreaInformationReponse servireChareReponse) {
            // Inflate the modal layout
            View modalView = getLayoutInflater().inflate(R.layout.modal2_layout, null);

            // Create the AlertDialog
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setView(modalView);

            // Find components in the modal layout
            TextView textViewModal = modalView.findViewById(R.id.text_namearea);
            EditText editTextModal1 = modalView.findViewById(R.id.text_khoangcacharea);
            EditText editTextModal2 = modalView.findViewById(R.id.text_motaarea);
            Button btnConfirm = modalView.findViewById(R.id.btnConfirm__);
            Button btnCancel = modalView.findViewById(R.id.btnCancel__);

            // Set data from servireChareReponse
            textViewModal.setText(servireChareReponse.getAreainformation_name());

            // Create the AlertDialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // Set up click listener for the confirm button
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the entered text
                    String kc = editTextModal1.getText().toString();
                    String mt = editTextModal2.getText().toString();
                    areaInformationAdapter.updatePhi(servireChareReponse.get_id(), Double.parseDouble(kc),mt);
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

