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

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterStudentSelected extends RecyclerView.Adapter<CustomAdapterStudentSelected.viewHolderStudentSelected> {
    List<students> list ;
    Context context;
    payloadStudent payloadStudent;

    public CustomAdapterStudentSelected(List<students> list, Context context, com.betterschool.co.classContainer.insertClassContainer.adapter.payloadStudent payloadStudent) {
        this.list = list;
        this.context = context;
        this.payloadStudent = payloadStudent;
    }


    @NonNull
    @Override
    public viewHolderStudentSelected onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderStudentSelected(LayoutInflater.from(context).inflate(R.layout.item_student_selected,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderStudentSelected holder, int position) {
        students item = list.get(position);
        holder.fullName.setText(item.getName() + " " + item.getLastName());
        holder.remove.setOnClickListener(v -> payloadStudent.students(item));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderStudentSelected extends RecyclerView.ViewHolder {
        TextView fullName,remove;
        public viewHolderStudentSelected(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            remove = itemView.findViewById(R.id.remove);
        }
    }
}
