package com.example.rentappandroid.Adapter.Landlord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Model.Leasecontracts;
import com.example.rentappandroid.R;

import java.util.List;

public class NguoiThueAdapter extends RecyclerView.Adapter<NguoiThueAdapter.ViewHolder>{

    private Context context;
    private List<Leasecontracts> leasecontractsList;

    public NguoiThueAdapter(Context context, List<Leasecontracts> leasecontractsList) {
        this.context = context;
        this.leasecontractsList = leasecontractsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nguoithue_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Leasecontracts leasecontracts = leasecontractsList.get(position);

        // Set data to views
        holder.tenkhachhang_nguoithue.setText(leasecontracts.getTenant().getName());
        holder.sdt_nguoithue.setText(leasecontracts.getTenant().getPhoneNumber());
        holder.diachi_nguoithue.setText(leasecontracts.getRoomingHouse().getAddress().getFullAddress());
        holder.phongtro_nguoithue.setText(leasecontracts.getRoomingHouse().getTitle());
        holder.trangthai_nguoithue.setText(leasecontracts.isStatus() ? "Active" : "Inactive");

    }

    @Override
    public int getItemCount() {
        return leasecontractsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tenkhachhang_nguoithue, sdt_nguoithue, diachi_nguoithue, phongtro_nguoithue, trangthai_nguoithue;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tenkhachhang_nguoithue = itemView.findViewById(R.id.tenkhachhang_nguoithue);
            sdt_nguoithue = itemView.findViewById(R.id.sdt_nguoithue);
            diachi_nguoithue = itemView.findViewById(R.id.diachi_nguoithue);
            phongtro_nguoithue = itemView.findViewById(R.id.phongtro_nguoithue);
            trangthai_nguoithue = itemView.findViewById(R.id.trangthai_nguoithue);
            cardView = itemView.findViewById(R.id.carview_nguoithu);
        }
    }
}
