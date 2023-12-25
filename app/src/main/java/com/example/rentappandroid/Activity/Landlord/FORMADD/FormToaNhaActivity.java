package com.example.rentappandroid.Activity.Landlord.FORMADD;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Adapter.AreaInformationAdapter;
import com.example.rentappandroid.Adapter.DistrictAdapter;
import com.example.rentappandroid.Adapter.ImageAdapter;
import com.example.rentappandroid.Adapter.ProvincesAdapter;
import com.example.rentappandroid.Adapter.WardAdapter;
import com.example.rentappandroid.Dto.District;
import com.example.rentappandroid.Dto.Provinces;
import com.example.rentappandroid.Dto.Reponse.AreaInformation;
import com.example.rentappandroid.Dto.Reponse.AreaInformationReponse;
import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Dto.Reponse.RoomReponseComplex;
import com.example.rentappandroid.Dto.Reponse.RoomingHouseComplex;
import com.example.rentappandroid.Dto.Request.Add.AreaInformationRequest;
import com.example.rentappandroid.Dto.Request.Add.RoomingHouseComplexRequest;
import com.example.rentappandroid.Dto.Request.Schema.Address;
import com.example.rentappandroid.Dto.Ward;
import com.example.rentappandroid.R;
import com.example.rentappandroid.Utils.MultiSelectionSpinner;
import com.example.rentappandroid.api.ApiAddress;
import com.example.rentappandroid.api.ApiArea;
import com.example.rentappandroid.api.ApiRoomHouse;
import com.example.rentappandroid.api.ApiRoomingHouseComplex;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormToaNhaActivity extends AppCompatActivity implements AreaInformationAdapter.AreaClickListener{
    private EditText editTextTenToaNha, tenDuong;
    private TextView diaChiCuThe;
    private Spinner spinnerProvinces, spinnerDistrict, spinnerWards;
    private EditText editTextDiaChiCuThe;
    private List<String> listNameNhaTro;
    private Button buttonAddRoomComplex;
    private List<Room> listRoom ;
    private List<Provinces> listProvicense;
    private  List<District> districtList;
    private  List<Ward> wardList;

    private  List<AreaInformationReponse> areaInformationList;
    private AreaInformationAdapter areaInformationAdapter;
    private String token;
    private String phoneOwner;
    private String nameOwner;

    List<String> list;
    private void init(){
        // Find views by ID
        editTextTenToaNha = findViewById(R.id.editText_TenToaNha);
        diaChiCuThe = findViewById(R.id.DiaChiCuThe);
        spinnerProvinces = findViewById(R.id.provinces_ToaNha);
        spinnerDistrict = findViewById(R.id.district_ToaNha);
        spinnerWards = findViewById(R.id.wards_ToaNha);
        editTextDiaChiCuThe = findViewById(R.id.diachicuathe);
        vucxungquanh_toanha_recycleview = findViewById(R.id.vucxungquanh_toanha_recycleview);
        khuvucxungquanh_toanha_recycleview = findViewById(R.id.khuvucxungquanh_toanha_recycleview);
        tenDuong= findViewById(R.id.tenDuong);
        buttonAddRoomComplex = findViewById(R.id.buttonAddRoomComplex);


    }
    StorageReference storageReference;
    private ArrayList<Uri> selectedImages = new ArrayList<>();
    private void upload(ArrayList<Uri> selectedImages) {
        if(selectedImages.size() == 0){
            progressBar.setVisibility(View.GONE);
            Toast.makeText(FormToaNhaActivity.this ,"Vui lòng chọn ảnh", Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(FormToaNhaActivity.this, "OK", Toast.LENGTH_SHORT).show();
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
                                addRoomingHouseComplex();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle any errors getting the download URL
                            Toast.makeText(FormToaNhaActivity.this, "Error getting download URL", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Handle upload failure
                    Toast.makeText(FormToaNhaActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void addRoomingHouseComplex() {
        String RoomingHouseComplex_name = editTextTenToaNha.getText().toString().trim();
        String owner = phoneOwner;
        String selectedProvince = spinnerProvinces.getSelectedItem().toString();
        String selectedDistrict = spinnerDistrict.getSelectedItem().toString();
        String selectedWard = spinnerWards.getSelectedItem().toString();
        String tenduong= tenDuong.getText().toString();
        Address address = new Address(selectedProvince,selectedDistrict,selectedWard,tenduong);
        List<AreaInformationRequest> areaInformationRequests = new ArrayList<>();
        for (AreaInformationReponse areaInformationReponse: areaInformationList){
            AreaInformationRequest areaInformationRequest = new AreaInformationRequest(areaInformationReponse.get_id(),areaInformationReponse.getDistance(),areaInformationReponse.getDescription());
            areaInformationRequests.add(areaInformationRequest);
        }

        if(selectedImages.size() == 0){
            progressBar.setVisibility(View.GONE);

            Toast.makeText(FormToaNhaActivity.this, "Vui Lòng Chọn Ảnh", Toast.LENGTH_SHORT).show();
            return;
        }

        if(list.size() != selectedImages.size()){
            return;
        }


        RoomingHouseComplexRequest roomingHouseComplexRequest = new RoomingHouseComplexRequest(
                RoomingHouseComplex_name,
                list,
                owner,
                address,
                areaInformationRequests
        );


        if(type.equals("edit")){

            ApiRoomingHouseComplex.apiRoomingHouseComplex.put(roomingHouseComplexId,roomingHouseComplexRequest,token).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(FormToaNhaActivity.this, "Chỉnh sửa Thành Công", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(FormToaNhaActivity.this, "CHỉnh sửa không Thành Công", Toast.LENGTH_SHORT).show();
                }
            });

        }else {
            ApiRoomingHouseComplex.apiRoomingHouseComplex.add(roomingHouseComplexRequest,token).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(FormToaNhaActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(FormToaNhaActivity.this, "Thêm Không Thành Công", Toast.LENGTH_SHORT).show();

                }
            });
        }



    }

    private RecyclerView khuvucxungquanh_toanha_recycleview, vucxungquanh_toanha_recycleview;
    private ImageAdapter imageAdapter;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final int GALLERY_REQUEST_CODE = 102;

    private RoomingHouseComplex roomingHouseComplex;
    private String type = "";
    String roomingHouseComplexId ="";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_toa_nha);
        progressBar = findViewById(R.id.progressBarcomplex);
        FirebaseApp.initializeApp(getApplicationContext());
        storageReference = FirebaseStorage.getInstance().getReference();
        list  = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);
