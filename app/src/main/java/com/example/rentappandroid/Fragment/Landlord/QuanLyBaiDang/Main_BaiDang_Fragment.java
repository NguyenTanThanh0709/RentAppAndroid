package com.example.rentappandroid.Fragment.Landlord.QuanLyBaiDang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Fragment.Landlord.QuanLyPhongTro.ListPhongTroFragment;
import com.example.rentappandroid.R;
import com.example.rentappandroid.Utils.MultiSelectionSpinner;
import com.example.rentappandroid.api.ApiRoomHouse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Main_BaiDang_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main_BaiDang_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Main_BaiDang_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Main_BaiDang_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Main_BaiDang_Fragment newInstance(String param1, String param2) {
        Main_BaiDang_Fragment fragment = new Main_BaiDang_Fragment();
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


    private List<Room> list_room;
    private String token;
    private String phoneOwner;
    private String nameOwner;
    private void get(){
        ApiRoomHouse.apiRoom.getListRoomByOwner(phoneOwner,token).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                list_room = response.body();
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main__bai_dang_, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences("Owner", Context.MODE_PRIVATE);

// Retrieve values
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        nameOwner = preferences.getString("name", "");  // Replace "" with the default value if not found

        // Find the RelativeLayouts by their IDs
        RelativeLayout layTatCaBaiDang = view.findViewById(R.id.laytatcabaidang);
        RelativeLayout layBaiDangTheoPhongTro = view.findViewById(R.id.laybaidangtheophongtro);
        list_room = new ArrayList<>();

        get();
        layTatCaBaiDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ListBaiDangFragment(), "null", phoneOwner, token);
            }
        });

        layBaiDangTheoPhongTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRoomListDialog();
            }
        });
        return view;
    }

    private void showRoomListDialog() {
        // Prepare the list of room names
        List<String> roomNames = new ArrayList<>();
        for (Room room : list_room) {
            roomNames.add(room.getTitle()); // Adjust this based on your Room class
        }

        // Convert the list to an array for the ArrayAdapter
        String[] roomNamesArray = roomNames.toArray(new String[0]);

        // Create an ArrayAdapter for the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_single_choice, roomNamesArray);

        // Create the ListView
        ListView listView = new ListView(getContext());
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Create the RadioGroup to hold the radio buttons
        RadioGroup radioGroup = new RadioGroup(getContext());
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        // Add the ListView to the RadioGroup
        radioGroup.addView(listView);

        // Create the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose a Room");
        builder.setView(radioGroup);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle the selection
                int selectedPosition = listView.getCheckedItemPosition();
                if (selectedPosition != ListView.INVALID_POSITION) {
                    Room selectedRoom = list_room.get(selectedPosition);
                    replaceFragment(new ListBaiDangFragment(), selectedRoom.get_id(), phoneOwner, token);
                }
            }
        });

        builder.setNegativeButton("Cancel", null);

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void replaceFragment(Fragment fragment, String id, String phone, String token) {
        if (getFragmentManager() != null) {
            Bundle args = new Bundle();
            args.putString("id", id);
            args.putString("phone", phone);
            args.putString("token", token);
            fragment.setArguments(args);

            getFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout_quanlybaidang, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

}