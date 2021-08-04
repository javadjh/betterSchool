package com.betterschool.co.students.detailSingleStudent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.students.detailSingleStudent.model.exams;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterExamSingleStudentDetail extends RecyclerView.Adapter<CustomAdapterExamSingleStudentDetail.viewHolderExamSingleStudentDetail> {
    List<exams> list;

    public CustomAdapterExamSingleStudentDetail(List<exams> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolderExamSingleStudentDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderExamSingleStudentDetail(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderExamSingleStudentDetail holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class viewHolderExamSingleStudentDetail extends RecyclerView.ViewHolder {
        public viewHolderExamSingleStudentDetail(@NonNull View itemView) {
            super(itemView);
        }
    }
}
