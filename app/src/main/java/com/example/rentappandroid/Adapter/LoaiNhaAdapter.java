package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        if (loaiNha.get__v() == 1) {
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.purple_200));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change __v to 1 and background color
                if (loaiNha.get__v() == 0) {


                    for (LoaiNha loaiNha1:loaiNhaList){
                            if(loaiNha1.get__v() == 1 && loaiNha.get_id() != loaiNha1.get_id()){
                                Toast.makeText(context,"Vui lòng gỡ chọn loại nhà cũ", Toast.LENGTH_SHORT).show();
                                return;
                            }
                    }
                    loaiNha.set__v(1);
                    holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.purple_200));
                } else {
                    loaiNha.set__v(0);
                    // Set the default background color here
                    holder.cardView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                }
            }
        });
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
