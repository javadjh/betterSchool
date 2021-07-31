package com.betterschool.co.weeklySchedule.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.classContainer.model.classes;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterWeeklySchedule extends RecyclerView.Adapter<CustomAdapterWeeklySchedule.viewHolderWeeklySchedule> {
    List<classes> list;

    public CustomAdapterWeeklySchedule(List<classes> list) {
        this.list = list;
    }

    @NotNull
    @Override
    public viewHolderWeeklySchedule onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderWeeklySchedule(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderWeeklySchedule holder, int position) {
        classes item = list.get(position);
        holder.className.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderWeeklySchedule extends RecyclerView.ViewHolder {
        TextView className;
        public viewHolderWeeklySchedule(@NonNull  View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.className);
        }
    }
}
