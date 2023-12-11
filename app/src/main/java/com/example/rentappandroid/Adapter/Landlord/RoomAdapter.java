package com.example.rentappandroid.Adapter.Landlord;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.rentappandroid.Activity.Landlord.FORMADD.FormAddRoomHouseActivity;
import com.example.rentappandroid.Activity.Landlord.FORMADD.FormToaNhaActivity;
import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>{

    private Context context;
    private List<Room> roomList;

    public RoomAdapter(Context context, List<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_tro_item, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.giatienTextView.setText(room.getPrice() + " đ");
        holder.titleTextView.setText(room.getTitle());
        holder.diachiTextView.setText(room.getAddress().getFullAddress());
        holder.dientichTextView.setText("Diện tích: " + room.getSquare_feet() + " mét vuông");
        holder.ngaythemTextView.setText("Ngày đăng: " + room.getUp_dates());
        Picasso.with(context)
                .load(room.getImage_url().get(0))
                .placeholder(R.drawable.house)
                .error(R.drawable.house)
                .into(holder.imageView);

        holder.moreImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view, room);
            }
        });
    }

    private void showPopupMenu(View view, Room room) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.menu_edit_see_phongtro); // Replace with your menu resource

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();

                if (itemId == R.id.menu_edit_see_phongtro) {


                    Intent intent = new Intent(context, FormAddRoomHouseActivity.class);
                    intent.putExtra("roomingHouse", room.get_id());
                    context.startActivity(intent);
                    return true;

                }else if (itemId == R.id.menu_delete_phongtro) {
                    showDeleteConfirmationDialog(room.get_id());
                    return true;
                } else {
                    return false;
                }
            }
        });

        popupMenu.show();
    }

    private void showDeleteConfirmationDialog(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this Room?");
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
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView titleTextView;
        TextView giatienTextView;
        ImageView checkImageView;
        ImageView heartImageView;
        TextView diachiTextView;
        TextView dientichTextView;
        TextView ngaythemTextView;
        ImageView moreImageView;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.carview_phongtro);
            imageView = itemView.findViewById(R.id.img_listphongtro);
            titleTextView = itemView.findViewById(R.id.title_listphongtro);
            giatienTextView = itemView.findViewById(R.id.giatienTro_listphongtro);
            checkImageView = itemView.findViewById(R.id.check);
            heartImageView = itemView.findViewById(R.id.heart);
            diachiTextView = itemView.findViewById(R.id.diachi_listphongtro);
            dientichTextView = itemView.findViewById(R.id.dientich_listphongtro);
            ngaythemTextView = itemView.findViewById(R.id.ngaythem_listphongtro);
            moreImageView = itemView.findViewById(R.id.more_listphongtro);
            // Initialize other views...
        }
    }

}
