package com.example.rentappandroid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Model.Message;
import com.example.rentappandroid.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    private List<Message> messages;

    public MessageAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(Message message) {
        messages.add(message);
        notifyDataSetChanged();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView senderNameTextView;
        private TextView receiverNameTextView;
        private TextView messageContentTextView;
        private TextView timestampTextView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            senderNameTextView = itemView.findViewById(R.id.tenkhachhang_mess);
            receiverNameTextView = itemView.findViewById(R.id.tenchu_mess);
            messageContentTextView = itemView.findViewById(R.id.noidung_mess);
            timestampTextView = itemView.findViewById(R.id.thoigian_mess);
        }

        public void bind(Message message) {
            senderNameTextView.setText("Tên Người Thuê: " + message.getNamet());
            receiverNameTextView.setText("Tên chủ trọ: " + message.getNamel());
            messageContentTextView.setText("Nội dung: " + message.getContent());
            timestampTextView.setText(String.valueOf(message.getTimestamp()));
        }
    }
}
