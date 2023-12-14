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
import com.example.rentappandroid.Model.Issue;
import com.example.rentappandroid.R;

import java.util.List;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.IssueViewHolder> {

    private Context context;
    private List<Issue> issueList;
    private String role;
    private String token;

    public IssueAdapter(Context context, List<Issue> issueList, String role, String token) {
        this.context = context;
        this.issueList = issueList;
        this.role = role;
        this.token = token;
    }

    @NonNull
    @Override
    public IssueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.issue_item, parent, false);
        return new IssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueViewHolder holder, int position) {
        Issue currentIssue = issueList.get(position);

        // Set data to your views
        holder.tenNguoiThue.setText("Tên người thuê: " + currentIssue.getUser().getName());
        holder.sdtNguoiThue.setText("SĐT người thuê: " + currentIssue.getUser().getPhoneNumber());
        holder.phongTroDuocThue.setText("Phòng: " + currentIssue.getRoom().getTitle() + " _ " + currentIssue.getRoom().getAddress().getFullAddress());
        holder.trangThaiIssue.setText("Trạng thái: " +currentIssue.getStatus());
        holder.ngayTao.setText("Ngày: " + currentIssue.getDate());
        holder.moTaIssue.setText("Mô tả: " + currentIssue.getDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(role.equals("ADMIN")){

                }else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return issueList.size();
    }

    public class IssueViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tenNguoiThue, sdtNguoiThue, phongTroDuocThue, trangThaiIssue, ngayTao, moTaIssue;

        public IssueViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.carview_issue);
            tenNguoiThue = itemView.findViewById(R.id.tennguoithue_issue);
            sdtNguoiThue = itemView.findViewById(R.id.sdtnguoithue_issue);
            phongTroDuocThue = itemView.findViewById(R.id.phongtroduocthue_issue);
            trangThaiIssue = itemView.findViewById(R.id.trangthaiissue);
            ngayTao = itemView.findViewById(R.id.ngaytao_issue);
            moTaIssue = itemView.findViewById(R.id.mota_issue);
        }
    }
}
