package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentappandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RuleAdapter extends RecyclerView.Adapter<RuleAdapter.ViewHolder>{
    private List<String> rules;
    private Context context;

    public void setData(List<String> list){
        this.rules   = list;
        notifyDataSetChanged();
    }

    public RuleAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.rules = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rule_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ruleText = rules.get(position);

        // Set the text to the TextView in ViewHolder
        holder.rule.setText(ruleText);
        holder.deleteRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();

                // Ensure the position is valid
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Remove the item from the list
                    rules.remove(adapterPosition);

                    // Notify the adapter that an item has been removed
                    notifyItemRemoved(adapterPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rules.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView rule;
        ImageView deleteRule;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rule = itemView.findViewById(R.id.rule);
            deleteRule = itemView.findViewById(R.id.deleteRule);
        }
    }
}
