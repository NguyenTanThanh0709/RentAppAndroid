package com.example.rentappandroid.Adapter.Landlord;

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

import com.example.rentappandroid.Activity.Landlord.FORMADD.FormContractActivity;
import com.example.rentappandroid.Activity.Landlord.FORMADD.FormPostActivity;
import com.example.rentappandroid.Activity.Tenant.DetailInfoRoomActivity;
import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.R;

import java.util.List;

public class LeaseContractAdapter extends RecyclerView.Adapter<LeaseContractAdapter.ViewHolder>{

    private Context context;
    private  List<Leasecontracts> leaseContractsList;
    private String role;
    private String token;

    public LeaseContractAdapter(Context context, List<Leasecontracts> leaseContractsList,String role, String token) {
        this.context = context;
        this.leaseContractsList = leaseContractsList;
        this.role = role;
        this.token = token;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hopdong_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Leasecontracts leaseContract = leaseContractsList.get(position);
        holder.tennguoithue.setText("Tên người thuê: " + leaseContract.getTenant().getName());
        holder.sdtnguoithue.setText("SĐT người thuê: " + leaseContract.getTenant().getPhoneNumber());
        holder.phongtroduocthue.setText("Phòng trọ: " + leaseContract.getRoomingHouse().getTitle() + " - " + leaseContract.getRoomingHouse().get_id());
        holder.trangthaihopdong.setText("Trạng thái hợp đồng: " + (leaseContract.isStatus() ? "Còn thời hạn" : "Hợp đồng hết hạn"));
        holder.ngaytaohopdong.setText("Ngày tạo hợp đồng: " + leaseContract.getCreate_date());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(role.equals("ADMIN")){
                    Intent intent = new Intent(context, FormContractActivity.class);
                    intent.putExtra("idContract", leaseContract.get_id());
                    context.startActivity(intent);
                }else {
//                    Intent intent = new Intent(context, DetailInfoRoomActivity.class);
//                    intent.putExtra("ID_POST", baiViet.get_id());
//                    intent.putExtra("token", token);
//                    context.startActivity(intent);

                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return leaseContractsList.size();
    }

    public void setRoomList(List<Leasecontracts> list) {
        this.leaseContractsList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView tennguoithue;
        private final TextView sdtnguoithue;
        private final TextView phongtroduocthue;
        private final TextView trangthaihopdong;
        private final TextView ngaytaohopdong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.carview_hopdong);
            tennguoithue = itemView.findViewById(R.id.tennguoithue);
            sdtnguoithue = itemView.findViewById(R.id.sdtnguoithue);
            phongtroduocthue = itemView.findViewById(R.id.phongtroduocthue);
            trangthaihopdong = itemView.findViewById(R.id.trangthaihopdong);
            ngaytaohopdong = itemView.findViewById(R.id.ngaytaohopdong);
        }
    }
}
