package com.example.rentappandroid.Utils;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Request.Add.IssueRequest;
import com.example.rentappandroid.FireBase.FirebaseHelper;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.Model.Notification;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiHopDong;
import com.example.rentappandroid.api.ApiIssue;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IsssueEntryDialog extends Dialog {

    private Spinner roomingHouseSpinner;
    private EditText contentEditText;
    private Button addButton;

    private List<String > rooms;
    private List<Leasecontracts> leasecontracts;
    private FirebaseHelper firebaseHelper;
    private String token;
    private String phoneOwner;
    public IsssueEntryDialog(Context context, String token, String phoneOwner) {
        super(context);
        this.token = token;
        this.phoneOwner = phoneOwner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_issue_entry);
        firebaseHelper = new FirebaseHelper();
        roomingHouseSpinner = findViewById(R.id.spinnerRoomingHouseissue);
        contentEditText = findViewById(R.id.editTextContentissue);
        addButton = findViewById(R.id.btnAddissue);
        rooms = new ArrayList<>();
        leasecontracts = new ArrayList<>();
        getData();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIssue();
                dismiss();  // Close the dialog after adding the comment
            }
        });
    }
    Owner tenant ;
    Owner landord;
    private void addIssue() {
        String selectedRoomingHouse = roomingHouseSpinner.getSelectedItem().toString();
         tenant = new Owner();
         landord = new Owner();
        for (Leasecontracts leasecontracts1 : leasecontracts){
            if(selectedRoomingHouse.equals(leasecontracts1.getRoomingHouse().getAddress().getFullAddress() + "_" + leasecontracts1.getRoomingHouse().get_id() + "_" + leasecontracts1.getRoomingHouse().getOwner().get_id())){
                tenant = leasecontracts1.getTenant();
                landord = leasecontracts1.getLandlord();
                break;
            }
        }
        String content = contentEditText.getText().toString();
        String plit[] = selectedRoomingHouse.split("_");
        Date currentDate = new Date();

        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        // Format the current date using the SimpleDateFormat
        String formattedDate = sdf.format(currentDate);
        IssueRequest issueRequest = new IssueRequest(phoneOwner,plit[2],plit[1],content,formattedDate, "UNRESOLVED");

        ApiIssue.apiApiIssue.createIssue(issueRequest,token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(),"Thành Công và đã được gửi đến chủ nhà trọ", Toast.LENGTH_SHORT).show();
                Notification notification = new Notification(UUID.randomUUID().toString()   , "Có sự cố với nhà trọ: " + issueRequest.getRoom() + " Vui lòng kiểm tra"
                        , tenant, landord, LocalDate.now().toString(), "SỰ CỐ", ""
                );
                firebaseHelper.addNotification(notification);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(),"Thất Bại", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void getData() {
        ApiHopDong.apiHopDong.getallleasecontractBytenant(phoneOwner, token).enqueue(new Callback<List<Leasecontracts>>() {
            @Override
            public void onResponse(Call<List<Leasecontracts>> call, Response<List<Leasecontracts>> response) {
                if (response.isSuccessful()) {
                    leasecontracts.addAll(response.body());
                    for (Leasecontracts leasecontracts : response.body()) {
                        String id = leasecontracts.getRoomingHouse().getAddress().getFullAddress() + "_" + leasecontracts.getRoomingHouse().get_id() + "_" + leasecontracts.getRoomingHouse().getOwner().get_id();

                        if (!rooms.contains(id)) {
                            rooms.add(id);
                        }
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, rooms);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // Apply the adapter to the spinner
                    roomingHouseSpinner.setAdapter(adapter);
                } else {
                    // Log error if the API call is not successful
                    Log.e(TAG, "API call failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Leasecontracts>> call, Throwable t) {
                // Log error if the API call fails
                Log.e(TAG, "API call failed", t);
            }
        });
    }

}
