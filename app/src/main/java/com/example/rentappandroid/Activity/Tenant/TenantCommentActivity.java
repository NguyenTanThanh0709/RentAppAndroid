package com.example.rentappandroid.Activity.Tenant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.rentappandroid.Adapter.CommentAdapter;
import com.example.rentappandroid.Model.CommentReview;
import com.example.rentappandroid.R;
import com.example.rentappandroid.Utils.CommentEntryDialog;
import com.example.rentappandroid.api.ApiComment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TenantCommentActivity extends AppCompatActivity {

    private RecyclerView commentListCommentRecycleview;
    private String token;
    private String phoneOwner;
    private String role;

    private List<CommentReview> listcomment;
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_comment);
        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);

        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        role = preferences.getString("role", "");
        listcomment = new ArrayList<>();
        commentListCommentRecycleview = findViewById(R.id.tenant_comment_recycleview);
        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        commentListCommentRecycleview.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter( listcomment, this,token,"delete");
        commentListCommentRecycleview.setAdapter(commentAdapter);

        getdata();


        FloatingActionButton fabAddComment = findViewById(R.id.menu_add_Comment);

        fabAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCommentEntryDialog();
            }
        });
    }

    private void showCommentEntryDialog() {
        CommentEntryDialog commentEntryDialog = new CommentEntryDialog(this, token, phoneOwner);
        commentEntryDialog.show();
    }

    private void getdata() {
        ApiComment.apiApiComment.getCommentsByRoomingtenantId(phoneOwner, token).enqueue(new Callback<List<CommentReview>>() {
            @Override
            public void onResponse(Call<List<CommentReview>> call, Response<List<CommentReview>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().isEmpty()) {
                        // The list is not empty, handle the comments
                        listcomment.addAll(response.body());
                        commentAdapter.notifyDataSetChanged();
                    } else {
                        listcomment = new ArrayList<>();
                    }
                } else {
                    // Log error for unsuccessful response
                    Log.e("CommentActivity", "API call failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CommentReview>> call, Throwable t) {
                // Log error for failure
                Log.e("CommentActivity", "API call failed: " + t.getMessage(), t);
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