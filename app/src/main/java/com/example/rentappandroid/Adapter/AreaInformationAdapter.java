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

import com.example.rentappandroid.Dto.Reponse.AreaInformationReponse;
import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
import com.example.rentappandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AreaInformationAdapter extends RecyclerView.Adapter<AreaInformationAdapter.ViewHolder>{
    private List<AreaInformationReponse> areaInformationList;
    private Context context;

    public void updatePhi(String  position, Double distance, String mota) {
        for (int i = 0; i < areaInformationList.size(); i++) {
            AreaInformationReponse servireChareReponse = areaInformationList.get(i);
            if (servireChareReponse.get_id().equals(position)) {
                servireChareReponse.setDistance(distance);
                servireChareReponse.setDescription(mota);
                notifyItemChanged(i);
                break;
            }
        }
    }

    private AreaClickListener listener;

    public interface AreaClickListener {
        void onAreaClick(AreaInformationReponse areaInformationReponse);
    }

    public AreaInformationAdapter(Context context,List<AreaInformationReponse> areaInformationList, AreaClickListener listener) {
        this.areaInformationList = areaInformationList;
        this.context = context;
        this.listener = listener;
    }

    public AreaInformationAdapter(List<AreaInformationReponse> areaInformationList, Context context) {
        this.areaInformationList = areaInformationList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.khuvucxungquanh_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AreaInformationReponse areaInformation = areaInformationList.get(position);

        // Set data to views
        holder.descriptionTextView.setText(areaInformation.getDescription());
        holder.ten_khuvuc.setText(areaInformation.getAreainformation_name());
        holder.distanceTextView.setText(String.valueOf(areaInformation.getDistance()));

        // Load image using Glide or your preferred image loading library
        Picasso.with(context)
                .load(areaInformation.getAreainformation_img())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.apartment)
                .into(holder.imageView);

        // Handle item click if needed
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAreaClick(areaInformation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return areaInformationList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView distanceTextView;
        TextView descriptionTextView;
        CardView cardView;
        TextView ten_khuvuc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_khuvuc);
            distanceTextView = itemView.findViewById(R.id.khoangcach_khuvuc);
            ten_khuvuc = itemView.findViewById(R.id.ten_khuvuc);
            descriptionTextView = itemView.findViewById(R.id.mota_khuvuc);
            cardView = itemView.findViewById(R.id.khuvucxungquanh_item);
        }
    }
}
