package com.example.eventmanagerapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.views.activities.GoogleMapActivity;

import java.util.List;

/**
 * Controller for category recycler adapter
 * Handles recycler display logic
 */
public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CustomViewHolder>{
    List<Category> data;
    public void setData(List<Category> data) {
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

        holder.cardView.setOnClickListener(v -> {
            String eventLocation = data.get(position).getEventLocation();
            String categoryName = data.get(position).getName();

            // TODO: Launch new MapsActivity with Country Name in extras
            Context context = holder.cardView.getContext();
            Intent intent = new Intent(context, GoogleMapActivity.class);
            intent.putExtra("location", eventLocation);
            intent.putExtra("name", categoryName);
            context.startActivity(intent);
        });
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
        public View cardView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            tvCategoryId = itemView.findViewById(R.id.ctv_categoryid);
            tvCategoryName = itemView.findViewById(R.id.ctv_categoryname);
            tvEventCount = itemView.findViewById(R.id.ctv_eventcount);
            tvIsActive = itemView.findViewById(R.id.ctv_isactive);
        }
    }
}

