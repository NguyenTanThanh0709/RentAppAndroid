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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rentappandroid.Adapter.BaiVietAdapter;
import com.example.rentappandroid.Adapter.FindRoomHouseAdapter;
import com.example.rentappandroid.Adapter.Khamdapter;
import com.example.rentappandroid.Adapter.TimNguoiOGhepAdapter;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.Model.FindRoomHouseResponse;
import com.example.rentappandroid.Model.TimNguoiOGhep;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiBaiDang;
import com.example.rentappandroid.api.ApiPostFindHouse;
import com.example.rentappandroid.api.ApiTimNguoiOGhep;

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
    }

    private void handleEvent(){
        click_tieuchitimphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the custom layout
                View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.modal_bo_loc_tim_kiem, null);

                // Create the AlertDialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setView(dialogView);
                builder.setTitle("Bộ lọc tìm kiếm");

                // Set up the dialog buttons or any other customization
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle OK button click
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
        quanCuaThanhPho.add("Quận 1");
        quanCuaThanhPho.add("Quận 2");
        quanCuaThanhPho.add("Quận 3");
        quanCuaThanhPho.add("Quận 4");
        quanCuaThanhPho.add("Quận 5");
        quanCuaThanhPho.add("Quận 6");
        quanCuaThanhPho.add("Quận 7");
        quanCuaThanhPho.add("Quận 8");

        ApiTimNguoiOGhep.apiApiTimNguoiOGhep.getAllTimNguoiOGheps(token).enqueue(new Callback<List<TimNguoiOGhep>>() {
            @Override
            public void onResponse(Call<List<TimNguoiOGhep>> call, Response<List<TimNguoiOGhep>> response) {
                timNguoiOGhepList.addAll(response.body());
                timNguoiOGhepAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TimNguoiOGhep>> call, Throwable t) {

            }
        });

        ApiPostFindHouse.apiApiPostFindHouse.getAllFindRoomHouses(token).enqueue(new Callback<List<FindRoomHouseResponse>>() {
            @Override
            public void onResponse(Call<List<FindRoomHouseResponse>> call, Response<List<FindRoomHouseResponse>> response) {
                findRoomHouseResponseList.addAll(response.body());
                findRoomHouseAdapter.notifyDataSetChanged();
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
                    baiVietAdapter.notifyDataSetChanged();
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
        baiVietAdapter = new BaiVietAdapter(  baiVietList,getContext(),role, token, "", phoneOwner);
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


        return view;
    }
}