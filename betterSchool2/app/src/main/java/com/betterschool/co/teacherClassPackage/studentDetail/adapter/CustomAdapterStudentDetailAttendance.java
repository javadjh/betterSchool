package com.betterschool.co.teacherClassPackage.studentDetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.teacherClassPackage.studentDetail.model.attendance;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterStudentDetailAttendance extends RecyclerView.Adapter<CustomAdapterStudentDetailAttendance.viewHolderStudentDetailAttendance> {
    List<attendance> list;

    public CustomAdapterStudentDetailAttendance(List<attendance> list) {
        this.list = list;
    }

    @NotNull
    @Override
    public viewHolderStudentDetailAttendance onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderStudentDetailAttendance(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_text,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderStudentDetailAttendance holder, int position) {
        attendance item = list.get(position);
        holder.title.setText(item.getCreateDate());
        holder.studentsAbsent.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderStudentDetailAttendance extends RecyclerView.ViewHolder {
        TextView studentsAbsent,title;
        public viewHolderStudentDetailAttendance(@NonNull @NotNull View itemView) {
            super(itemView);
            studentsAbsent = itemView.findViewById(R.id.studentsAbsent);
            title = itemView.findViewById(R.id.title);
        }
    }
}
