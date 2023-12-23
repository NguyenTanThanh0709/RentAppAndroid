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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Activity.Landlord.FORMADD.FormToaNhaActivity;
import com.example.rentappandroid.Dto.Reponse.RoomReponseComplex;
import com.example.rentappandroid.Dto.Reponse.RoomingHouseComplex;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiRoomingHouseComplex;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToaNhaAdapter extends RecyclerView.Adapter<ToaNhaAdapter.ViewHolder>{
    private List<RoomingHouseComplex> roomingHouseComplexList;
    private Context context;
private String token;
    public ToaNhaAdapter(List<RoomingHouseComplex> roomingHouseComplexList, Context context) {
        this.roomingHouseComplexList = roomingHouseComplexList;
        this.context = context;
    }

    public ToaNhaAdapter(List<RoomingHouseComplex> roomingHouseComplexList, Context context, String token) {
        this.roomingHouseComplexList = roomingHouseComplexList;
        this.context = context;
        this.token = token;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toa_nha_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoomingHouseComplex roomingHouseComplex = roomingHouseComplexList.get(position);
        Picasso.with(context)
                .load(roomingHouseComplex.getImage_url().get(0))
                .placeholder(R.drawable.tower)
                .error(R.drawable.tower)
                .into(holder.imageView);
        holder.textViewName.setText(roomingHouseComplex.getRoomingHouseComplex_name());
        holder.textViewAddress.setText(roomingHouseComplex.getAddress().getFullAddress());

        holder.more_toanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view, roomingHouseComplex);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomingHouseComplexList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView more_toanha;
        TextView textViewName;
        TextView textViewAddress;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_toanha_item);
            textViewName = itemView.findViewById(R.id.name_toanha);
            textViewAddress = itemView.findViewById(R.id.address_toanha);
            cardView = itemView.findViewById(R.id.carview_toanha);
            more_toanha = itemView.findViewById(R.id.more_toanha);
        }
    }

    private void showPopupMenu(View view, RoomingHouseComplex roomingHouseComplex) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.menu_more_toanha); // Replace with your menu resource

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();

                if (itemId == R.id.menu_edit_see_toanha) {


                    Intent intent = new Intent(context, FormToaNhaActivity.class);
                    intent.putExtra("roomingHouseComplex", roomingHouseComplex.get_id());
                    intent.putExtra("type", "edit");
                    context.startActivity(intent);
                    return true;

                }else if (itemId == R.id.menu_delete_toanha) {
                    showDeleteConfirmationDialog(roomingHouseComplex.get_id());
                    return true;
                } else {
                    return false;
                }
            }
        });

        popupMenu.show();
    }

    private void showDeleteConfirmationDialog(String email) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this Tower?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ApiRoomingHouseComplex.apiRoomingHouseComplex.delete(email,token).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            int position = -1;
                            for (int i = 0; i < roomingHouseComplexList.size(); i++) {
                                if (email.equals(roomingHouseComplexList.get(i).get_id())) {
                                    position = i;
                                    break;
                                }
                            }

                            if (position != -1) {
                                roomingHouseComplexList.remove(position);
                                notifyItemRemoved(position);
                            } else {
                                // Handle the case where the item to be removed is not found
                            }
                        } else {
                            // Handle unsuccessful response
                        }

                        Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context, "Xóa không Thành Công", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
