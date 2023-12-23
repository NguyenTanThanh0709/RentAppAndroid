package com.example.rentappandroid.Fragment.Landlord;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Activity.Landlord.FORMADD.FormAddRoomHouseActivity;
import com.example.rentappandroid.Activity.Landlord.FORMADD.FormBillActivity;
import com.example.rentappandroid.Activity.Landlord.FORMADD.FormContractActivity;
import com.example.rentappandroid.Activity.Landlord.FORMADD.FormPostActivity;
import com.example.rentappandroid.Activity.Landlord.FORMADD.FormTenantActivity;
import com.example.rentappandroid.Activity.Landlord.FORMADD.FormToaNhaActivity;
import com.example.rentappandroid.Activity.Landlord.FORMLIST.QuanLtBaiDangActivity;
import com.example.rentappandroid.Activity.Landlord.FORMLIST.QuanLyHopDongActivity;
import com.example.rentappandroid.Activity.Landlord.FORMLIST.QuanLyNguoiThue;
import com.example.rentappandroid.Activity.Landlord.FORMLIST.QuanLyPhongTroActivity;
import com.example.rentappandroid.Activity.LoginActivity;
import com.example.rentappandroid.Activity.MainActivity;
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

        quanlyphongtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuanLyPhongTroActivity.class);
                startActivity(intent);
            }
        });

        quanlybaidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuanLtBaiDangActivity.class);
                startActivity(intent);
            }
        });

        quanlynguoithue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuanLyNguoiThue.class);
                startActivity(intent);
            }
        });

        quanlyhopdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuanLyHopDongActivity.class);
                startActivity(intent);
            }
        });

        quanlyhoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuanLyHopDongActivity.class);

                intent.putExtra("type", "hoadonlist");
                startActivity(intent);
            }
        });

        baocaosuco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListIssueActivity.class);
                startActivity(intent);
            }
        });

        quanlydichvuchung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for quanlydichvuchung
                // Add your code here
            }
        });

        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity
                getActivity().finish();

                // Clear the user information from SharedPreferences
                SharedPreferences preferences = getActivity().getSharedPreferences("Owner", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                if (preferences.contains("token") && preferences.contains("sdt")) {
                    editor.remove("token");
                    editor.remove("sdt");
                    editor.remove("name");
                    editor.remove("role");
                    editor.apply();
                }

                // Restart LoginActivity and show the role selection dialog
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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