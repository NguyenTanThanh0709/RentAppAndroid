package com.example.rentappandroid.Fragment.Landlord;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rentappandroid.Activity.Landlord.FORMADD.FormAddRoomHouseActivity;
import com.example.rentappandroid.Adapter.BaiVietAdapter;
import com.example.rentappandroid.Adapter.DistrictAdapter;
import com.example.rentappandroid.Adapter.FindRoomHouseAdapter;
import com.example.rentappandroid.Adapter.Khamdapter;
import com.example.rentappandroid.Adapter.LoaiNhaAdapter;
import com.example.rentappandroid.Adapter.ProvincesAdapter;
import com.example.rentappandroid.Adapter.TimNguoiOGhepAdapter;
import com.example.rentappandroid.Dto.District;
import com.example.rentappandroid.Dto.Provinces;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.Model.FindRoomHouseResponse;
import com.example.rentappandroid.Model.LoaiNha;
import com.example.rentappandroid.Model.TimNguoiOGhep;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiAddress;
import com.example.rentappandroid.api.ApiBaiDang;
import com.example.rentappandroid.api.ApiPostFindHouse;
import com.example.rentappandroid.api.ApiTimNguoiOGhep;
import com.example.rentappandroid.api.ApiTypeHouse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangChuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangChuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangChuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangChuFragment newInstance(String param1, String param2) {
        TrangChuFragment fragment = new TrangChuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ImageView imageView___;
    ImageView imageView;
    TextView tieuchitimphong_text;
    LinearLayout timphong;
    LinearLayout tindangtim;
    LinearLayout timoghep;
    RecyclerView listimagekhampha_recycle;
    TextView xemthembaidang;
    RecyclerView listbaivietnoibat_recycle;
    RecyclerView listbaiviettimtro_recycle;
    TextView xemthemtimkiemphongtro;
    RecyclerView listbaiviettimnguoioghep_recycle;
    TextView xemthemtimkiemoghep;
    LinearLayout click_tieuchitimphong; // Adding this line

    private void init(View view){
        click_tieuchitimphong = view.findViewById(R.id.click_tieuchitimphong); // Adding this line
        imageView___ = view.findViewById(R.id.imageView___);
        imageView = view.findViewById(R.id.imageView);
        tieuchitimphong_text = view.findViewById(R.id.tieuchitimphong_text);
        timphong = view.findViewById(R.id.timphong);
        tindangtim = view.findViewById(R.id.tindangtim);
        timoghep = view.findViewById(R.id.timoghep);
        listimagekhampha_recycle = view.findViewById(R.id.listimagekhampha_recycle);
        xemthembaidang = view.findViewById(R.id.xemthembaidang);
        listbaivietnoibat_recycle = view.findViewById(R.id.listbaivietnoibat_recycle);
        listbaiviettimtro_recycle = view.findViewById(R.id.listbaiviettimtro_recycle);
        xemthemtimkiemphongtro = view.findViewById(R.id.xemthemtimkiemphongtro);
        listbaiviettimnguoioghep_recycle = view.findViewById(R.id.listbaiviettimnguoioghep_recycle);
        xemthemtimkiemoghep = view.findViewById(R.id.xemthemtimkiemoghep);
        handleXemThem();
    }

    private void handleXemThem(){
        xemthembaidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private List<Provinces> listProvicense;
    private  List<District> districtList;
    private List<LoaiNha> loaiNhaList;
    private LoaiNhaAdapter loaiNhaAdapter;


    private String tieuChiTimTinh = "";
    private String getTieuChiTimQuan = "";
    private Integer  giatroMax = 0;
    private String isMaxPrice = "0";
    private String tieuChitimLoaiTro = "";

    private void eventHande(){
        SharedPreferences preferences = getActivity().getSharedPreferences("Owner", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // Save the access token and phone number
        editor.putString("tieuchitimtinh", tieuChiTimTinh);
        editor.putString("tieuchitimquan", getTieuChiTimQuan);
        editor.putString("maxprice", giatroMax + "");
        editor.putString("ismaxprice", isMaxPrice + "");
        editor.putString("loainhatieuchitim", tieuChitimLoaiTro);
        editor.apply();
        eventHandelTimKiem();


    }

    private void eventHandelTimKiem(){
        tieuChiTimTinh = (tieuChiTimTinh != null) ? tieuChiTimTinh : "";
        getTieuChiTimQuan = (getTieuChiTimQuan != null) ? getTieuChiTimQuan : "";
        tieuChitimLoaiTro = (tieuChitimLoaiTro != null) ? tieuChitimLoaiTro : "";

        if (giatroMax == null) {
            giatroMax = 0;
        }

        if(tieuChiTimTinh.equals("Chọn Tỉnh")){
            tieuChiTimTinh = "";
        }
        if(getTieuChiTimQuan.equals("Chọn Quận")){
            getTieuChiTimQuan = "";
        }

        if(!tieuChiTimTinh.isEmpty() || !getTieuChiTimQuan.isEmpty() || !tieuChitimLoaiTro.isEmpty() || giatroMax != 0){


            List<BaiViet> baiVietList1 = new ArrayList<>();
            for(BaiViet baiViet : baiVietList){

                if (!tieuChiTimTinh.isEmpty() && !getTieuChiTimQuan.isEmpty() && giatroMax > 0 && !tieuChitimLoaiTro.isEmpty()) {
                    // Tất cả các item đều có giá trị không rỗng và giatroMax > 0
                    if(
                            baiViet.getRoom().getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())
                                    && baiViet.getRoom().getAddress().getDistrict().toLowerCase().equals(getTieuChiTimQuan.toLowerCase())
                                    && baiViet.getRoom().getPrice() <= giatroMax
                                    && tieuChitimLoaiTro.equals(baiViet.getRoom().getTypehouse().get_id())
                    ){
                        baiVietList1.add(baiViet);
                    }
                } else if (!tieuChiTimTinh.isEmpty() && !getTieuChiTimQuan.isEmpty() && !tieuChitimLoaiTro.isEmpty()) {
                    // Xử lý khi chỉ có tiêu chí tìm tỉnh, quận và giá trị tối đa
                    if(
                            baiViet.getRoom().getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())
                                    && baiViet.getRoom().getAddress().getDistrict().toLowerCase().equals(getTieuChiTimQuan.toLowerCase())
                                    && tieuChitimLoaiTro.equals(baiViet.getRoom().getTypehouse().get_id())
                    ){
                        baiVietList1.add(baiViet);
                    }
                }else if (!tieuChiTimTinh.isEmpty() && !getTieuChiTimQuan.isEmpty() && giatroMax > 0) {
                    // Xử lý khi chỉ có tiêu chí tìm tỉnh, quận và giá trị tối đa
                    if(
                            baiViet.getRoom().getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())
                                    && baiViet.getRoom().getAddress().getDistrict().toLowerCase().equals(getTieuChiTimQuan.toLowerCase())
                                    && baiViet.getRoom().getPrice() <= giatroMax
                    ){
                        baiVietList1.add(baiViet);
                    }
                } else if (!tieuChiTimTinh.isEmpty() && !getTieuChiTimQuan.isEmpty()) {
                    // Chỉ xử lý tiêu chí tìm tỉnh và quận
                    if(
                            baiViet.getRoom().getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())
                                    && baiViet.getRoom().getAddress().getDistrict().toLowerCase().equals(getTieuChiTimQuan.toLowerCase())

                    ){
                        baiVietList1.add(baiViet);
                    }
                } else if (giatroMax > 0 && !tieuChitimLoaiTro.isEmpty()) {
                    // Chỉ xử lý giá trị tối đa và loại trọ
                    if(
                            baiViet.getRoom().getPrice() <= giatroMax
                                    && tieuChitimLoaiTro.equals(baiViet.getRoom().getTypehouse().get_id())
                    ){
                        baiVietList1.add(baiViet);
                    }
                } else if (!tieuChiTimTinh.isEmpty() && !tieuChitimLoaiTro.isEmpty()) {
                    if(
                            baiViet.getRoom().getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())
                                    && tieuChitimLoaiTro.equals(baiViet.getRoom().getTypehouse().get_id())
                    ){
                        baiVietList1.add(baiViet);
                    }
                } else if (!tieuChiTimTinh.isEmpty() && giatroMax > 0) {
                    if(
                            baiViet.getRoom().getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())
                                    && baiViet.getRoom().getPrice() <= giatroMax
                    ){
                        baiVietList1.add(baiViet);
                    }
                }else if (!tieuChiTimTinh.isEmpty()) {
                    if(
                            baiViet.getRoom().getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())
                    ){
                        baiVietList1.add(baiViet);
                    }
                }else if (giatroMax > 0) {
                    if(
                            baiViet.getRoom().getPrice() <= giatroMax
                    ){
                        baiVietList1.add(baiViet);
                    }
                } else if (!tieuChitimLoaiTro.isEmpty()) {
                    if(
                            tieuChitimLoaiTro.equals(baiViet.getRoom().getTypehouse().get_id())
                    ){
                        baiVietList1.add(baiViet);
                    }
                } else {
                    // Xử lý trường hợp khác
                }

            }
            baiVietAdapter.updateList(baiVietList1);

