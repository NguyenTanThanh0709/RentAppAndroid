package com.example.rentappandroid.Fragment.Landlord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.rentappandroid.Adapter.IssueAdapter;
import com.example.rentappandroid.Adapter.Landlord.RoomAdapter;
import com.example.rentappandroid.Dto.Reponse.AreaInformationReponse;
import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Model.Issue;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiIssue;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListIssueActivity extends AppCompatActivity {

    private String token;
    private String phoneOwner;
    private String role;

    private RecyclerView recyclerView;
    private IssueAdapter issueAdapter;
    private List<Issue> issueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_issue);
        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        role = preferences.getString("role", "");
        issueList = new ArrayList<>();
        recyclerView = findViewById(R.id.listissue_recycleview);
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(getApplication(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        issueAdapter = new IssueAdapter( getApplication(), issueList,role,token);
        recyclerView.setAdapter(issueAdapter);


        getData();
    }

    private void getData(){
        if (role.equals("ADMIN")) {
            ApiIssue.apiApiIssue.getListIssueOwner(phoneOwner, token).enqueue(new Callback<List<Issue>>() {
                @Override
                public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                    if (response.isSuccessful()) {
                        issueList.addAll(response.body());
                        issueAdapter.notifyDataSetChanged();
                    } else {
                        // Log the error message
                        Log.e("ListIssueActivity", "Error in getListIssueOwner: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Issue>> call, Throwable t) {
                    // Log the error message
                    Log.e("ListIssueActivity", "Failure in getListIssueOwner", t);
                }
            });

        } else {
            ApiIssue.apiApiIssue.getListIssuetenant(phoneOwner, token).enqueue(new Callback<List<Issue>>() {
                @Override
                public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                    if (response.isSuccessful()) {
                        issueList.addAll(response.body());
                        issueAdapter.notifyDataSetChanged();
                    } else {
                        // Log the error message
                        Log.e("ListIssueActivity", "Error in getListIssuetenant: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Issue>> call, Throwable t) {
                    // Log the error message
                    Log.e("ListIssueActivity", "Failure in getListIssuetenant", t);
                }
            });
        }

    }


}