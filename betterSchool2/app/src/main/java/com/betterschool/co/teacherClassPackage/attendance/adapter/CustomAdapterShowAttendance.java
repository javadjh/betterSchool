package com.betterschool.co.teacherClassPackage.attendance.adapter;

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

public class CustomAdapterShowAttendance extends RecyclerView.Adapter<CustomAdapterShowAttendance.viewHolderShowAttendance> {
    List<students> list ;
    Context context;

    public CustomAdapterShowAttendance(List<students> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolderShowAttendance onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderShowAttendance(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendance,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderShowAttendance holder, int position) {
        students item = list.get(position);
        holder.fullName.setText(item.getName() + " " + item.getLastName());
        if(item.getPresent()){
            holder.percent.setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.absent.setBackgroundColor(context.getResources().getColor(R.color.white));
        }else{
            holder.absent.setBackgroundColor(context.getResources().getColor(R.color.red));
            holder.percent.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        if(item.getHasPositiveScore()){
            holder.positive.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
        if(item.getHasNegativeScore()){
            holder.negative.setBackgroundColor(context.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderShowAttendance extends RecyclerView.ViewHolder {
        TextView fullName;
        ImageView percent,absent,positive,negative;
        public viewHolderShowAttendance(@NonNull @NotNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            percent = itemView.findViewById(R.id.percent);
            absent = itemView.findViewById(R.id.absent);
            positive = itemView.findViewById(R.id.positive);
            negative = itemView.findViewById(R.id.negative);
        }
    }
}
