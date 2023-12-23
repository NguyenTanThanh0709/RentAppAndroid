package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Activity.Tenant.BaiDang.FormTimPhongActivity;
import com.example.rentappandroid.Activity.Tenant.DetailInfoFindHouseActivity;
import com.example.rentappandroid.Model.FindRoomHouseResponse;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiPostFindHouse;
import com.example.rentappandroid.api.UpdateStatusRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindRoomHouseAdapter extends RecyclerView.Adapter<FindRoomHouseAdapter.ViewHolder> {

    private final Context context;
    private List<FindRoomHouseResponse> findRoomHouseList;
    private String token;
    private String role;
    private String type;
    public void setRoomList(List<FindRoomHouseResponse> temp) {
        this.findRoomHouseList = temp;
    }

    public FindRoomHouseAdapter(Context context, List<FindRoomHouseResponse> findRoomHouseList, String token, String role, String type) {
        this.context = context;
        this.findRoomHouseList = findRoomHouseList;
        this.token = token;
        this.role = role;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timphongtro_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FindRoomHouseResponse findRoomHouse = findRoomHouseList.get(position);

        holder.titleTextView.setText(findRoomHouse.getTitle());
        holder.priceTextView.setText(String.format("%s - %.0fđ", 0, findRoomHouse.getMaxPrice()));
        holder.addressTextView.setText(findRoomHouse.getAddress());
        holder.ngayupTextView.setText(findRoomHouse.getDay_up());
        holder.trangthaiTextView.setText(findRoomHouse.getStatus());
        holder.carview_timphongtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("")){
                    Intent intent = new Intent(context, DetailInfoFindHouseActivity.class);
                    intent.putExtra("ID_POST", findRoomHouse.get_id());
                    intent.putExtra("token", token);
                    intent.putExtra("role", role);
                    context.startActivity(intent);
                }else {
                    showPopupMenu(view, findRoomHouse);
                }
            }
        });
    }
    public void updateDataset(List<FindRoomHouseResponse> newData) {
        this.findRoomHouseList.clear();
        this.findRoomHouseList.addAll(newData);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return findRoomHouseList.size();
    }

    public void updateStatus(String issueId, String newStatus) {
        for (int i = 0; i < findRoomHouseList.size(); i++) {

            if (findRoomHouseList.get(i).get_id().equals(issueId)) {
                findRoomHouseList.get(i).setStatus(newStatus);
                notifyItemChanged(i);
                break;
            }
        }
    }

    private void showPopupMenu(View view, FindRoomHouseResponse findRoomHouse) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.find_room_house_menu);

        // Set up the menu item click listener
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_edit) {
                    Intent intent = new Intent(context, FormTimPhongActivity.class);
                    intent.putExtra("ID_KEY", findRoomHouse.get_id());
                    intent.putExtra("type", "edit");
                    intent.putExtra("token", token);
                    context.startActivity(intent); // Corrected line
                    return true;
                } else if (item.getItemId() == R.id.menu_delete) {

                    ApiPostFindHouse.apiApiPostFindHouse.deleteFindRoomHouse(findRoomHouse.get_id(),token).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            List<FindRoomHouseResponse> updatedList = new ArrayList<>(findRoomHouseList);
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
                    ApiPostFindHouse.apiApiPostFindHouse.updateFindRoomHouseStatusById(findRoomHouse.get_id(), updateStatusRequest, token).enqueue(new Callback<FindRoomHouseResponse>() {
                        @Override
                        public void onResponse(Call<FindRoomHouseResponse> call, Response<FindRoomHouseResponse> response) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(findRoomHouse.get_id(), "Đang hoạt động");
                        }

                        @Override
                        public void onFailure(Call<FindRoomHouseResponse> call, Throwable t) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(findRoomHouse.get_id(), "Đang hoạt động");
                        }
                    });

                    return true;
                } else if (item.getItemId() == R.id.menu_change_hidden) {
                    UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest("Bị ẩn");
                    ApiPostFindHouse.apiApiPostFindHouse.updateFindRoomHouseStatusById(findRoomHouse.get_id(), updateStatusRequest, token).enqueue(new Callback<FindRoomHouseResponse>() {
                        @Override
                        public void onResponse(Call<FindRoomHouseResponse> call, Response<FindRoomHouseResponse> response) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(findRoomHouse.get_id(), "Bị ẩn");
                        }

                        @Override
                        public void onFailure(Call<FindRoomHouseResponse> call, Throwable t) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(findRoomHouse.get_id(), "Bị ẩn");

                        }
                    });

                    return true;
                } else {
                    return false;
                }
            }
        });

        // Show the popup menu
        popupMenu.show();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView priceTextView;
        TextView ngayupTextView;
        TextView trangthaiTextView;
        TextView addressTextView;
        CardView carview_timphongtro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title_timphong);
            priceTextView = itemView.findViewById(R.id.giatimphong_timphong);
            addressTextView = itemView.findViewById(R.id.address_timphong);
            ngayupTextView = itemView.findViewById(R.id.ngayup_timphong);
            trangthaiTextView = itemView.findViewById(R.id.trangthai_timphong);
            carview_timphongtro = itemView.findViewById(R.id.carview_timphongtro);

        }
    }
}