package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BaiVietAdapter extends RecyclerView.Adapter<BaiVietAdapter.ViewHolder>{
    private List<BaiViet> baiVietList;
    private Context context;

    // Constructor to initialize the data
    public BaiVietAdapter(List<BaiViet> baiVietList, Context context) {
        this.baiVietList = baiVietList;
        this.context = context;
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

            }
        });



        holder.yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
