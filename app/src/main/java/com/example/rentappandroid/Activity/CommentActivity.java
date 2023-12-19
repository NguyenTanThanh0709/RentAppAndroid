package com.example.rentappandroid.Activity;

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
import android.widget.TextView;

import com.example.rentappandroid.Adapter.CommentAdapter;
import com.example.rentappandroid.Adapter.Image_info_Adapter;
import com.example.rentappandroid.Adapter.Landlord.LeaseContractAdapter;
import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Model.CommentReview;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiComment;
import com.example.rentappandroid.api.ApiRoomHouse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    private String token;
    private String phoneOwner;
    private String role;

    private List<CommentReview> listcomment;
    private CommentAdapter commentAdapter;
    private Room room;



    private RecyclerView commentListImagePhongTroRecycle;
    private TextView commentLoaiPhongTro;
    private TextView commentTieuDeBaiDang;
    private TextView commentGiaTro;
    private TextView commentDienTichPhong;
    private TextView commentSoNguoi;
    private TextView commentQuyTacPhongTro;
    private TextView commentTenChuTro;
    private TextView commentSdtBaiDangInfo;
    private RecyclerView commentListCommentRecycleview;

    private void init(){
        commentListImagePhongTroRecycle = findViewById(R.id.comment_listimagephongtro_recycle);
        commentLoaiPhongTro = findViewById(R.id.comment_loaiphongtro);
        commentTieuDeBaiDang = findViewById(R.id.comment_tieudebaidang);
        commentGiaTro = findViewById(R.id.comment_giatro);
        commentDienTichPhong = findViewById(R.id.comment_dientichphong_baidanginfo);
        commentSoNguoi = findViewById(R.id.comment_songuoi_baidanginfo);
        commentQuyTacPhongTro = findViewById(R.id.comment_quytac_baidanginfo_recycleview);
        commentTenChuTro = findViewById(R.id.comment_tenchutro);
        commentSdtBaiDangInfo = findViewById(R.id.comment_sdt_baidanginfo);
        commentListCommentRecycleview = findViewById(R.id.comment_listcomment_recycleview);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        SharedPreferences preferences =  getSharedPreferences("Owner", Context.MODE_PRIVATE);

        token = preferences.getString("token", "");  // Replace "" with the default value if not found
        phoneOwner = preferences.getString("sdt", "");  // Replace "" with the default value if not found
        role = preferences.getString("role", "");
        listcomment = new ArrayList<>();
        room = new Room();
        init();

        Intent intent = getIntent();
        String idHouse = intent.getStringExtra("idhouse");

        int spanCount = 1; // Number of items per row
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        commentListCommentRecycleview.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter( listcomment, this);
        commentListCommentRecycleview.setAdapter(commentAdapter);

        getData(idHouse);


    }

    private void getData(String idHouse) {

        ApiRoomHouse.apiRoom.getListRoomByID(idHouse,token).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                room = response.body();

                commentListImagePhongTroRecycle.setLayoutManager(new LinearLayoutManager(CommentActivity.this, LinearLayoutManager.HORIZONTAL, false));
                Image_info_Adapter imageAdapter = new Image_info_Adapter(CommentActivity.this, room.getImage_url());
                commentListImagePhongTroRecycle.setAdapter(imageAdapter);
                commentLoaiPhongTro.setText(room.getTypehouse().getTypehouse_name());
                commentTieuDeBaiDang.setText(room.getAddress().getFullAddress());
                commentGiaTro.setText(room.getPrice() + "triệu/tháng");
                commentDienTichPhong.setText(room.getSquare_feet() + "");
                commentSoNguoi.setText(room.getPeopeleNumber() + "");
                String rules = "";
                for (String in : room.getRules()){
                    rules += in + "\n";
                }
                commentQuyTacPhongTro.setText(rules);
                commentTenChuTro.setText(room.getOwner().getName());
                commentSdtBaiDangInfo.setText(room.getOwner().getPhoneNumber());





            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {

            }
        });



        ApiComment.apiApiComment.getCommentsByRoomingHouseId(idHouse, token).enqueue(new Callback<List<CommentReview>>() {
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