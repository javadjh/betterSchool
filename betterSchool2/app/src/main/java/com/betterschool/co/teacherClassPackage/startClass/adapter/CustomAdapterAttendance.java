package com.betterschool.co.teacherClassPackage.startClass.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.students.model.students;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterAttendance extends RecyclerView.Adapter<CustomAdapterAttendance.viewHolderAttendance> {
    List<students> list;
    Context context;
    changeStudentStatus changeStudentStatus;

    public CustomAdapterAttendance(List<students> list, Context context, com.betterschool.co.teacherClassPackage.startClass.adapter.changeStudentStatus changeStudentStatus) {
        this.list = list;
        this.context = context;
        this.changeStudentStatus = changeStudentStatus;
    }

    @NonNull
    @Override
    public viewHolderAttendance onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderAttendance(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendance,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderAttendance holder, int position) {
        students item = list.get(position);
        holder.fullName.setText(item.getName() + " " + item.getLastName());
        holder.percent.setOnClickListener(v -> {
            holder.percent.setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.absent.setBackgroundColor(context.getResources().getColor(R.color.white));
            item.setPresent(true);
            changeStudentStatus.onChange(item.getPresent(),item.getHasNegativeScore(),item.getHasPositiveScore(),item.get_id());
        });
        holder.absent.setOnClickListener(v -> {
            holder.absent.setBackgroundColor(context.getResources().getColor(R.color.red));
            holder.percent.setBackgroundColor(context.getResources().getColor(R.color.white));
            item.setPresent(false);
            changeStudentStatus.onChange(item.getPresent(),item.getHasNegativeScore(),item.getHasPositiveScore(),item.get_id());
        });
        holder.positive.setOnClickListener(v -> {
            holder.positive.setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.negative.setBackgroundColor(context.getResources().getColor(R.color.white));
            item.setHasPositiveScore(true);
            item.setHasNegativeScore(false);
            changeStudentStatus.onChange(item.getPresent(),item.getHasNegativeScore(),item.getHasPositiveScore(),item.get_id());
        });
        holder.negative.setOnClickListener(v -> {
            holder.negative.setBackgroundColor(context.getResources().getColor(R.color.red));
            holder.positive.setBackgroundColor(context.getResources().getColor(R.color.white));
            item.setHasPositiveScore(false);
            item.setHasNegativeScore(true);
            changeStudentStatus.onChange(item.getPresent(),item.getHasNegativeScore(),item.getHasPositiveScore(),item.get_id());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderAttendance extends RecyclerView.ViewHolder {
        TextView fullName;
        ImageView percent,absent,positive,negative;
        public viewHolderAttendance(@NonNull @NotNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            percent = itemView.findViewById(R.id.percent);
            absent = itemView.findViewById(R.id.absent);
            positive = itemView.findViewById(R.id.positive);
            negative = itemView.findViewById(R.id.negative);
        }
    }
}
