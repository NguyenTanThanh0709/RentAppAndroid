package com.example.rentappandroid.Fragment.Landlord;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.AsyncListUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.rentappandroid.Adapter.ConversationAdapter;
import com.example.rentappandroid.Adapter.NotificationAdapter;
import com.example.rentappandroid.FireBase.ConversationIdsCallback;
import com.example.rentappandroid.FireBase.DataCallback;
import com.example.rentappandroid.FireBase.MessageHelper;
import com.example.rentappandroid.Model.ListConversation;
import com.example.rentappandroid.Model.Message;
import com.example.rentappandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TinNhanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TinNhanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TinNhanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TinNhanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TinNhanFragment newInstance(String param1, String param2) {
        TinNhanFragment fragment = new TinNhanFragment();
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
        messageHelper = new MessageHelper();

    }
    private MessageHelper messageHelper;
    private List<ListConversation> listConversations;
    private ConversationAdapter conversationAdapter;
    private RecyclerView recyclerView;


    private String token;
    private  String role;
    private  String phoneOwner;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tin_nhan, container, false);
        listConversations = new ArrayList<>();
        //list = new ArrayList<>();
//        Message newMessage = new Message();
//        newMessage.setLandrodId("656754184c4b3706405f8445");
//        newMessage.setTenantId("6569944a5cae883457a5ce56");
//        newMessage.setContent("Hello, how are you?");
//        newMessage.setTimestamp(System.currentTimeMillis());
//        messageHelper.addMessage(newMessage);

//        messageHelper.getMessages("656754184c4b3706405f8445", "6569944a5cae883457a5ce56", new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // Xử lý dữ liệu nhận được từ Firebase
//                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
//                    Message retrievedMessage = messageSnapshot.getValue(Message.class);
//                    list.add(retrievedMessage);
//                    Log.d("ok", retrievedMessage.getContent());
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Xử lý khi có lỗi xảy ra
//                Log.e("Firebase", "Error: " + databaseError.getMessage());
//            }
//        });


        SharedPreferences preferences =  getActivity().getSharedPreferences("Owner", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        role = preferences.getString("role", "");
        phoneOwner = preferences.getString("sdt", "");


        recyclerView = view.findViewById(R.id.listtinnhan_recycleview);
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        conversationAdapter = new ConversationAdapter( getActivity(), listConversations);
        recyclerView.setAdapter(conversationAdapter);

        messageHelper.getConversationIds(new ConversationIdsCallback() {
            @Override
            public void onConversationIdsReceived(List<String> conversationIds) {
                for (String conversationId : conversationIds) {
                    String[] split = conversationId.split("_");
                    if (role.equals("ADMIN")) {
                        if (phoneOwner.equals(split[0])) {
                            ListConversation listConversation = new ListConversation(split[0], split[1]);
                            if (!listConversations.contains(listConversation)) {
                                listConversation.initializeData(token, new DataCallback() {
                                    @Override
                                    public void onDataInitialized() {
                                        listConversations.add(listConversation);
                                        conversationAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    } else {
                        if (phoneOwner.equals(split[1])) {
                            ListConversation listConversation = new ListConversation(split[0], split[1]);
                            if (!listConversations.contains(listConversation)) {
                                listConversation.initializeData(token, new DataCallback() {
                                    @Override
                                    public void onDataInitialized() {
                                        listConversations.add(listConversation);
                                        conversationAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    }
                }
            }
        });


        return view;
    }



}