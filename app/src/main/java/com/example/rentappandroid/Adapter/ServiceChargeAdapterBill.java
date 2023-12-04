package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rentappandroid.Dto.Reponse.ServiceCharge;
import com.example.rentappandroid.R;
import java.util.List;
public class ServiceChargeAdapterBill extends RecyclerView.Adapter<ServiceChargeAdapterBill.ServiceChargeViewHolder>{
    private List<ServiceCharge> serviceChargeList;
    private Context context;

    public ServiceChargeAdapterBill(List<ServiceCharge> serviceChargeList, Context context) {
        this.serviceChargeList = serviceChargeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceChargeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_service_item, parent, false);
        return new ServiceChargeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceChargeViewHolder holder, int position) {
        ServiceCharge serviceCharge = serviceChargeList.get(position);

        holder.tendichvu.setText(serviceCharge.getServiceChargeId().getServicecharge_name());
        holder.giagoc.setText(String.valueOf(serviceCharge.getPrice()));

        holder.tong.setText("Tổng: ");
    }

    @Override
    public int getItemCount() {
        return serviceChargeList.size();
    }

    public static class ServiceChargeViewHolder extends RecyclerView.ViewHolder {

        TextView tendichvu, tong;
        EditText giagoc, soluong;

        public ServiceChargeViewHolder(@NonNull View itemView) {
            super(itemView);

            tendichvu = itemView.findViewById(R.id.tendichvu);
            giagoc = itemView.findViewById(R.id.giagoc);
            soluong = itemView.findViewById(R.id.soluong);
            tong = itemView.findViewById(R.id.tong);


        }
    }
}
