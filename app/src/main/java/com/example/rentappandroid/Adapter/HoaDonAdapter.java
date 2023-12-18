package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Activity.Landlord.FORMADD.FormBillActivity;
import com.example.rentappandroid.Activity.Tenant.HoaDon.InfoBillTenantActivity;
import com.example.rentappandroid.Model.HoaDon;
import com.example.rentappandroid.R;

import java.util.Calendar;
import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder>{

    private List<HoaDon> hoaDonList;
    private Context context;
    private String role;
    private String token;
    private String type;

    public HoaDonAdapter(List<HoaDon> hoaDonList, Context context,String role, String token) {
        this.hoaDonList = hoaDonList;
        this.context = context;
        this.token = token;
        this.role = role;
    }

    public HoaDonAdapter(List<HoaDon> hoaDonList, Context context,String role, String token, String type) {
        this.hoaDonList = hoaDonList;
        this.context = context;
        this.token = token;
        this.role = role;
        this.type = type;
    }

    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hoadon_item, parent, false);
        return new HoaDonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        HoaDon hoaDon = hoaDonList.get(position);

        holder.tenNguoiThue.setText("Tên người thuê: " + hoaDon.getLeaseContract().getTenant().getName());
        holder.sdtNguoiThue.setText("SĐT người thuê: " + hoaDon.getLeaseContract().getTenant().getPhoneNumber());
        holder.phongTroDuocThue.setText("Phòng trọ: " + hoaDon.getLeaseContract().getRoomingHouse().getTitle() + " - " + hoaDon.getLeaseContract().getRoomingHouse().get_id());
        holder.trangThaiHopDong.setText("Trạng thái hợp đồng: " + hoaDon.getLeaseContract().isStatus());
        holder.amount.setText("Giá tiền: " + hoaDon.getAmount());
        holder.ngayTaoHoaDon.setText("Hóa đơn ngày: " + hoaDon.getPayment_date());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(type.equals("see")){
                    Intent intent = new Intent(context, InfoBillTenantActivity.class);
                    intent.putExtra("idBill", hoaDon.get_id());
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, FormBillActivity.class);
                    intent.putExtra("idBill", hoaDon.get_id());
                    context.startActivity(intent);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public void setRoomList(List<HoaDon> temp) {
        hoaDonList = temp;
    }

    public static class HoaDonViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tenNguoiThue, sdtNguoiThue, phongTroDuocThue, trangThaiHopDong, amount, ngayTaoHoaDon;

        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);

            tenNguoiThue = itemView.findViewById(R.id.tennguoithue_bill);
            sdtNguoiThue = itemView.findViewById(R.id.sdtnguoithue_bill);
            phongTroDuocThue = itemView.findViewById(R.id.phongtroduocthue_bill);
            trangThaiHopDong = itemView.findViewById(R.id.trangthaihopdong);
            amount = itemView.findViewById(R.id.amount_bill);
            ngayTaoHoaDon = itemView.findViewById(R.id.ngaytaohoadon_bill);
            cardView = itemView.findViewById(R.id.carview_hoadon);

            // You can find other views here as well
        }
    }
}
