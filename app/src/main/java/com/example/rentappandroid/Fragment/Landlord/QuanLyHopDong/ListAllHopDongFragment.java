package com.example.rentappandroid.Fragment.Landlord.QuanLyHopDong;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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
import android.widget.Button;

import com.example.rentappandroid.Adapter.BaiVietAdapter;
import com.example.rentappandroid.Adapter.Landlord.LeaseContractAdapter;
import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiHopDong;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListAllHopDongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListAllHopDongFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListAllHopDongFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListAllHopDongFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListAllHopDongFragment newInstance(String param1, String param2) {
        ListAllHopDongFragment fragment = new ListAllHopDongFragment();
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
            id = getArguments().getString("id");
            type = getArguments().getString("type");
        }
    }
    String id ;
    String phone ;
    String token ;
    String phoneOwner;
    String type;
    private RecyclerView recyclerView;
    private LeaseContractAdapter leaseContractAdapter;
    private List<Leasecontracts> leasecontracts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_all_hop_dong, container, false);
        leasecontracts =new ArrayList<>();

        SharedPreferences preferences =  getActivity().getSharedPreferences("Owner", Context.MODE_PRIVATE);

        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        String role = preferences.getString("role", "");
        recyclerView = view.findViewById(R.id.listhopdong_recycleview);
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        leaseContractAdapter = new LeaseContractAdapter( getContext() ,leasecontracts,role, token, type);
        recyclerView.setAdapter(leaseContractAdapter);


        getData();
        handleButton(view);
        return view;
    }

    private void handleButton(View view) {
        // Inside your Activity's onCreate or any other method
        Button allButton = view.findViewById(R.id.ALLHOPCONHAN);
        Button rentedButton = view.findViewById(R.id.ALLHOPDONGHETHAN);


        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Leasecontracts> list = new ArrayList<>();
                for (Leasecontracts room: leasecontracts){
                    if(room.isStatus()){
                        list.add(room);
                    }
                }
                leaseContractAdapter.setRoomList(list);
                leaseContractAdapter.notifyDataSetChanged();
            }
        });

        rentedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Leasecontracts> list = new ArrayList<>();
                for (Leasecontracts room: leasecontracts){
                    if(!room.isStatus()){
                        list.add(room);
                    }
                }
                leaseContractAdapter.setRoomList(list);
                leaseContractAdapter.notifyDataSetChanged();
            }
        });



    }

    private void getData() {
        if (id.equals("null")) {
            ApiHopDong.apiHopDong.getallleasecontractByOwner(phoneOwner, token).enqueue(new Callback<List<Leasecontracts>>() {
                @Override
                public void onResponse(Call<List<Leasecontracts>> call, Response<List<Leasecontracts>> response) {
                    if (response.isSuccessful()) {
                        // API call successful, add data to the list
                        leasecontracts.clear();
                        leasecontracts.addAll(response.body());
                        leaseContractAdapter.notifyDataSetChanged();
                    } else {
                        // Log error if the API call is not successful
                        Log.e(TAG, "API call failed with code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Leasecontracts>> call, Throwable t) {
                    // Log error if the API call fails
                    Log.e(TAG, "API call failed", t);
                }
            });
        } else {
            ApiHopDong.apiHopDong.getallleasecontractByIdRoom(id, token).enqueue(new Callback<List<Leasecontracts>>() {
                @Override
                public void onResponse(Call<List<Leasecontracts>> call, Response<List<Leasecontracts>> response) {
                    if (response.isSuccessful()) {
                        // API call successful, add data to the list
                        leasecontracts.clear();
                        leasecontracts.addAll(response.body());
                        leaseContractAdapter.notifyDataSetChanged();
                    } else {
                        // Log error if the API call is not successful
                        Log.e(TAG, "API call failed with code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Leasecontracts>> call, Throwable t) {
                    // Log error if the API call fails
                    Log.e(TAG, "API call failed", t);
                }
            });
        }
    }

}