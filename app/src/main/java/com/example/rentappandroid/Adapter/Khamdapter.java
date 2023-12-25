package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Activity.ListInfoActivity;
import com.example.rentappandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Khamdapter extends RecyclerView.Adapter<Khamdapter.KhamViewHolder>{


    private Context context;
    private List<String> dataList;
    private String token;
    public Khamdapter(Context context, List<String> dataList, String token) {
        this.context = context;
        this.dataList = dataList;
        this.token = token;
    }

    @NonNull
    @Override
    public KhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kham_pha_item, parent, false);
        return new Khamdapter.KhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhamViewHolder holder, int position) {
        String title = dataList.get(position);

        holder.nameTextView.setText(title);
        Picasso.with(context)
                .load("https://file1.dangcongsan.vn/data/0/images/2023/11/11/upload_37/img-4361.jpeg")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.apartment)
                .into(holder.img_khampha);

        holder.img_khampha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListInfoActivity.class);
                intent.putExtra("type", "address");
                intent.putExtra("quan", title);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class KhamViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        ImageView img_khampha;

        public KhamViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_khampha);
            img_khampha = itemView.findViewById(R.id.img_khampha);
        }
    }
}
