package com.betterschool.co.studentsClassPackage.exam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.studentsClassPackage.exam.model.studentsExamSt;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterImStudentsExam extends RecyclerView.Adapter<CustomAdapterImStudentsExam.viewHolderImStudentsExam> {
    List<studentsExamSt> list;

    public CustomAdapterImStudentsExam(List<studentsExamSt> list) {
        this.list = list;
    }

    @NotNull
    @Override
    public viewHolderImStudentsExam onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderImStudentsExam(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exams_student,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderImStudentsExam holder, int position) {
        studentsExamSt item = list.get(position);
        holder.date.setText(item.getDate());
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderImStudentsExam extends RecyclerView.ViewHolder {
        TextView name,date;
        public viewHolderImStudentsExam(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
        }
    }
}
