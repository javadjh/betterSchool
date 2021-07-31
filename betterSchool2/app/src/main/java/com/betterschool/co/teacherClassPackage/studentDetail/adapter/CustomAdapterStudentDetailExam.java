package com.betterschool.co.teacherClassPackage.studentDetail.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.teacherClassPackage.studentDetail.model.scores;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterStudentDetailExam extends RecyclerView.Adapter<CustomAdapterStudentDetailExam.viewHolderStudentDetailExam> {
    List<scores> list;

    public CustomAdapterStudentDetailExam(List<scores> list) {
        this.list = list;
    }

    @NotNull
    @Override
    public viewHolderStudentDetailExam onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderStudentDetailExam(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_exam,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderStudentDetailExam holder, int position) {
        scores item = list.get(position);
        holder.score.setText("نمره اخذ شده : " + item.getStudent().getScore());
        holder.startDate.setText("تاریخ برگزاری : " + item.getStartDate());
        switch (item.getType()){
            case "class":
                holder.type.setText("نوع : امتحان کلاسی");
                break;
            case "firstFinalExam":
                holder.type.setText("نوع : امتحان نوبت اول");
                break;
            case "firstExam":
                holder.type.setText("نوع : امتحان مستمر اول");
                break;
            case "secondFinalExam":
                holder.type.setText("نوع : امتحان نوبت دوم");
                break;
            case "secondExam":
                holder.type.setText("نوع : امتحان مستمر دوم");
                break;
        }
        holder.title.setText("عنوان : " + item.getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderStudentDetailExam extends RecyclerView.ViewHolder {
        TextView title,startDate,type,score;
        public viewHolderStudentDetailExam(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            startDate = itemView.findViewById(R.id.startDate);
            type = itemView.findViewById(R.id.type);
            score = itemView.findViewById(R.id.score);
        }
    }
}
