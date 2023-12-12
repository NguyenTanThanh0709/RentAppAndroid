package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Image_info_Adapter extends RecyclerView.Adapter<Image_info_Adapter.ViewHolder>{

    private List<String> imageUrls;  // Replace with your actual list of image URLs or resource IDs
    private Context context;

    public Image_info_Adapter(Context context, List<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.with(context)
                .load(imageUrls.get(position))
                .placeholder(R.drawable.phongtro)
                .error(R.drawable.phongtro)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_info); // Assuming you have an ImageView in your item layout
        }
    }
}