// timNguoiOGhepList findRoomHouseResponseList
            List<TimNguoiOGhep> timNguoiOGhepList1 = new ArrayList<>();
            for (TimNguoiOGhep timNguoiOGhep: timNguoiOGhepList){

                if(!tieuChiTimTinh.isEmpty() && !getTieuChiTimQuan.isEmpty() && giatroMax > 1  ){
                    if(timNguoiOGhep.getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())
                            && timNguoiOGhep.getAddress().getDistrict().toLowerCase().equals(getTieuChiTimQuan.toLowerCase())
                            && timNguoiOGhep.getPrice() <= giatroMax

                    ){
                        timNguoiOGhepList1.add(timNguoiOGhep);
                    }

                }else if(!tieuChiTimTinh.isEmpty() && !getTieuChiTimQuan.isEmpty()){
                    if(timNguoiOGhep.getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())
                            && timNguoiOGhep.getAddress().getDistrict().toLowerCase().equals(getTieuChiTimQuan.toLowerCase())
                    ){
                        timNguoiOGhepList1.add(timNguoiOGhep);
                    }

                } else
                if(!tieuChiTimTinh.isEmpty()  && giatroMax > 1){
                    if(timNguoiOGhep.getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())
                            && timNguoiOGhep.getPrice() <= giatroMax
                    ){
                        timNguoiOGhepList1.add(timNguoiOGhep);
                    }
                } else

                if(!tieuChiTimTinh.isEmpty()){
                    if(timNguoiOGhep.getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())
                    ){
                        timNguoiOGhepList1.add(timNguoiOGhep);
                    }

                }
            }
            timNguoiOGhepAdapter.updateList(timNguoiOGhepList1);

            List<FindRoomHouseResponse> findRoomHouseResponses = new ArrayList<>();
            for (FindRoomHouseResponse findRoomHouseResponse : findRoomHouseResponseList){
                if (!tieuChiTimTinh.isEmpty() && !getTieuChiTimQuan.isEmpty() && giatroMax > 1 && !tieuChitimLoaiTro.isEmpty()) {
                    if(findRoomHouseResponse.getAddress().contains(tieuChiTimTinh)
                            && findRoomHouseResponse.getAddress().contains(getTieuChiTimQuan)
                            && findRoomHouseResponse.getMaxPrice() <=        giatroMax
                            &&        tieuChitimLoaiTro.equals(findRoomHouseResponse.getTypehouse().get_id())
                    ){
                        findRoomHouseResponses.add(findRoomHouseResponse);
                    }
                } else if (!tieuChiTimTinh.isEmpty() && !getTieuChiTimQuan.isEmpty() && giatroMax > 1) {
                    if(findRoomHouseResponse.getAddress().contains(tieuChiTimTinh)
                            && findRoomHouseResponse.getAddress().contains(getTieuChiTimQuan)
                            && findRoomHouseResponse.getMaxPrice() <=        giatroMax

                    ){
                        findRoomHouseResponses.add(findRoomHouseResponse);
                    }
                }
                else if (!tieuChiTimTinh.isEmpty() && !getTieuChiTimQuan.isEmpty() && !tieuChitimLoaiTro.isEmpty()) {
                    if(findRoomHouseResponse.getAddress().contains(tieuChiTimTinh)
                            && findRoomHouseResponse.getAddress().contains(getTieuChiTimQuan)
                            &&        tieuChitimLoaiTro.equals(findRoomHouseResponse.getTypehouse().get_id())
                    ){
                        findRoomHouseResponses.add(findRoomHouseResponse);
                    }

                } else if (!tieuChiTimTinh.isEmpty() && !getTieuChiTimQuan.isEmpty()) {
                    if(findRoomHouseResponse.getAddress().contains(tieuChiTimTinh)
                            && findRoomHouseResponse.getAddress().contains(getTieuChiTimQuan)
                    ){
                        findRoomHouseResponses.add(findRoomHouseResponse);
                    }

                } else if (giatroMax > 0 && !tieuChitimLoaiTro.isEmpty()) {
                    if(
                            findRoomHouseResponse.getMaxPrice() <=        giatroMax
                                    &&        tieuChitimLoaiTro.equals(findRoomHouseResponse.getTypehouse().get_id())
                    ){
                        findRoomHouseResponses.add(findRoomHouseResponse);
                    }

                } else if (!tieuChiTimTinh.isEmpty() && !tieuChitimLoaiTro.isEmpty()) {
                    if(findRoomHouseResponse.getAddress().contentEquals(tieuChiTimTinh)
                            && findRoomHouseResponse.getMaxPrice() <=        giatroMax
                            &&        tieuChitimLoaiTro.equals(findRoomHouseResponse.getTypehouse().get_id())

                    ){
                        findRoomHouseResponses.add(findRoomHouseResponse);
                    }
                } else if (!tieuChiTimTinh.isEmpty() && giatroMax > 1) {
                    if(findRoomHouseResponse.getAddress().contentEquals(tieuChiTimTinh)
                            && findRoomHouseResponse.getMaxPrice() <=        giatroMax
                    ){
                        findRoomHouseResponses.add(findRoomHouseResponse);
                    }
                }else if (!tieuChiTimTinh.isEmpty()) {
                    if(findRoomHouseResponse.getAddress().contentEquals(tieuChiTimTinh)
                    ){
                        findRoomHouseResponses.add(findRoomHouseResponse);
                    }
                }else if (giatroMax > 1) {
                    if( findRoomHouseResponse.getMaxPrice() <=        giatroMax
                    ){
                        findRoomHouseResponses.add(findRoomHouseResponse);
                    }
                } else if (!tieuChitimLoaiTro.isEmpty()) {
                    if(tieuChitimLoaiTro.equals(findRoomHouseResponse.getTypehouse().get_id())
                    ){
                        findRoomHouseResponses.add(findRoomHouseResponse);
                    }
                } else {
                    // Xử lý trường hợp khác
                }
            }
            findRoomHouseAdapter.updateList(findRoomHouseResponses);
        }


    }

    TextView modal_tieuchitim;
    EditText priceEditText;
    RadioButton highestPriceRadioButton;
    RadioButton lowestPriceRadioButton;
    RecyclerView roomTypeRecyclerView;
    private void handleEvent(){
        listProvicense = new ArrayList<>();
        districtList = new ArrayList<>();
        loaiNhaList = new ArrayList<>();
        click_tieuchitimphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the custom layout
                View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.modal_bo_loc_tim_kiem, null);

                // Create the AlertDialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setView(dialogView);
                builder.setTitle("Bộ lọc tìm kiếm");


                modal_tieuchitim = dialogView.findViewById(R.id.modal_tieuchitim);
                Spinner provincesSpinner = dialogView.findViewById(R.id.modal_timphong_provinces_phongtromuontim);
                Spinner districtSpinner = dialogView.findViewById(R.id.modal_timphong_district_phongtromuontim);
                priceEditText = dialogView.findViewById(R.id.modal_timphong_editText_giatromuontim);
                RadioGroup radioGroup = dialogView.findViewById(R.id.modal_timphong_radioGroup);
                highestPriceRadioButton = dialogView.findViewById(R.id.modal_timphong_radioHighestPrice);
                lowestPriceRadioButton = dialogView.findViewById(R.id.modal_timphong_radioLowestPrice);
                RadioGroup roomTypeRadioGroup = dialogView.findViewById(R.id.modal_timphong_radioGroup1);
                roomTypeRecyclerView = dialogView.findViewById(R.id.modal_timphong_loaiphongmuontim_recycleview);


                GridLayoutManager layoutManager1 = new GridLayoutManager(getContext(), 3);
                roomTypeRecyclerView.setLayoutManager(layoutManager1);
                loaiNhaAdapter = new LoaiNhaAdapter(getActivity(), loaiNhaList );
                roomTypeRecyclerView.setAdapter(loaiNhaAdapter);



                SharedPreferences preferences = getActivity().getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values from SharedPreferences



                modal_tieuchitim.setText(tieuChiTimTinh + "-" + getTieuChiTimQuan);
                priceEditText.setText(giatroMax + "");
                if(isMaxPrice.equals("1")){
                    highestPriceRadioButton.setChecked(true);
                }
                if(isMaxPrice.equals("2")){
                    lowestPriceRadioButton.setChecked(true);
                }


                for (LoaiNha in: loaiNhaList){
                    if(in.get_id().equals(tieuChitimLoaiTro)){
                        in.set__v(1);
                        break;
                    }
                }
                loaiNhaAdapter.notifyDataSetChanged();


