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

import com.example.rentappandroid.Activity.Landlord.FORMLIST.QuanLyHopDongActivity;
import com.example.rentappandroid.Activity.MessageActivity;
import com.example.rentappandroid.Model.ListConversation;
import com.example.rentappandroid.R;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>{
    private final Context context;
    private final List<ListConversation> conversations;

    public ConversationAdapter(Context context, List<ListConversation> conversations) {
        this.context = context;
        this.conversations = conversations;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tin_nhan_item, parent, false);
        return new ConversationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        ListConversation conversation = conversations.get(position);

        // Set data to your views
        if(conversation.getTenant() == null){
            holder.tenKhachHang.setText("");
        }else {
            holder.tenKhachHang.setText(conversation.getTenant().getName());
        }

        if(conversation.getLandrod() == null){
            holder.tenChuTro.setText("");
        }else {
            holder.tenChuTro.setText(conversation.getLandrod().getName());
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conversation.getTenantId() != null && conversation.getLandrodId() != null){
                    Intent intent = new Intent(context, MessageActivity.class);

                    intent.putExtra("id", conversation.getLandrodId() + "_" + conversation.getTenantId());
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public  class ConversationViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tenKhachHang;
        TextView tenChuTro;

        public ConversationViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.carview_tinhan);
            tenKhachHang = itemView.findViewById(R.id.tenkhachhang_tinnhan);
            tenChuTro = itemView.findViewById(R.id.tenchu_tinhan);
        }
    }
}
