package com.betterschool.co.classContainer.insertClassContainer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.students.model.students;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterAllStudents extends RecyclerView.Adapter<CustomAdapterAllStudents.viewHolderAllStudents> {
    Context context;
    List<students> list ;
    payloadStudent payloadStudent;

    public CustomAdapterAllStudents(Context context, List<students> list, com.betterschool.co.classContainer.insertClassContainer.adapter.payloadStudent payloadStudent) {
        this.context = context;
        this.list = list;
        this.payloadStudent = payloadStudent;
    }

    @NotNull
    @Override
    public viewHolderAllStudents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderAllStudents(LayoutInflater.from(context).inflate(R.layout.item_students,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull  viewHolderAllStudents holder, int position) {
        students item = list.get(position);
        holder.fullName.setText(item.getName() + " " + item.getLastName());
        holder.fullName.setOnClickListener(v -> payloadStudent.students(item));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderAllStudents extends RecyclerView.ViewHolder {
        TextView fullName;
        public viewHolderAllStudents(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
        }
    }
}
