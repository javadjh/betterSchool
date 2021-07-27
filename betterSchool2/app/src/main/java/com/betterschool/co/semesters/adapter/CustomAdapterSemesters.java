package com.betterschool.co.semesters.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.semesters.model.semesters;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterSemesters extends RecyclerView.Adapter<CustomAdapterSemesters.viewHolderSemesters> {
    List<semesters> list;

    public CustomAdapterSemesters(List<semesters> list) {
        this.list = list;
    }

    @NotNull
    @Override
    public viewHolderSemesters onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderSemesters(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teachers,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderSemesters holder, int position) {
        semesters item = list.get(position);
        holder.fullName.setText("کد سال تحصیلی : " + item.getName());
        holder.title.setText("(" + item.getCreateDate() + ")");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderSemesters extends RecyclerView.ViewHolder {
        TextView fullName,title;
        public viewHolderSemesters(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            title = itemView.findViewById(R.id.title);
        }
    }
}
