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
    }

    @Override
    public int getItemCount() {
        return tienNghiList.size();
    }
    class TienNghiHolder extends RecyclerView.ViewHolder{

        private CardView tiennghi_item;
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
