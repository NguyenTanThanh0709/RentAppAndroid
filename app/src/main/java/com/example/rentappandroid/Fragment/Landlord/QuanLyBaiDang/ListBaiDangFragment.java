package com.example.rentappandroid.Fragment.Landlord.QuanLyBaiDang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rentappandroid.Adapter.BaiVietAdapter;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiBaiDang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListBaiDangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListBaiDangFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListBaiDangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListBaiDangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListBaiDangFragment newInstance(String param1, String param2) {
        ListBaiDangFragment fragment = new ListBaiDangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    String id ;
    String phone ;
    String token ;
    String phoneOwner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString("id");
        }
    }

    private RecyclerView recyclerView;
    private BaiVietAdapter baiVietAdapter;
    private List<BaiViet> postList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_bai_dang, container, false);
        postList =new ArrayList<>();

        SharedPreferences preferences =  getActivity().getSharedPreferences("Owner", Context.MODE_PRIVATE);

        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        String role = preferences.getString("role", "");
        recyclerView = view.findViewById(R.id.listbaidang_recycleview);
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        baiVietAdapter = new BaiVietAdapter(  postList,getContext(),role, token);
        recyclerView.setAdapter(baiVietAdapter);


        getData();

        return view;
    }

    private void getData() {

        if(id.equals("null")){



        ApiBaiDang.apiBaiDang.getallBaiDangByOwner(phoneOwner, token).enqueue(new Callback<List<BaiViet>>() {
            @Override
            public void onResponse(Call<List<BaiViet>> call, Response<List<BaiViet>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().isEmpty()) {
                        postList.clear();
                        postList.addAll(response.body());
                        baiVietAdapter.notifyDataSetChanged();
                        // Notify the adapter or update the UI if needed
                    } else {
                        // Handle the case where the response body is empty
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("ApiBaiDang", "Unsuccessful response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<BaiViet>> call, Throwable t) {
                Log.e("ApiBaiDang", "API call failed", t);
            }
        });
    }else {
            ApiBaiDang.apiBaiDang.getallBaiDangByIdRoom(id,token).enqueue(new Callback<List<BaiViet>>() {
                @Override
                public void onResponse(Call<List<BaiViet>> call, Response<List<BaiViet>> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null && !response.body().isEmpty()) {
                            postList.clear();
                            postList.addAll(response.body());
                            baiVietAdapter.notifyDataSetChanged();
                            // Notify the adapter or update the UI if needed
                        } else {
                            // Handle the case where the response body is empty
                        }
                    } else {
                        // Handle unsuccessful response
                        Log.e("ApiBaiDang", "Unsuccessful response: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<BaiViet>> call, Throwable t) {

                }
            });
        }
    }

}