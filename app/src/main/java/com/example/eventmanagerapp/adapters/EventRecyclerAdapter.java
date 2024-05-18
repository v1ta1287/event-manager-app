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
import com.example.eventmanagerapp.data.model.Event;
import com.example.eventmanagerapp.views.activities.EventGoogleResult;
import com.example.eventmanagerapp.views.activities.GoogleMapActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for event recycler adapter
 * Handles recycler display logic
 */
public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.CustomViewHolder> {
    List<Event> data;
    public void setData(List<Event> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card_layout, parent, false);
        return new CustomViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvEventId.setText(data.get(position).getEventId());
        holder.tvEventName.setText(data.get(position).getName());
        holder.tvCategoryId.setText(data.get(position).getCategoryId());
        holder.tvTicketsAvailable.setText(String.valueOf(data.get(position).getTicketsAvailable()));
        if (data.get(position).isActive()) {
            holder.tvIsActive.setText("Active");
        } else {
            holder.tvIsActive.setText("Inactive");
        }

        holder.cardView.setOnClickListener(v -> {
            String eventName = data.get(position).getName();
            Context context = holder.cardView.getContext();
            Intent intent = new Intent(context, EventGoogleResult.class);
            intent.putExtra("eventName", eventName);
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

        public TextView tvEventId;
        public TextView tvEventName;
        public TextView tvCategoryId;
        public TextView tvTicketsAvailable;
        public TextView tvIsActive;
        public View cardView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            tvEventId = itemView.findViewById(R.id.tv_eventid);
            tvEventName = itemView.findViewById(R.id.tv_eventname);
            tvCategoryId = itemView.findViewById(R.id.tv_categoryid);
            tvTicketsAvailable = itemView.findViewById(R.id.tv_ticketsavailable);
            tvIsActive = itemView.findViewById(R.id.tv_isactive);
        }
    }
}
