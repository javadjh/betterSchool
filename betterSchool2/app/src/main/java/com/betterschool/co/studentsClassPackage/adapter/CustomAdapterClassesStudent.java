package com.betterschool.co.studentsClassPackage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.classContainer.model.classes;
import com.betterschool.co.studentsClassPackage.mainPageStudentsClass.MainPageStudentsClassActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterClassesStudent extends RecyclerView.Adapter<CustomAdapterClassesStudent.viewHolderClassesStudent> {
    List<classes> list;
    Context context;

    public CustomAdapterClassesStudent(List<classes> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NotNull
    @Override
    public viewHolderClassesStudent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderClassesStudent(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_student,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderClassesStudent holder, int position) {
        classes item = list.get(position);
        holder.name.setText(item.getName());
        holder.timeStart.setText("ساعت : " + item.getTimeStart());
        holder.classContainerName.setText(item.getTeacher().getName() + " " + item.getTeacher().getLastName());
        switch (item.getDayStart()){
            case 0:
                holder.classBlock.setBackgroundColor(context.getResources().getColor(R.color.green));
                holder.dayTitle.setText("شنبه");
                break;
            case 1:
                holder.classBlock.setBackgroundColor(context.getResources().getColor(R.color.or));
                holder.dayTitle.setText("یکشنبه");
                break;
            case 2:
                holder.classBlock.setBackgroundColor(context.getResources().getColor(R.color.red));
                holder.dayTitle.setText("دوشنبه");
                break;
            case 3:
                holder.classBlock.setBackgroundColor(context.getResources().getColor(R.color.black));
                holder.dayTitle.setText("سه شنبه");
                break;
            case 4:
                holder.classBlock.setBackgroundColor(context.getResources().getColor(R.color.yellow));
                holder.dayTitle.setText("چهار شنبه");
                break;
            case 5:
                holder.classBlock.setBackgroundColor(context.getResources().getColor(R.color.purple_500));
                holder.dayTitle.setText("پنجشنبه");
                break;
            case 6:
                holder.classBlock.setBackgroundColor(context.getResources().getColor(R.color.teal_700));
                holder.dayTitle.setText("جمعه");
                break;
        }
        holder.classBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomAdapterClassesStudent.this.context, MainPageStudentsClassActivity.class);
                intent.putExtra("id",item.get_id());
                intent.putExtra("classContainer",item.getClassContainer());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderClassesStudent extends RecyclerView.ViewHolder {
        TextView dayTitle,name,timeStart,classContainerName;
        RelativeLayout classBlock;

        public viewHolderClassesStudent(@NonNull View itemView) {
            super(itemView);
            dayTitle = itemView.findViewById(R.id.dayTitle);
            name = itemView.findViewById(R.id.name);
            timeStart = itemView.findViewById(R.id.timeStart);
            classContainerName = itemView.findViewById(R.id.classContainerName);
            classBlock = itemView.findViewById(R.id.classBlock);
        }
    }
}
