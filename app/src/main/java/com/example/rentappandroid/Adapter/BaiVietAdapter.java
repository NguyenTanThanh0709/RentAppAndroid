package com.example.rentappandroid.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Activity.Landlord.FORMADD.FormPostActivity;
import com.example.rentappandroid.Activity.Landlord.FORMADD.FormToaNhaActivity;
import com.example.rentappandroid.Activity.Tenant.DetailInfoRoomActivity;
import com.example.rentappandroid.Dto.Reponse.RoomingHouseComplex;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BaiVietAdapter extends RecyclerView.Adapter<BaiVietAdapter.ViewHolder>{
    private List<BaiViet> baiVietList;
    private Context context;
    private String role;
    private String token;

    // Constructor to initialize the data
    public BaiVietAdapter(List<BaiViet> baiVietList, Context context,String role, String token) {
        this.baiVietList = baiVietList;
        this.context = context;
        this.role = role;
        this.token = token;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.baiviet_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiViet baiViet = baiVietList.get(position);
        holder.titleBaiDang.setText(baiViet.getDescription());
        holder.diachiBaiDang.setText("Địa chỉ: " + baiViet.getRoom().getAddress().getFullAddress());
        holder.dientichBaiDang.setText("Diện tích mét vuông: " + String.valueOf(baiViet.getRoom().getSquare_feet()));
        holder.ngayThemBaiDang.setText("Ngày đăng bài: " + baiViet.getDay_up());
        holder.motaBaiDang.setText(baiViet.getDescription());
        holder.tiencoc_baidang.setText("Tiền cọc: " + baiViet.getDeposit());

        Picasso.with(context)
                .load(baiViet.getRoom().getImage_url().get(0))
                .placeholder(R.drawable.phongtro)
                .error(R.drawable.phongtro)
                .into(holder.imgBaiDang);

        holder.carview_baiViet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(role.equals("ADMIN")){
                    showPopupMenu(view, baiViet.get_id());
                }else {
                    Intent intent = new Intent(context, DetailInfoRoomActivity.class);
                    intent.putExtra("ID_POST", baiViet.get_id());
                    intent.putExtra("token", token);
                    context.startActivity(intent);

                }

            }
        });



        holder.yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void showPopupMenu(View view, String roomingHouseComplex) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.menu_more_baiviet); // Replace with your menu resource

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();

                if (itemId == R.id.menu_edit_see_baiviet) {


                    Intent intent = new Intent(context, FormPostActivity.class);
                    intent.putExtra("idPost", roomingHouseComplex);
                    context.startActivity(intent);
                    return true;

                }else if (itemId == R.id.menu_delete_baiviet) {
                    showDeleteConfirmationDialog(roomingHouseComplex);
                    return true;
                } else {
                    return false;
                }
            }
        });

        popupMenu.show();
    }

    private void showDeleteConfirmationDialog(String email) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this Post?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    @Override
    public int getItemCount() {
        return baiVietList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Declare your views here
        ImageView imgBaiDang;
        TextView tiencoc_baidang, giatienTro, titleBaiDang, diachiBaiDang, dientichBaiDang, ngayThemBaiDang, motaBaiDang;
        ImageView yeuthich;

        CardView carview_baiViet;

        public ViewHolder(View itemView) {
            super(itemView);
            // Initialize your views here
            imgBaiDang = itemView.findViewById(R.id.img_baidang);
            giatienTro = itemView.findViewById(R.id.giatienTro_baidang);
            titleBaiDang = itemView.findViewById(R.id.title_baidang);
            diachiBaiDang = itemView.findViewById(R.id.diachi_baidang);
            dientichBaiDang = itemView.findViewById(R.id.dientich_baidang);
            ngayThemBaiDang = itemView.findViewById(R.id.ngaythem_baidang);
            motaBaiDang = itemView.findViewById(R.id.mota_baidang);
            yeuthich = itemView.findViewById(R.id.yeuthich);
            tiencoc_baidang = itemView.findViewById(R.id.tiencoc_baidang);
            carview_baiViet = itemView.findViewById(R.id.carview_baiViet);
        }
    }
}
