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
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rentappandroid.Dto.Request.Add.CommentReviewData;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiComment;
import com.example.rentappandroid.api.ApiHopDong;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentEntryDialog extends Dialog {

    private Spinner roomingHouseSpinner;
    private EditText contentEditText;
    private RatingBar ratingBar;
    private Button addButton;

    private List<String > rooms;

    private String token;
    private String phoneOwner;

    public CommentEntryDialog(Context context, String token, String phoneOwner) {
        super(context);
        this.token = token;
        this.phoneOwner = phoneOwner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment_entry);

        roomingHouseSpinner = findViewById(R.id.spinnerRoomingHouse);
        contentEditText = findViewById(R.id.editTextContent);
        ratingBar = findViewById(R.id.ratingBar);
        addButton = findViewById(R.id.btnAddComment);
        rooms = new ArrayList<>();

        getData();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the method to add a comment
                addComment();
                dismiss();  // Close the dialog after adding the comment
            }
        });
    }

    private void getData() {
        ApiHopDong.apiHopDong.getallleasecontractBytenant(phoneOwner, token).enqueue(new Callback<List<Leasecontracts>>() {
            @Override
            public void onResponse(Call<List<Leasecontracts>> call, Response<List<Leasecontracts>> response) {
                if (response.isSuccessful()) {
                    for (Leasecontracts leasecontracts : response.body()) {
                        String id = leasecontracts.getRoomingHouse().getAddress().getFullAddress() + "_" + leasecontracts.getRoomingHouse().get_id();

                        // Check if the id already exists in the rooms list
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

    private void addComment() {
        // Retrieve the selected rooming house, content, and rating
        String selectedRoomingHouse = roomingHouseSpinner.getSelectedItem().toString();
        String content = contentEditText.getText().toString();
        float rating = ratingBar.getRating();

// Convert float rating to String and then parse it to an integer
        String ratingString = String.valueOf((int) rating);
        int ratingInt = Integer.parseInt(ratingString);

        String[] splot = selectedRoomingHouse.split("_");

        CommentReviewData commentRequest = new CommentReviewData(phoneOwner, splot[1], content, ratingInt);

        ApiComment.apiApiComment.addCommentReview(commentRequest,token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(),"Thành Công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(),"Thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        // TODO: Implement the logic to add the comment, e.g., send a network request
    }
}
