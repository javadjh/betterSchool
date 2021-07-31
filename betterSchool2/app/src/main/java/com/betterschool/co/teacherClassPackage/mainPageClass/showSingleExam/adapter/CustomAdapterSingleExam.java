package com.betterschool.co.teacherClassPackage.mainPageClass.showSingleExam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.students.model.students;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterSingleExam extends RecyclerView.Adapter<CustomAdapterSingleExam.viewHolderSingleExam> {
    List<students> list;
    Context context;
    payloadStudentExam payloadStudentExam;
    Boolean isUpdated;

    public CustomAdapterSingleExam(List<students> list, Context context, com.betterschool.co.teacherClassPackage.mainPageClass.showSingleExam.adapter.payloadStudentExam payloadStudentExam, Boolean isUpdated) {
        this.list = list;
        this.context = context;
        this.payloadStudentExam = payloadStudentExam;
        this.isUpdated = isUpdated;
    }


    @NotNull
    @Override
    public viewHolderSingleExam onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderSingleExam(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set_score_student,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderSingleExam holder, int position) {
        students item = list.get(position);
        holder.fullName.setText(item.getName() + " " + item.getLastName());
        if(isUpdated){
            holder.score.setText(item.getScore());
            holder.setScore.setVisibility(View.GONE);
            holder.score.setEnabled(false);
        }else{
            holder.setScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setScore(holder.score.getText().toString());
                    holder.score.setBackground(context.getResources().getDrawable(R.drawable.gree_btn));
                    holder.setScore.setBackground(context.getResources().getDrawable(R.drawable.gree_btn));
                    payloadStudentExam.onChange(item);
                }
            });
            if(item.getScore()==null){
                holder.score.setBackground(context.getResources().getDrawable(R.drawable.search_style));
                holder.setScore.setBackground(context.getResources().getDrawable(R.drawable.search_style));
            }else{
                holder.score.setBackground(context.getResources().getDrawable(R.drawable.gree_btn));
                holder.setScore.setBackground(context.getResources().getDrawable(R.drawable.gree_btn));
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderSingleExam extends RecyclerView.ViewHolder {
        TextView fullName,setScore;
        EditText score;
        public viewHolderSingleExam(@NonNull @NotNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            setScore = itemView.findViewById(R.id.setScore);
            score = itemView.findViewById(R.id.score);
        }
    }
}
