package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Model.LoaiNha;
import com.example.rentappandroid.R;
import java.util.List;

public class LoaiNhaAdapter extends RecyclerView.Adapter<LoaiNhaAdapter.LoaiNhaViewHolder> {

    private Context context;
    private List<LoaiNha> loaiNhaList;

    public LoaiNhaAdapter(Context context, List<LoaiNha> loaiNhaList) {
        this.context = context;
        this.loaiNhaList = loaiNhaList;
    }

    @NonNull
    @Override
    public LoaiNhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.loainha_item, parent, false);
        return new LoaiNhaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiNhaViewHolder holder, int position) {
        LoaiNha loaiNha = loaiNhaList.get(position);
        holder.nameTextView.setText(loaiNha.getTypehouse_name());
    }

    @Override
    public int getItemCount() {
        return loaiNhaList.size();
    }

    public class LoaiNhaViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nameTextView;

        public LoaiNhaViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.loainha_item);
            nameTextView = itemView.findViewById(R.id.name);
        }
    }
}
