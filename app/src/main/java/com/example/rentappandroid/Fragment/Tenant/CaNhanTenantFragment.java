package com.example.rentappandroid.Fragment.Tenant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rentappandroid.Activity.LoginActivity;
import com.example.rentappandroid.Activity.Tenant.BaiDang.MainBaiDangActivity;
import com.example.rentappandroid.Activity.Tenant.BaiYeuThich.ListBaiYeuThichActivity;
import com.example.rentappandroid.Activity.Tenant.HopDong.ListHopDongActivity;
import com.example.rentappandroid.Activity.Tenant.TieuChiTim.FormTieuChiTimActivity;
import com.example.rentappandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaNhanTenantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaNhanTenantFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CaNhanTenantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaNhanTenantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaNhanTenantFragment newInstance(String param1, String param2) {
        CaNhanTenantFragment fragment = new CaNhanTenantFragment();
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

    private ImageView imageView3, imageView4, imageView, imageView2;
    private TextView textView, tenantName, textView4, textView3, textView2;
    private LinearLayout quanlyhopdong, quanlybaidang, quanlybaiyeuthich, quanlytieuchitim, hoadon, dangxuat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ca_nhan_tenant, container, false);
        init(view);
        event();
        return view;
    }

    private void init(View view) {
        imageView3 = view.findViewById(R.id.tenant_imageView3);
        imageView4 = view.findViewById(R.id.tenant_imageView4);
        imageView = view.findViewById(R.id.tenant_imageView);
        textView = view.findViewById(R.id.tenant_textView);
        tenantName = view.findViewById(R.id.tenant_name);
        textView4 = view.findViewById(R.id.tenant_textView4);
        textView3 = view.findViewById(R.id.tenant_textView3);

        quanlyhopdong = view.findViewById(R.id.tenant_quanlyhopdong);
        quanlybaidang = view.findViewById(R.id.tenant_quanlybaidang);
        quanlybaiyeuthich = view.findViewById(R.id.tenant_quanlybaiyeuthich);
        quanlytieuchitim = view.findViewById(R.id.tenant_quanlytieuchitim);
        hoadon = view.findViewById(R.id.tenant_hoadon);
        dangxuat = view.findViewById(R.id.tenant_dangxuat);
    }

    private void event(){

        hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getActivity(), ListHoaDonActivity.class));


                Intent intent = new Intent(getActivity(), ListHopDongActivity.class);

                intent.putExtra("type", "bill_");

                startActivity(intent);
            }
        });

        quanlytieuchitim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FormTieuChiTimActivity.class));
            }
        });

        quanlybaiyeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ListBaiYeuThichActivity.class));
            }
        });

        quanlyhopdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ListHopDongActivity.class));
            }
        });
        quanlybaidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainBaiDangActivity.class));
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
}