if(loaiNhaList.size() == 0){
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

}
                ApiAddress.apiDriverTrip.getListProvices().enqueue(new Callback<List<Provinces>>() {
                    @Override
                    public void onResponse(Call<List<Provinces>> call, Response<List<Provinces>> response) {
                        listProvicense =  response.body();
                        listProvicense.add(0, new Provinces("Chọn Tỉnh", -1));
                        ProvincesAdapter provincesAdapter = new ProvincesAdapter(getContext(), listProvicense);
                        provincesSpinner.setAdapter(provincesAdapter);
                        Log.d("OK","OK");
                    }

                    @Override
                    public void onFailure(Call<List<Provinces>> call, Throwable t) {

                    }
                });
                provincesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                                DistrictAdapter prcesAdapter = new DistrictAdapter(getContext(), districtList);
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





                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            tieuChiTimTinh = provincesSpinner.getSelectedItem().toString();
                            getTieuChiTimQuan = districtSpinner.getSelectedItem().toString();
                            if (priceEditText.getText().toString().equals("") || priceEditText.getText().toString() == null) {
                                giatroMax = 0;
                            } else {
                                giatroMax = Integer.parseInt(priceEditText.getText().toString());
                            }
                            if (highestPriceRadioButton.isChecked()) {
                                isMaxPrice = "1";
                            }
                            if (lowestPriceRadioButton.isChecked()) {
                                isMaxPrice = "2";
                            }
                            String okln = "";
                            for(LoaiNha loaiNha: loaiNhaList){
                                if(loaiNha.get__v() == 1){
                                    okln = loaiNha.get_id();
                                    break;
                                }
                            }
                            tieuChitimLoaiTro = okln;

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("Error", "An error occurred: " + e.getMessage());
                        }

                        eventHande();



                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle Cancel button click
                        dialog.dismiss(); // Dismiss the dialog
                    }
                });

                // Show the dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    private String token;
    private  String role;
    private  String phoneOwner;
    private List<String> quanCuaThanhPho;
    private Khamdapter khamdapter;
    private List<BaiViet> baiVietList;
    private BaiVietAdapter baiVietAdapter;
    private List<FindRoomHouseResponse> findRoomHouseResponseList;
    private FindRoomHouseAdapter findRoomHouseAdapter;

    private List<TimNguoiOGhep> timNguoiOGhepList;
    private TimNguoiOGhepAdapter timNguoiOGhepAdapter;



    private void getData(){



        ApiTimNguoiOGhep.apiApiTimNguoiOGhep.getAllTimNguoiOGheps(token).enqueue(new Callback<List<TimNguoiOGhep>>() {
            @Override
            public void onResponse(Call<List<TimNguoiOGhep>> call, Response<List<TimNguoiOGhep>> response) {

                timNguoiOGhepList.addAll(response.body());
                if(tieuChiTimTinh.isEmpty()){
                    timNguoiOGhepAdapter.notifyDataSetChanged();
                }else {
                    List<TimNguoiOGhep> list = new ArrayList<>();
                    for (TimNguoiOGhep timNguoiOGhep : timNguoiOGhepList){
                        if(timNguoiOGhep.getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())){
                            list.add(timNguoiOGhep);
                        }
                    }
                    timNguoiOGhepAdapter.updateList(list);
                }

            }

            @Override
            public void onFailure(Call<List<TimNguoiOGhep>> call, Throwable t) {

            }
        });
        ApiPostFindHouse.apiApiPostFindHouse.getAllFindRoomHouses(token).enqueue(new Callback<List<FindRoomHouseResponse>>() {
            @Override
            public void onResponse(Call<List<FindRoomHouseResponse>> call, Response<List<FindRoomHouseResponse>> response) {




                findRoomHouseResponseList.addAll(response.body());
                if(tieuChiTimTinh.isEmpty()){
                    findRoomHouseAdapter.notifyDataSetChanged();
                }else {
                    List<FindRoomHouseResponse> list = new ArrayList<>();
                    for (FindRoomHouseResponse timNguoiOGhep : findRoomHouseResponseList){
                        if(timNguoiOGhep.getAddress().contains(tieuChiTimTinh)){
                            list.add(timNguoiOGhep);
                        }
                    }
                    findRoomHouseAdapter.updateList(list);
                }


            }

            @Override
            public void onFailure(Call<List<FindRoomHouseResponse>> call, Throwable t) {

            }
        });
        ApiBaiDang.apiBaiDang.getallBaiDang(token).enqueue(new Callback<List<BaiViet>>() {
            @Override
            public void onResponse(Call<List<BaiViet>> call, Response<List<BaiViet>> response) {
                if (response.isSuccessful()) {
                    // Xử lý khi response thành công


                    baiVietList.addAll(response.body());

                    if(tieuChiTimTinh.isEmpty()){
                        baiVietAdapter.notifyDataSetChanged();
                    }else {
                        List<BaiViet> list = new ArrayList<>();
                        for (BaiViet timNguoiOGhep : baiVietList){
                            if(timNguoiOGhep.getRoom().getAddress().getCity().toLowerCase().equals(tieuChiTimTinh.toLowerCase())){
                                list.add(timNguoiOGhep);
                            }
                        }
                        baiVietAdapter.updateList(list);
                    }


                    for(BaiViet baiViet : baiVietList){
                        String quan = baiViet.getRoom().getAddress().getDistrict();
                        if(!quanCuaThanhPho.contains(quan)){
                            quanCuaThanhPho.add(quan);
                        }
                    }
                    khamdapter.notifyDataSetChanged();

                } else {
                    // Xử lý khi response không thành công (ví dụ: server trả về lỗi)
                    Log.e("API Response", "Error: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<BaiViet>> call, Throwable t) {
                // Xử lý khi request thất bại (ví dụ: không có kết nối internet)
                Log.e("API Request", "Failure: " + t.getMessage(), t);
            }
        });

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_trang_chu, container, false);
        SharedPreferences preferences =  getActivity().getSharedPreferences("Owner", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        role = preferences.getString("role", "");
        phoneOwner = preferences.getString("sdt", "");
        init(view);
        handleEvent();
        quanCuaThanhPho = new ArrayList<>();
        baiVietList = new ArrayList<>();
        findRoomHouseResponseList = new ArrayList<>();
        timNguoiOGhepList = new ArrayList<>();


        String tieuChiTimTinh_ = preferences.getString("tieuchitimtinh", "");
        tieuChiTimTinh = tieuChiTimTinh_;
        String tieuChiTimQuan_ = preferences.getString("tieuchitimquan", "");
        getTieuChiTimQuan = tieuChiTimQuan_;

        String maxPrice = preferences.getString("maxprice", "0");
        giatroMax = Integer.parseInt(maxPrice);
        String tieuChiTimLoaiTro = preferences.getString("loainhatieuchitim", "");
        tieuChitimLoaiTro = tieuChiTimLoaiTro;

        String isMaxPrice_ = preferences.getString("ismaxprice", "0");
        isMaxPrice = isMaxPrice_;
        getData();
        // KHÁM PHÁ

        khamdapter = new Khamdapter(getContext() ,quanCuaThanhPho, token);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        listimagekhampha_recycle.setLayoutManager(layoutManager);
        listimagekhampha_recycle.setAdapter(khamdapter);

        // BÀI VIẾT
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManagerBaiViet = new GridLayoutManager(getContext(), spanCount);
        listbaivietnoibat_recycle.setLayoutManager(layoutManagerBaiViet);
        baiVietAdapter = new BaiVietAdapter(  baiVietList,getContext(),role, token, "", phoneOwner, "");
        listbaivietnoibat_recycle.setAdapter(baiVietAdapter);

        // TÌM TRỌ
        GridLayoutManager layoutManagerBaiVietTIMTRO = new GridLayoutManager(getContext(), spanCount);
        listbaiviettimtro_recycle.setLayoutManager(layoutManagerBaiVietTIMTRO);
        findRoomHouseAdapter = new FindRoomHouseAdapter(getContext(),findRoomHouseResponseList,token, role, "");
        listbaiviettimtro_recycle.setAdapter(findRoomHouseAdapter);


        // TÌM NGƯỜI Ở GHÉP
        // TÌM NGƯỜI Ở GHÉP
        GridLayoutManager layoutManagerBaiVietTIMnguoighep = new GridLayoutManager(getContext(), spanCount);
        listbaiviettimnguoioghep_recycle.setLayoutManager(layoutManagerBaiVietTIMnguoighep);
// Use timNguoiOGhepAdapter instead of findRoomHouseAdapter
        timNguoiOGhepAdapter = new TimNguoiOGhepAdapter(getContext(), timNguoiOGhepList, token, role, "");
        listbaiviettimnguoioghep_recycle.setAdapter(timNguoiOGhepAdapter);
        if(baiVietList.size() != 0 && timNguoiOGhepList.size() != 0 && findRoomHouseResponseList.size()!= 0){
            eventHandelTimKiem();
        }

        return view;
    }
}