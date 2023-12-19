package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Model.Notification;
import com.example.rentappandroid.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{



    private Context context;
    private List<Notification> notificationList;
    private String role;
    private String token;

    public NotificationAdapter(Context context, List<Notification> notificationList,String role,String token) {
        this.context = context;
        this.notificationList = notificationList;
        this.role = role;
        this.token  = token;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thong_bao_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = notificationList.get(position);

        // Bind data to views
        holder.typeTextView.setText(notification.getType());
        holder.descriptionTextView.setText(notification.getDescription());
        holder.dateTextView.setText(notification.getDate());
        holder.tenantNameTextView.setText(notification.getTenant().getName());
        holder.landlordNameTextView.setText(notification.getLandlord().getName());
        holder.idTypeTextView.setText(notification.getId_type());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notification.getType().equals("TIN NHẮN")) {

                } else if (notification.getType().equals("SỰ CỐ")) {

                } else if (notification.getType().equals("HÓA ĐƠN")) {

                } else if (notification.getType().equals("LỊCH HẸN")) {

                } else if (notification.getType().equals("TIÊU CHÍ PHÙ HỢP")) {

                } else {

                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView typeTextView, descriptionTextView, dateTextView, tenantNameTextView, landlordNameTextView, idTypeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.carview_timphongtro);
            typeTextView = itemView.findViewById(R.id.type_notification);
            descriptionTextView = itemView.findViewById(R.id.mota_notification);
            dateTextView = itemView.findViewById(R.id.ngayup_notification);
            tenantNameTextView = itemView.findViewById(R.id.tenkhachhang_notification);
            landlordNameTextView = itemView.findViewById(R.id.tenchu_notification);
            idTypeTextView = itemView.findViewById(R.id.id_notification);
        }
    }
}
