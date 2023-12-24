package com.example.rentappandroid.Adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Activity.MessageActivity;
import com.example.rentappandroid.Activity.Tenant.DetailInfoRoomActivity;
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
                    Intent intent = new Intent(context, MessageActivity.class);
                    intent.putExtra("id", notification.getId_type());
                    context.startActivity(intent);
                } else if (notification.getType().equals("SỰ CỐ")) {

                } else if (notification.getType().equals("HÓA ĐƠN")) {

                } else if (notification.getType().equals("LỊCH HẸN")) {
                    showPopupMenu(view, notification);
                } else if (notification.getType().equals("TIÊU CHÍ PHÙ HỢP")) {

                } else {

                }


            }
        });
    }

    private void showPopupMenu(View view, Notification notification) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.your_popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_call) {
                    if(role.equals("ADMIN")){
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // Request the CALL_PHONE permission
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 100);
                        } else {
                            // Permission is already granted, initiate the call
                            Intent i = new Intent(Intent.ACTION_CALL);
                            i.setData(Uri.parse("tel:" + notification.getTenant().getPhoneNumber()));
                            context.startActivity(i);
                        }
                    } else {
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // Request the CALL_PHONE permission
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 100);
                        } else {
                            // Permission is already granted, initiate the call
                            Intent i = new Intent(Intent.ACTION_CALL);
                            i.setData(Uri.parse("tel:" + notification.getLandlord().getPhoneNumber()));
                            context.startActivity(i);
                        }
                    }

                } else if (item.getItemId() == R.id.menu_chat) {
                    Intent intent = new Intent(context, MessageActivity.class);
                    intent.putExtra("id", notification.getId_type());
                    context.startActivity(intent);
                }
                return true;
            }
        });

        popupMenu.show();
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
