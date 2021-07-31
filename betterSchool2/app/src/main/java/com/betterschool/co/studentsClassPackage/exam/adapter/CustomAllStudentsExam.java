package com.betterschool.co.studentsClassPackage.exam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.studentsClassPackage.exam.model.AllStudentsExamModelRoot;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAllStudentsExam extends RecyclerView.Adapter<CustomAllStudentsExam.viewHolderAllStudentsExam> {
    List<AllStudentsExamModelRoot> list;
    Context context;

    public CustomAllStudentsExam(List<AllStudentsExamModelRoot> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NotNull
    @Override
    public viewHolderAllStudentsExam onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderAllStudentsExam(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_exam,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderAllStudentsExam holder, int position) {
        AllStudentsExamModelRoot item = list.get(position);
        if(item.getStudent()!=null) {
            if(item.getStudent().getScore()!=null) {
                holder.score.setText("نمره اخذ شده : " + item.getStudent().getScore());
                holder.score.setBackgroundColor(context.getResources().getColor(R.color.primary));
            }
            else{
                holder.score.setText("نمره اخذ شده : ثبت نشده" );
                holder.score.setBackgroundColor(context.getResources().getColor(R.color.red));
            }
        }
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

    public static class viewHolderAllStudentsExam extends RecyclerView.ViewHolder {
        TextView title,startDate,type,score;
        public viewHolderAllStudentsExam(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            startDate = itemView.findViewById(R.id.startDate);
            type = itemView.findViewById(R.id.type);
            score = itemView.findViewById(R.id.score);
        }
    }
}
