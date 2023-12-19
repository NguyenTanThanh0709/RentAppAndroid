package com.example.rentappandroid.Fragment.Landlord;

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

import com.example.rentappandroid.Adapter.IssueAdapter;
import com.example.rentappandroid.Adapter.NotificationAdapter;
import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.FireBase.FirebaseHelper;
import com.example.rentappandroid.Model.Notification;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiLandrod;
import com.example.rentappandroid.api.ApiTenant;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DichVuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DichVuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DichVuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DichVuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DichVuFragment newInstance(String param1, String param2) {
        DichVuFragment fragment = new DichVuFragment();
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
        firebaseHelper = new FirebaseHelper();
    }


    private Notification notification;
    private String token;
    private  String role;
    private  String phoneOwner;
    private FirebaseHelper firebaseHelper;
    private List<Notification> notificationList;
    private NotificationAdapter notificationAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dich_vu, container, false);

        SharedPreferences preferences =  getActivity().getSharedPreferences("Owner", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        role = preferences.getString("role", "");
        phoneOwner = preferences.getString("sdt", "");
        notificationList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.listthongbao_recycleview);

        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        notificationAdapter = new NotificationAdapter( getActivity(), notificationList,role,token);
        recyclerView.setAdapter(notificationAdapter);

        if(role.equals("ADMIN")){
            firebaseHelper.getNotificationsByLandlord(phoneOwner, new FirebaseHelper.OnNotificationListReadListener() {
                @Override
                public void onNotificationListRead(List<Notification> notifications) {
                    // Handle the list of notifications
                    notificationList.addAll(notifications);
                    notificationAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFirebaseError(String errorMessage) {
                    // Handle Firebase errors
                    Log.e("FirebaseError", errorMessage);
                }
            });
        }else {
            firebaseHelper.getNotificationsByTenant(phoneOwner, new FirebaseHelper.OnNotificationListReadListener() {
                @Override
                public void onNotificationListRead(List<Notification> notifications) {
                    // Handle the list of notifications
                    notificationList.addAll(notifications);

                    notificationAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFirebaseError(String errorMessage) {
                    // Handle Firebase errors
                    Log.e("FirebaseError", errorMessage);
                }
            });
        }


        return view;
    }

}