package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
import com.example.rentappandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServiceChargeAdapter extends RecyclerView.Adapter<ServiceChargeAdapter.ViewHolder>{

    public void updatePhi(String  position, String newPhi) {
        for (int i = 0; i < serviceChargeList.size(); i++) {
            ServireChareReponse servireChareReponse = serviceChargeList.get(i);
            if (servireChareReponse.get_id().equals(position)) {
                servireChareReponse.setPhi(newPhi);
                notifyItemChanged(i);
                break;
            }
        }
    }
    private List<ServireChareReponse> serviceChargeList;
    private Context context;
    private ServiceChargeClickListener listener;

    public interface ServiceChargeClickListener {
        void onServiceChargeClick(ServireChareReponse servireChareReponse);
    }

    public ServiceChargeAdapter(Context context, List<ServireChareReponse> serviceChargeList, ServiceChargeClickListener listener) {
        this.context = context;
        this.serviceChargeList = serviceChargeList;
        this.listener = listener;
    }

    public ServiceChargeAdapter(Context context, List<ServireChareReponse> serviceChargeList) {
        this.context = context;
        this.serviceChargeList = serviceChargeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phidichvu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServireChareReponse serviceCharge = serviceChargeList.get(position);

        // Set data to views
        holder.nameTextView.setText(serviceCharge.getServicecharge_name());
        holder.phitextView.setText(serviceCharge.getPhi());

        Picasso.with(context)
                .load(serviceCharge.getServicecharge_img())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.issue)
                .into(holder.imageView);

        // Handle item click if needed
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onServiceChargeClick(serviceCharge);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceChargeList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView phitextView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_dich_vu);
            nameTextView = itemView.findViewById(R.id.name_dichvu);
            phitextView = itemView.findViewById(R.id.phi_dich_vu);
            cardView = itemView.findViewById(R.id.dichvu_item);
        }
    }
}
