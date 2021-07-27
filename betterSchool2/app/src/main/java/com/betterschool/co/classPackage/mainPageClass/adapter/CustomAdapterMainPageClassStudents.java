package com.betterschool.co.classPackage.mainPageClass.adapter;

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

public class CustomAdapterMainPageClassStudents extends RecyclerView.Adapter<CustomAdapterMainPageClassStudents.viewHolderMainPageClassStudents> {
    Context context;
    List<students> list;

    public CustomAdapterMainPageClassStudents(Context context, List<students> list) {
        this.context = context;
        this.list = list;
    }

    @NotNull
    @Override
    public viewHolderMainPageClassStudents onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderMainPageClassStudents(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderMainPageClassStudents holder, int position) {
        students item = list.get(position);
        holder.name.setText("نام : " + item.getName());
        holder.lastName.setText("فامیلی : " + item.getLastName());
        holder.melliCode.setText("ملی : " + item.getMelliCode());
        holder.fathersName.setText("پدر : " + item.getFathersName());
        holder.grade.setText("سطح : " + item.getGrade());
        holder.createDate.setText("ثبت : " + item.getCreateDate());
        holder.indexCounter.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderMainPageClassStudents extends RecyclerView.ViewHolder {
        TextView name,lastName,melliCode,fathersName,grade,createDate,indexCounter;
        public viewHolderMainPageClassStudents(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            lastName = itemView.findViewById(R.id.lastName);
            melliCode = itemView.findViewById(R.id.melliCode);
            fathersName = itemView.findViewById(R.id.fathersName);
            grade = itemView.findViewById(R.id.grade);
            createDate = itemView.findViewById(R.id.createDate);
            indexCounter = itemView.findViewById(R.id.indexCounter);
        }
    }
}
