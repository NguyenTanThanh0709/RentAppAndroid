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

import com.example.rentappandroid.Adapter.Landlord.ToaNhaAdapter;
import com.example.rentappandroid.Dto.Reponse.RoomReponseComplex;
import com.example.rentappandroid.Dto.Reponse.RoomingHouseComplex;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiRoomingHouseComplex;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListToaNhaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListToaNhaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListToaNhaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListToaNhaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListToaNhaFragment newInstance(String param1, String param2) {
        ListToaNhaFragment fragment = new ListToaNhaFragment();
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
    private ToaNhaAdapter toaNhaAdapter;
    private List<RoomingHouseComplex> roomingHouseComplexList;

    private String token;
    private String phoneOwner;
    private String nameOwner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_list_toa_nha, container, false);
        recyclerView = view.findViewById(R.id.listtoanha_recycleview);

        SharedPreferences preferences =  getActivity().getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        nameOwner = preferences.getString("name", "");  // Replace "" with the default value if not found


        roomingHouseComplexList = new ArrayList<>();
        // Set up the RecyclerView with a GridLayoutManager
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        toaNhaAdapter = new ToaNhaAdapter(roomingHouseComplexList, getContext());
        recyclerView.setAdapter(toaNhaAdapter);
        getData();

        return view;
    }

    private void getData() {
        ApiRoomingHouseComplex.apiRoomingHouseComplex.getListRoomingHouseComplexByOwer(phoneOwner, token).enqueue(new Callback<List<RoomingHouseComplex>>() {
            @Override
            public void onResponse(Call<List<RoomingHouseComplex>> call, Response<List<RoomingHouseComplex>> response) {
                roomingHouseComplexList.clear(); // Clear the existing list
                roomingHouseComplexList.addAll(response.body()); // Add new items from the response
                toaNhaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<RoomingHouseComplex>> call, Throwable t) {
                Log.e("OK", t.toString());
            }
        });
    }

}