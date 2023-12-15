package com.example.rentappandroid.Fragment.Landlord;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rentappandroid.R;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_trang_chu, container, false);
        init(view);
        handleEvent();
        return view;
    }
}