// Retrieve values
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        nameOwner = preferences.getString("name", "");  // Replace "" with the default value if not found


        init();
        areaInformationList = new ArrayList<>();
        listRoom =new ArrayList<>();
        listNameNhaTro = new ArrayList<>();
        roomingHouseComplex = new RoomingHouseComplex();
        TextView chooseImgTextView = findViewById(R.id.chooseIMG);
        chooseImgTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissionAndOpenGallery();
            }
        });

        listProvicense = new ArrayList<>();
        districtList = new ArrayList<>();
        districtList.add(new District("Chọn Quận", -1,-1));
        wardList = new ArrayList<>();
        wardList.add(new Ward("Chọn Phường", -1,-1));



        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL,false);
        khuvucxungquanh_toanha_recycleview.setLayoutManager(gridLayoutManager);
        imageAdapter = new ImageAdapter(this, selectedImages);
        khuvucxungquanh_toanha_recycleview.setAdapter(imageAdapter);


        GridLayoutManager gridLayoutManager____ = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL,false);
        vucxungquanh_toanha_recycleview.setLayoutManager(gridLayoutManager____);
        areaInformationAdapter = new AreaInformationAdapter(this, areaInformationList, this);
        vucxungquanh_toanha_recycleview.setAdapter(areaInformationAdapter);

        event();
        Intent intent = getIntent();
        if (intent != null) {
             roomingHouseComplexId = intent.getStringExtra("roomingHouseComplex");
            type = intent.getStringExtra("type");
            roomingHouseComplexId = (roomingHouseComplexId != null) ? roomingHouseComplexId : "";

// If type is null, set it to an empty string
            type = (type != null) ? type : "";
            if(type.equals("edit")){
                fillData(roomingHouseComplexId);
            }
        }

    }

    private void event() {

        buttonAddRoomComplex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                list.clear();
                upload(selectedImages);
            }
        });

        ApiArea.apiArea.getListArea(token).enqueue(new Callback<List<AreaInformationReponse>>() {
            @Override
            public void onResponse(Call<List<AreaInformationReponse>> call, Response<List<AreaInformationReponse>> response) {
                areaInformationList.addAll(response.body());
                areaInformationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AreaInformationReponse>> call, Throwable t) {

            }
        });

        ApiAddress.apiDriverTrip.getListProvices().enqueue(new Callback<List<Provinces>>() {
            @Override
            public void onResponse(Call<List<Provinces>> call, Response<List<Provinces>> response) {
                listProvicense =  response.body();
                listProvicense.add(0, new Provinces("Chọn Tỉnh", -1));
                ProvincesAdapter provincesAdapter = new ProvincesAdapter(FormToaNhaActivity.this, listProvicense);
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
                        DistrictAdapter prcesAdapter = new DistrictAdapter(FormToaNhaActivity.this, districtList);
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
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                        WardAdapter prceswAdapter = new WardAdapter(FormToaNhaActivity.this, wardList);
                        spinnerWards.setAdapter(prceswAdapter);
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

    private void fillData(String roomingHouseComplexId){





        ApiRoomingHouseComplex.apiRoomingHouseComplex.getListRoomingHouseComplexByID(roomingHouseComplexId, token).enqueue(new Callback<RoomingHouseComplex>() {
            @Override
            public void onResponse(Call<RoomingHouseComplex> call, Response<RoomingHouseComplex> response) {
                roomingHouseComplex = response.body();
                editTextTenToaNha.setText(roomingHouseComplex.getRoomingHouseComplex_name());
                editTextDiaChiCuThe.setText(roomingHouseComplex.getAddress().getFullAddress());
                selectedImages.clear();

                // Iterate through the list of image URLs and convert them to URIs
                for (String imageUrl : roomingHouseComplex.getImage_url()) {
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
                for (AreaInformation areaInformation : roomingHouseComplex.getAreaInformation()) {
                    String areaInformationId = areaInformation.getAreaInformationID().get_id();
                    if (areaInformationMap.containsKey(areaInformationId)) {
                        AreaInformationReponse areaInformationReponse = areaInformationMap.get(areaInformationId);
                        areaInformationReponse.setDistance(areaInformation.getDistance());
                        areaInformationReponse.setDescription(areaInformation.getDescription());
                    }
                }

                areaInformationAdapter.notifyDataSetChanged();

                String ok = "";
                for(RoomReponseComplex roomReponseComplex: roomingHouseComplex.getListroom()){
                    ok += roomReponseComplex.getTitle() + " - ";
                }



            }

            @Override
            public void onFailure(Call<RoomingHouseComplex> call, Throwable t) {

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
        khuvucxungquanh_toanha_recycleview.setAdapter(imageAdapter);
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

    @Override
    public void onAreaClick(AreaInformationReponse areaInformationReponse) {
        openAddRuleModalArea(areaInformationReponse);
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
}