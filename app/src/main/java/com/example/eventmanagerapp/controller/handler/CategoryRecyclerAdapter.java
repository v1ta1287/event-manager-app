package com.example.eventmanagerapp.controller.handler;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.model.Category;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CustomViewHolder>{
    ArrayList<Category> data = new ArrayList<>();
    public void setData(ArrayList<Category> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public CategoryRecyclerAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card_layout, parent, false);
        return new CustomViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvCategoryId.setText(data.get(position).getCategoryId());
        holder.tvCategoryName.setText(data.get(position).getName());
        holder.tvEventCount.setText(String.valueOf(data.get(position).getEventCount()));
        if (data.get(position).isActive()) {
            holder.tvIsActive.setText("Yes");
        } else {
            holder.tvIsActive.setText("No");
        }
    }

    @Override
    public int getItemCount() {
        if (this.data != null) { // if data is not null
            return this.data.size(); // then return the size of ArrayList
        }

        // else return zero if data is null
        return 0;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCategoryId;
        public TextView tvCategoryName;
        public TextView tvEventCount;
        public TextView tvIsActive;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryId = itemView.findViewById(R.id.ctv_categoryid);
            tvCategoryName = itemView.findViewById(R.id.ctv_categoryname);
            tvEventCount = itemView.findViewById(R.id.ctv_eventcount);
            tvIsActive = itemView.findViewById(R.id.ctv_isactive);
        }
    }
}

