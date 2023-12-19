package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.Model.CommentReview;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiComment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

    private List<CommentReview> commentList;
    private Context context;
    private String token;
    private String type ="";

    public CommentAdapter(List<CommentReview> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

    public CommentAdapter(List<CommentReview> commentList, Context context, String token, String type) {
        this.commentList = commentList;
        this.context = context;
        this.token = token;
        this.type = type;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        CommentReview comment = commentList.get(position);

        holder.tenKhachHang.setText(comment.getTenant().getName());
        holder.noiDungComment.setText(comment.getContent());
        if (comment.getRating() <= 3) {
            holder.ratingComment.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
        } else {
            // Set a different color or handle other cases if needed
            holder.ratingComment.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }
        holder.ratingComment.setText(String.valueOf(comment.getRating()));
        holder.ngayComment.setText(comment.getComment_date());

        if (type.equals("")) {
            // If type is empty, hide the delete_comment ImageView
            holder.delete_comment.setVisibility(View.GONE);
        } else {
            // If type is not empty, show the delete_comment ImageView
            holder.delete_comment.setVisibility(View.VISIBLE);

            // Set a click listener for delete_comment
            holder.delete_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApiComment.apiApiComment.deleteCommentReview(comment.get_id(), token).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            commentList.remove(comment);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            commentList.remove(comment);
                            notifyDataSetChanged();
                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView tenKhachHang;
        TextView noiDungComment;
        TextView ratingComment;
        TextView ngayComment;
        ImageView delete_comment;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKhachHang = itemView.findViewById(R.id.tenkhachhang_comment);
            noiDungComment = itemView.findViewById(R.id.noidung_comment);
            ratingComment = itemView.findViewById(R.id.rating_comment);
            ngayComment = itemView.findViewById(R.id.ngay_comment);
            delete_comment= itemView.findViewById(R.id.delete_comment);
        }
    }
}
