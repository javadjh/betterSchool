package com.betterschool.co.classContainer.classes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.teachers.model.teachers;

import java.util.List;

public class CustomAdapterTeachers extends RecyclerView.Adapter<CustomAdapterTeachers.viewHolderTeachers> {
    Context context;
    List<teachers> list ;
    payloadTeacher payloadTeacher;

    public CustomAdapterTeachers(Context context, List<teachers> list, com.betterschool.co.classContainer.classes.adapter.payloadTeacher payloadTeacher) {
        this.context = context;
        this.list = list;
        this.payloadTeacher = payloadTeacher;
    }

    @NonNull
    @Override
    public viewHolderTeachers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderTeachers(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teachers,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolderTeachers holder, int position) {
        teachers item = list.get(position);
        holder.fullName.setText(item.getName() + " " + item.getLastName());
        holder.title.setText("(" + item.getTitle()+")");
        holder.cardTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payloadTeacher.teacher(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderTeachers extends RecyclerView.ViewHolder {
        LinearLayout cardTeacher;
        TextView fullName,title;
        public viewHolderTeachers(@NonNull View itemView) {
            super(itemView);
            cardTeacher = itemView.findViewById(R.id.cardTeacher);
            fullName = itemView.findViewById(R.id.fullName);
            title = itemView.findViewById(R.id.title);
        }
    }
}
