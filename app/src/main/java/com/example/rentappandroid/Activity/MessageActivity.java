package com.example.rentappandroid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rentappandroid.Adapter.IssueAdapter;
import com.example.rentappandroid.Adapter.MessageAdapter;
import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.FireBase.MessageHelper;
import com.example.rentappandroid.Model.Message;
import com.example.rentappandroid.Model.Notification;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiLandrod;
import com.example.rentappandroid.api.ApiTenant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends AppCompatActivity {

    private String token;
    private  String role;
    private  String phoneOwner;
    private List<Message > list;
    private MessageHelper messageHelper;
    private MessageAdapter messageAdapter;
    private RecyclerView recyclerViewMessages;
    private EditText editTextMessage;
    private Button buttonSend;

    private Owner landlord;
    private Owner tenant;

    private void getdata(String idt, String idl){
        ApiTenant.apiTenant.getOne(idt,token).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                tenant = new Owner();
                tenant = response.body();

            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {

            }
        });

        ApiLandrod.apiLandrod.getOne(idl, token).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                landlord = new Owner();
                landlord = response.body();
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        messageHelper = new MessageHelper();
        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        role = preferences.getString("role", "");
        phoneOwner = preferences.getString("sdt", "");
        list = new ArrayList<>();

        Intent intent = getIntent();
        String conversationId = intent.getStringExtra("id");
        String[] split = conversationId.split("_");
        getdata(split[1],split[0]);

        recyclerViewMessages = findViewById(R.id.recyclerViewMessages);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);


        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(getApplication(), spanCount);
        recyclerViewMessages.setLayoutManager(layoutManager);
        messageAdapter = new MessageAdapter(list);
        recyclerViewMessages.setAdapter(messageAdapter);


        messageHelper.getMessages(split[0], split[1], new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Xử lý dữ liệu nhận được từ Firebase
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    Message retrievedMessage = messageSnapshot.getValue(Message.class);
                    list.add(retrievedMessage);
                    Log.d("ok", retrievedMessage.getContent());
                }

                messageAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                Log.e("Firebase", "Error: " + databaseError.getMessage());
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Handle sending the message
                String messageText = editTextMessage.getText().toString().trim();
                if (!messageText.isEmpty()) {
                    Message message = new Message(split[0],split[1],messageText,System.currentTimeMillis(),tenant.getName(),landlord.getName());
                    messageHelper.addMessage(message);

                    list.clear();
                    messageAdapter.notifyDataSetChanged();
                    editTextMessage.getText().clear();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_back) {
            // Handle menu item 1 click
            // Example: replace the fragment with a different fragment
            this.finish();
            return  true;
        }  else {
            return super.onOptionsItemSelected(item);
        }
    }
}