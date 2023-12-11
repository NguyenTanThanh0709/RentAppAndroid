package com.example.rentappandroid.Fragment.Landlord.QuanLyBaiDang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rentappandroid.Adapter.BaiVietAdapter;
import com.example.rentappandroid.Adapter.Landlord.RoomAdapter;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiBaiDang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListAllBaiDangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListAllBaiDangFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListAllBaiDangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListAllBaiDangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListAllBaiDangFragment newInstance(String param1, String param2) {
        ListAllBaiDangFragment fragment = new ListAllBaiDangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    String id ;
    String phone ;
    String token ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
             id = getArguments().getString("id");
             phone = getArguments().getString("phone");
             token = getArguments().getString("token");
        }
    }

    private RecyclerView recyclerView;
    private BaiVietAdapter baiVietAdapter;
    private List<BaiViet> postList;

    private void get(){

        if(id.equals("null")){
            ApiBaiDang.apiBaiDang.getallBaiDangByOwner(phone, token).enqueue(new Callback<List<BaiViet>>() {
                @Override
                public void onResponse(Call<List<BaiViet>> call, Response<List<BaiViet>> response) {
                    if (response.isSuccessful()) {
                        postList.addAll(response.body());
                    } else {
                        // Handle unsuccessful response
                        Log.e("API Error", "Unsuccessful response. Code: " + response.code());
                        // You can also log response.errorBody() for more details
                        try {
                            Log.e("API Error", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<BaiViet>> call, Throwable t) {
                    // Handle failure
                    Log.e("API Error", "Request failed: " + t.getMessage(), t);
                }
            });

        }else {
            ApiBaiDang.apiBaiDang.getallBaiDangByIdRoom(id,token).enqueue(new Callback<List<BaiViet>>() {
                @Override
                public void onResponse(Call<List<BaiViet>> call, Response<List<BaiViet>> response) {
                    postList.addAll(response.body())  ;
                }

                @Override
                public void onFailure(Call<List<BaiViet>> call, Throwable t) {

                }
            });
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_list_all_bai_dang, container, false);
        postList =new ArrayList<>();

        recyclerView = view.findViewById(R.id.listbaidang_recycleview);
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        baiVietAdapter = new BaiVietAdapter(  postList,getContext());
        recyclerView.setAdapter(baiVietAdapter);

        get();

        return view;
    }
}