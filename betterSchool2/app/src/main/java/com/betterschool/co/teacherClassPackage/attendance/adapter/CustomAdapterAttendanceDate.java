package com.betterschool.co.teacherClassPackage.attendance.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.teacherClassPackage.attendance.AttendanceActivity;
import com.betterschool.co.teacherClassPackage.attendance.model.attendanceRoot;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterAttendanceDate extends RecyclerView.Adapter<CustomAdapterAttendanceDate.viewHolderAttendance> {
    Context context;
    List<attendanceRoot> list ;

    public CustomAdapterAttendanceDate(Context context, List<attendanceRoot> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolderAttendance onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderAttendance(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_text,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderAttendance holder, int position) {
        attendanceRoot item = list.get(position);
        holder.title.setText(item.getCreateDate());
        holder.title.setOnClickListener(v -> {
            Intent intent = new Intent(CustomAdapterAttendanceDate.this.context, AttendanceActivity.class);
            intent.putExtra("id",item.get_id());
            intent.putExtra("classContainerId",item.getClassContainerId());
            context.startActivity(intent);
        });
        holder.studentsAbsent.setText("غائب ها : ");
        for (int i = 0 ; i<item.getStudents().size() ; i++){
            if(!item.getStudents().get(i).getPresent()) {
                holder.studentsAbsent.setText(holder.studentsAbsent.getText().toString()  + item.getStudents().get(i).getName() + " " + item.getStudents().get(i).getLastName() + " ، ");
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderAttendance extends RecyclerView.ViewHolder {
        TextView title,studentsAbsent;
        public viewHolderAttendance(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            studentsAbsent = itemView.findViewById(R.id.studentsAbsent);
        }
    }
}
