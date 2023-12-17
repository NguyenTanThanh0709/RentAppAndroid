package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Activity.Tenant.BaiDang.FormTimNguoiOGhepActivity;
import com.example.rentappandroid.Activity.Tenant.BaiDang.FormTimPhongActivity;
import com.example.rentappandroid.Activity.Tenant.DetailInfoRoomActivity;
import com.example.rentappandroid.Activity.Tenant.Detail_info_tim_o_ghep_Activity;
import com.example.rentappandroid.Model.FindRoomHouseResponse;
import com.example.rentappandroid.Model.TimNguoiOGhep;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiPostFindHouse;
import com.example.rentappandroid.api.ApiTimNguoiOGhep;
import com.example.rentappandroid.api.UpdateStatusRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimNguoiOGhepAdapter extends RecyclerView.Adapter<TimNguoiOGhepAdapter.ViewHolder>{


    private Context context;
    private List<TimNguoiOGhep> timNguoiOGhepList;
    private String token;
    private String role;
    private String type;

    public TimNguoiOGhepAdapter(Context context, List<TimNguoiOGhep> timNguoiOGhepList, String token, String role, String type) {
        this.context = context;
        this.timNguoiOGhepList = timNguoiOGhepList;
        this.token = token;
        this.role = role;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tim_nguoi_o_ghep_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimNguoiOGhep timNguoiOGhep = timNguoiOGhepList.get(position);
        holder.titleTextView.setText(timNguoiOGhep.getTitle());
        holder.addressTextView.setText(timNguoiOGhep.getAddress().getFullAddress());
        holder.priceTextView.setText(String.format("%,d đ", timNguoiOGhep.getPrice()));
        holder.dientich.setText(timNguoiOGhep.getSquare_feet() + " mét vuông");
        holder.songuoi.setText(timNguoiOGhep.getPeopeleNumber()+ " người");
        holder.trangthai.setText(timNguoiOGhep.getStatus());
        holder.ngaythembai.setText(timNguoiOGhep.getDay_up());

        Picasso.with(context)
                .load(timNguoiOGhep.getImage_url().get(0))
                .placeholder(R.drawable.phongtro)
                .error(R.drawable.phongtro)
                .into(holder.imageView);

        holder.timnguoioghep_carview_baiViet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("")){
                    Intent intent = new Intent(context, Detail_info_tim_o_ghep_Activity.class);
                    intent.putExtra("ID_POST", timNguoiOGhep.get_id());
                    intent.putExtra("token", token);
                    intent.putExtra("role", role);
                    context.startActivity(intent);
                }else {


                    showPopupMenu(view, timNguoiOGhep);
                }
            }
        });
    }

    public void updateDataset(List<TimNguoiOGhep> newData) {
        this.timNguoiOGhepList.clear();
        this.timNguoiOGhepList.addAll(newData);
        notifyDataSetChanged();
    }
    public void updateStatus(String issueId, String newStatus) {
        for (int i = 0; i < timNguoiOGhepList.size(); i++) {

            if (timNguoiOGhepList.get(i).get_id().equals(issueId)) {
                timNguoiOGhepList.get(i).setStatus(newStatus);
                notifyItemChanged(i);
                break;
            }
        }
    }

    private void showPopupMenu(View view, TimNguoiOGhep findRoomHouse) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.find_room_house_menu);

        // Set up the menu item click listener
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_edit) {
                    Intent intent = new Intent(context, FormTimNguoiOGhepActivity.class);
                    intent.putExtra("ID_KEY", findRoomHouse.get_id());
                    intent.putExtra("token", token);
                    context.startActivity(intent); // Corrected line
                    return true;
                } else if (item.getItemId() == R.id.menu_delete) {
                    ApiTimNguoiOGhep.apiApiTimNguoiOGhep.deleteTimNguoiOGhep(findRoomHouse.get_id(),token).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            List<TimNguoiOGhep> updatedList = new ArrayList<>(timNguoiOGhepList);
                            updatedList.remove(findRoomHouse); // Remove the deleted item
                            updateDataset(updatedList);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });



                    return true;
                } else if (item.getItemId() == R.id.menu_change_active) {

                    UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest("Đang hoạt động");
                    ApiTimNguoiOGhep.apiApiTimNguoiOGhep.updateTimNguoiOGhepStatusById(findRoomHouse.get_id(),updateStatusRequest, token).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(findRoomHouse.get_id(), "Đang hoạt động");
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(findRoomHouse.get_id(), "Đang hoạt động");
                        }
                    });

                    Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                    updateStatus(findRoomHouse.get_id(), "Đang hoạt động");

                    return true;
                } else if (item.getItemId() == R.id.menu_change_hidden) {
                    UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest("Bị ẩn");
                    ApiTimNguoiOGhep.apiApiTimNguoiOGhep.updateTimNguoiOGhepStatusById(findRoomHouse.get_id(),updateStatusRequest, token).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(findRoomHouse.get_id(), "Bị ẩn");
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(findRoomHouse.get_id(), "Bị ẩn");
                        }
                    });
                    Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                    updateStatus(findRoomHouse.get_id(), "Bị ẩn");
                    return true;
                } else {
                    return false;
                }
            }
        });

        // Show the popup menu
        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return timNguoiOGhepList.size();
    }

    public void setRoomList(List<TimNguoiOGhep> temp) {
        timNguoiOGhepList = temp;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView addressTextView;
        TextView priceTextView;
        // Add other views as needed

        TextView dientich;
        TextView songuoi;
        TextView trangthai;
        TextView ngaythembai;

        CardView timnguoioghep_carview_baiViet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timnguoioghep_carview_baiViet = itemView.findViewById(R.id.timnguoioghep_carview_baiViet);
            dientich = itemView.findViewById(R.id.timnguoioghep_dientich_baidang);
            songuoi = itemView.findViewById(R.id.timnguoioghep_songuoicantim_baidang);
            trangthai = itemView.findViewById(R.id.timnguoioghep_trangthai_baidang);
            ngaythembai = itemView.findViewById(R.id.timnguoioghep_ngaythem_baidang);

            // Initialize views
            imageView = itemView.findViewById(R.id.timnguoioghep_img_baidang);
            titleTextView = itemView.findViewById(R.id.timnguoioghep_title_baidang);
            addressTextView = itemView.findViewById(R.id.timnguoioghep_diachi_baidang);
            priceTextView = itemView.findViewById(R.id.timnguoioghep_giatienTro_baidang);
            // Initialize other views as needed
        }
    }
}
