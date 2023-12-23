package com.example.rentappandroid.Fragment.Landlord.QuanLyPhongTro;

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

import com.example.rentappandroid.Adapter.Landlord.RoomAdapter;
import com.example.rentappandroid.Adapter.Landlord.ToaNhaAdapter;
import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Dto.Reponse.RoomingHouseComplex;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.R;
import com.example.rentappandroid.Utils.MultiSelectionSpinner;
import com.example.rentappandroid.api.ApiBaiDang;
import com.example.rentappandroid.api.ApiRoomHouse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListPhongTroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPhongTroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListPhongTroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListPhongTroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListPhongTroFragment newInstance(String param1, String param2) {
        ListPhongTroFragment fragment = new ListPhongTroFragment();
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

    private RecyclerView recyclerView;
    private RoomAdapter roomAdapter;
    private List<Room> roomList;
    private String token;
    private String phoneOwner;
    private String nameOwner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_phong_tro, container, false);
        recyclerView = view.findViewById(R.id.listphongtro_recycleview);

        SharedPreferences preferences =  getActivity().getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        nameOwner = preferences.getString("name", "");  // Replace "" with the default value if not found

        roomList = new ArrayList<>();
        // Set up the RecyclerView with a GridLayoutManage r
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        roomAdapter = new RoomAdapter( getContext(), roomList, token);
        recyclerView.setAdapter(roomAdapter);
        getData();
        handleButton(view);
        return view;
    }

    private void handleButton(View view) {
        // Inside your Activity's onCreate or any other method
        Button allButton = view.findViewById(R.id.ALL);
        Button rentedButton = view.findViewById(R.id.RENTED);
        Button emptyRoomButton = view.findViewById(R.id.EMPTYROOM);
        Button maintenanceButton = view.findViewById(R.id.MAINTENANCE);

// Now you can use these button objects as needed, for example, set click listeners
        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomAdapter.setRoomList(roomList);
                roomAdapter.notifyDataSetChanged();
            }
        });

        rentedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Room> list = new ArrayList<>();
                for (Room room: roomList){
                    if(room.getStatus().equals("RENTED")){
                        list.add(room);
                    }
                }
                roomAdapter.setRoomList(list);
                roomAdapter.notifyDataSetChanged();
            }
        });

        emptyRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Room> list = new ArrayList<>();
                for (Room room: roomList){
                    if(room.getStatus().equals("EMPTYROOM")){
                        list.add(room);
                    }
                }
                roomAdapter.setRoomList(list);
                roomAdapter.notifyDataSetChanged();
            }
        });

        maintenanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Room> list = new ArrayList<>();
                for (Room room: roomList){
                    if(room.getStatus().equals("MAINTENANCE")){
                        list.add(room);
                    }
                }
                roomAdapter.setRoomList(list);
                roomAdapter.notifyDataSetChanged();
            }
        });

    }

    private void getData() {


        ApiRoomHouse.apiRoom.getListRoomByOwner(phoneOwner,token).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                roomList.clear();
                roomList.addAll(response.body());
                roomAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });
    }
}