package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Model.TienNghi;
import com.example.rentappandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TienNghiAdapter extends RecyclerView.Adapter<TienNghiAdapter.TienNghiHolder>{
    private List<TienNghi> tienNghiList;
    private Context context;

    public TienNghiAdapter(List<TienNghi> tienNghiList, Context context) {
        this.tienNghiList = tienNghiList;
        this.context = context;
    }

    @NonNull
    @Override
    public TienNghiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tien_nghi_item, parent, false);
        return new TienNghiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TienNghiHolder holder, int position) {
        TienNghi tienNghi = tienNghiList.get(position);

        // Set data to your views
        holder.name.setText(tienNghi.getAmenity_name());

        Picasso.with(context)
                .load(tienNghi.getAmenity_img())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.apartment)
                .into(holder.img);

        holder.tiennghi_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change __v to 1 and background color
                if (tienNghi.get__v() == 0) {
                    tienNghi.set__v(1);
                    holder.tiennghi_item.setBackgroundColor(context.getResources().getColor(R.color.purple_200));
                } else {
                    tienNghi.set__v(0);
                    // Set the default background color here
                    holder.tiennghi_item.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                }
            }
        });

        // Set background color based on __v value
        if (tienNghi.get__v() == 1) {
            holder.tiennghi_item.setBackgroundColor(context.getResources().getColor(R.color.purple_200));
        } else {
            // Set the default background color here
            holder.tiennghi_item.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return tienNghiList.size();
    }
    class TienNghiHolder extends RecyclerView.ViewHolder{

        CardView tiennghi_item;
        ImageView img;
        TextView name;

        public TienNghiHolder(@NonNull View itemView) {
            super(itemView);
            tiennghi_item = itemView.findViewById(R.id.tiennghi_item);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
        }
    }
}
