    package com.example.rentappandroid.Activity.Landlord;

    import android.app.AlertDialog;
    import android.content.Intent;
    import android.content.pm.PackageManager;
    import android.net.Uri;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
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
    import com.example.rentappandroid.Adapter.ImageAdapter;
    import com.example.rentappandroid.Adapter.LoaiNhaAdapter;
    import com.example.rentappandroid.Adapter.RuleAdapter;
    import com.example.rentappandroid.Adapter.ServiceChargeAdapter;
    import com.example.rentappandroid.Adapter.TienNghiAdapter;
    import com.example.rentappandroid.Dto.Reponse.AreaInformationReponse;
    import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
    import com.example.rentappandroid.Dto.Request.Schema.AreaInformation;
    import com.example.rentappandroid.Model.LoaiNha;
    import com.example.rentappandroid.Model.TienNghi;
    import com.example.rentappandroid.R;

    import java.util.ArrayList;
    import java.util.List;
    public class FormAddRoomHouseActivity extends AppCompatActivity implements ServiceChargeAdapter.ServiceChargeClickListener, AreaInformationAdapter.AreaClickListener{

        private RecyclerView recyclerView;
        private TienNghiAdapter tienNghiAdapter;

        private RecyclerView recyclerViewLoaiNha;
        private LoaiNhaAdapter loaiNhaAdapter;


        private ArrayList<Uri> selectedImages = new ArrayList<>();
        private RecyclerView imgRecyclerView;
        private ImageAdapter imageAdapter;
        private static final int PERMISSION_REQUEST_CODE = 100;
        private static final int GALLERY_REQUEST_CODE = 102;

        private List<String> rules;
        private RuleAdapter ruleAdapter;
        private RecyclerView rule_recycleview;


        private List<ServireChareReponse> serviceChargeList;
        private ServiceChargeAdapter serviceChargeAdapter;
        private RecyclerView phidich_recycleview;

        private  List<AreaInformationReponse> areaInformationList;
        private AreaInformationAdapter areaInformationAdapter;
        private RecyclerView khuvucxungquanh_recycleview;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form_add_room_house);
            areaInformationList = new ArrayList<>();
            khuvucxungquanh_recycleview = findViewById(R.id.khuvucxungquanh_recycleview);
            serviceChargeList = new ArrayList<>();
            phidich_recycleview = findViewById(R.id.phidich_recycleview);
            adddataa();
            List<TienNghi> tienNghiList = new ArrayList<>();
            rules = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                String id = "id_" + i;
                String amenityName = "Amenity " + i;
                String amenityImg = "image_url_" + i; // Replace with actual image URL or resource ID
                boolean status = i % 2 == 0; // Just an example for alternating statuses

                TienNghi tienNghi = new TienNghi(id, amenityName, amenityImg, status);
                tienNghiList.add(tienNghi);
            }

            recyclerView = findViewById(R.id.tiennghi_recycleview);
            int spanCount = 3; // Number of columns
            GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
            recyclerView.setLayoutManager(layoutManager);
            tienNghiAdapter = new TienNghiAdapter(tienNghiList, this);
            recyclerView.setAdapter(tienNghiAdapter);


            List<LoaiNha> loaiNhaList = new ArrayList<>();
            loaiNhaList.add(new LoaiNha("1", "Type 1"));
            loaiNhaList.add(new LoaiNha("2", "Type 2"));
            loaiNhaList.add(new LoaiNha("3", "Type 3"));
            loaiNhaList.add(new LoaiNha("4", "Type 4"));
            loaiNhaList.add(new LoaiNha("5", "Type 5"));

            recyclerViewLoaiNha = findViewById(R.id.loainha_recycleview);
            GridLayoutManager layoutManager1 = new GridLayoutManager(this, spanCount);
            recyclerViewLoaiNha.setLayoutManager(layoutManager1);
            loaiNhaAdapter = new LoaiNhaAdapter(this, loaiNhaList );
            recyclerViewLoaiNha.setAdapter(loaiNhaAdapter);


            TextView chooseImgTextView = findViewById(R.id.chooseIMG);
            imgRecyclerView = findViewById(R.id.img_recycleview);

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




        }

        private void adddataa(){
            serviceChargeList.add(new ServireChareReponse(
                    "65682a48983c94be1013c648",
                    "Điện",
                    "https://example.com/cleaning_image.jpg",
                    true
            ));

            serviceChargeList.add(new ServireChareReponse(
                    "65682a54983c94be1013c64a",
                    "Nước",
                    "https://example.com/cleaning_image.jpg",
                    true
            ));

            serviceChargeList.add(new ServireChareReponse(
                    "65682a5b983c94be1013c64c",
                    "Mạng Wifi",
                    "https://example.com/cleaning_image.jpg",
                    true
            ));

            serviceChargeList.add(new ServireChareReponse(
                    "65682a65983c94be1013c64e",
                    "Giữ xe",
                    "https://example.com/cleaning_image.jpg",
                    true
            ));

            serviceChargeList.add(new ServireChareReponse(
                    "6569b08f2985f35f5aa87b41",
                    "Quản lý chung",
                    "https://example.com/cleaning_image.jpg",
                    true
            ));

            areaInformationList.add(new AreaInformationReponse("65683961f32e4aabf2faccd6", "Gần Chợ", "schools.jpg", 0));
            areaInformationList.add(new AreaInformationReponse("65683969f32e4aabf2faccd8", "Gần Siêu Thị", "schools.jpg", 0));
            areaInformationList.add(new AreaInformationReponse("65683973f32e4aabf2faccda", "Gần Cửa Hàng Tiện Lợi", "schools.jpg", 0));
            areaInformationList.add(new AreaInformationReponse("65683987f32e4aabf2faccdc", "Gần trung tâm thương mại", "schools.jpg", 0));
            areaInformationList.add(new AreaInformationReponse("65683995f32e4aabf2faccde", "Gần bệnh viện phòng khám", "schools.jpg", 0));
            areaInformationList.add(new AreaInformationReponse("6568399ff32e4aabf2facce0", "Gần trường học", "schools.jpg", 0));
            areaInformationList.add(new AreaInformationReponse("656839c6f32e4aabf2facce2", "Gần trạm xe buýt", "schools.jpg", 0));
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
    }

