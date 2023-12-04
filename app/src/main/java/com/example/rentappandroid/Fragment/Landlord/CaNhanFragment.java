package com.example.rentappandroid.Fragment.Landlord;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rentappandroid.Activity.Landlord.FormAddRoomHouseActivity;
import com.example.rentappandroid.Activity.Landlord.FormBillActivity;
import com.example.rentappandroid.Activity.Landlord.FormContractActivity;
import com.example.rentappandroid.Activity.Landlord.FormPostActivity;
import com.example.rentappandroid.Activity.Landlord.FormTenantActivity;
import com.example.rentappandroid.Activity.Landlord.FormToaNhaActivity;
import com.example.rentappandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaNhanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaNhanFragment extends Fragment {

    private TextView name, sophong, sokhachthue, sophongdachothue, sophongcontrong;
    private LinearLayout taotoanha, taophong, taobaidang, themkhachthue, taohopdong, themhoadon, quanlyphongtro,
            quanlybaidang, quanlynguoithue, quanlyhopdong, quanlyhoadon, baocaosuco, quanlydichvuchung, dangxuat;

    private void init(View view){
        name = view.findViewById(R.id.name);
        sophong = view.findViewById(R.id.sophong);
        sokhachthue = view.findViewById(R.id.sokhachthue);
        sophongdachothue = view.findViewById(R.id.sophongdachothue);
        sophongcontrong = view.findViewById(R.id.sophongcontrong);

        taotoanha = view.findViewById(R.id.taotoanha);
        taophong = view.findViewById(R.id.taophong);
        taobaidang = view.findViewById(R.id.taobaidang);
        themkhachthue = view.findViewById(R.id.themkhachthue);
        taohopdong = view.findViewById(R.id.taohopdong);
        themhoadon = view.findViewById(R.id.themhoadon);

        quanlyphongtro = view.findViewById(R.id.quanlyphongtro);
        quanlybaidang = view.findViewById(R.id.quanlybaidang);
        quanlynguoithue = view.findViewById(R.id.quanlynguoithue);
        quanlyhopdong = view.findViewById(R.id.quanlyhopdong);
        quanlyhoadon = view.findViewById(R.id.quanlyhoadon);
        baocaosuco = view.findViewById(R.id.baocaosuco);
        quanlydichvuchung = view.findViewById(R.id.quanlydichvuchung);
        dangxuat = view.findViewById(R.id.dangxuat);
    }

    private void event(){
        taophong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for taophong
                Intent intent = new Intent(getActivity(), FormAddRoomHouseActivity.class);
                startActivity(intent);
            }
        });

        taobaidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for taophong
                Intent intent = new Intent(getActivity(), FormPostActivity.class);
                startActivity(intent);
            }
        });

        taotoanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for taophong
                Intent intent = new Intent(getActivity(), FormToaNhaActivity.class);
                startActivity(intent);
            }
        });

        themkhachthue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for taophong
                Intent intent = new Intent(getActivity(), FormTenantActivity.class);
                startActivity(intent);
            }
        });

        taohopdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for taophong
                Intent intent = new Intent(getActivity(), FormContractActivity.class);
                startActivity(intent);
            }
        });

        themhoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for taophong
                Intent intent = new Intent(getActivity(), FormBillActivity.class);
                startActivity(intent);
            }
        });
    }

    public CaNhanFragment() {
        // Required empty public constructor
    }


    public static CaNhanFragment newInstance(String param1, String param2) {
        CaNhanFragment fragment = new CaNhanFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        init(view);
        event();

        return view;
    }
}