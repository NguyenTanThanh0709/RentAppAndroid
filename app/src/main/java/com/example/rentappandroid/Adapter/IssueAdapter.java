package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rentappandroid.Model.Issue;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiIssue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.IssueViewHolder> {

    private Context context;
    private List<Issue> issueList;
    private String role;
    private String token;

    public void setData(List<Issue> newIssueList){
        issueList = newIssueList;
    }



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
                    showPopupMenu(holder.cardView, currentIssue);
                }else {

                }
            }
        });
    }

    public void updateStatus(String issueId, String newStatus) {
        for (int i = 0; i < issueList.size(); i++) {

            if (issueList.get(i).get_id().equals(issueId)) {
                issueList.get(i).setStatus(newStatus);
                notifyItemChanged(i);
                break;
            }
        }
    }

    private void showPopupMenu(View view, Issue currentIssue) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.menudagiaiquyet, popupMenu.getMenu());

        // Set the initial checked state based on the current issue status
        MenuItem menuItem = null;

        if (currentIssue.getStatus().equals("UNRESOLVED")) {
            menuItem = popupMenu.getMenu().findItem(R.id.menuChuaGiaiQuyet);
        } else if (currentIssue.getStatus().equals("IN_PROGRESS")) {
            menuItem = popupMenu.getMenu().findItem(R.id.menuDangGiaiQuyet);
        } else if (currentIssue.getStatus().equals("RESOLVED")) {
            menuItem = popupMenu.getMenu().findItem(R.id.menuDaGiaiQuyet);
        }

        if (menuItem != null) {
            menuItem.setChecked(true);
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Handle menu item click here
                if (menuItem.getItemId() == R.id.menuChuaGiaiQuyet) {
                    ApiIssue.apiApiIssue.update(currentIssue.get_id(),"UNRESOLVED",token).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(currentIssue.get_id(), "UNRESOLVED");
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(currentIssue.get_id(), "RESOLVED");
                        }
                    });
                } else if (menuItem.getItemId() == R.id.menuDangGiaiQuyet) {
                    ApiIssue.apiApiIssue.update(currentIssue.get_id(),"IN_PROGRESS",token).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(currentIssue.get_id(), "IN_PROGRESS");
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(currentIssue.get_id(), "RESOLVED");
                        }
                    });
                } else if (menuItem.getItemId() == R.id.menuDaGiaiQuyet) {
                    ApiIssue.apiApiIssue.update(currentIssue.get_id(),"RESOLVED",token).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(currentIssue.get_id(), "RESOLVED");
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(context,"Thành Công", Toast.LENGTH_SHORT).show();
                            updateStatus(currentIssue.get_id(), "RESOLVED");
                        }
                    });
                }
                return true;
            }
        });

        popupMenu.show();
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
