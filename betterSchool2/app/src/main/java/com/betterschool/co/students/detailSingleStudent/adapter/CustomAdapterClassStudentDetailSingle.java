package com.betterschool.co.students.detailSingleStudent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.students.detailSingleStudent.model.classesString;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterClassStudentDetailSingle extends RecyclerView.Adapter<CustomAdapterClassStudentDetailSingle.viewHolderClassStudentDetailSingle> {
    List<classesString> list;

    public CustomAdapterClassStudentDetailSingle(List<classesString> list) {
        this.list = list;
    }

    @NotNull
    @Override
    public viewHolderClassStudentDetailSingle onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderClassStudentDetailSingle(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_student_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderClassStudentDetailSingle holder, int position) {
        classesString item = list.get(position);
        holder.className.setText(item.getName());
        switch (item.getDayStart()){
            case 0:
                holder.classDayStart.setText("روز : شنبه");
                break;
            case 1:
                holder.classDayStart.setText("روز : یک شنبه");
                break;
            case 2:
                holder.classDayStart.setText("روز : دو شنبه");
                break;
            case 3:
                holder.classDayStart.setText("روز : سه شنبه");
                break;
            case 4:
                holder.classDayStart.setText("روز : چهار شنبه");
                break;
            case 5:
                holder.classDayStart.setText("روز : پنج شنبه");
                break;
            case 6:
                holder.classDayStart.setText("روز : جمعه");
                break;
        }
        holder.classTimeStart.setText("ساعت : " + item.getTimeStart());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderClassStudentDetailSingle extends RecyclerView.ViewHolder {
        TextView className,classDayStart,classTimeStart;
        public viewHolderClassStudentDetailSingle(@NonNull @NotNull View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.className);
            classDayStart = itemView.findViewById(R.id.classDayStart);
            classTimeStart = itemView.findViewById(R.id.classTimeStart);
        }
    }
}
