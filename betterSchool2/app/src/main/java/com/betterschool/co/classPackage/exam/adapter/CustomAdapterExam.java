package com.betterschool.co.classPackage.exam.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.classPackage.exam.model.exams;
import com.betterschool.co.classPackage.mainPageClass.showSingleExam.ShowSingleExamActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterExam extends RecyclerView.Adapter<CustomAdapterExam.viewHolderExam> {
    List<exams> list;
    Context context;
    String id;
    String classContainerId;

    public CustomAdapterExam(List<exams> list, Context context, String id, String classContainerId) {
        this.list = list;
        this.context = context;
        this.id = id;
        this.classContainerId = classContainerId;
    }


    @NotNull
    @Override
    public viewHolderExam onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderExam(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolderExam holder, int position) {
        exams item = list.get(position);
        switch (item.getType()){
            case "class":
                holder.type.setText("امتحان : کلاسی");
                break;
            case "firstFinalExam":
                holder.type.setText("امتحان : نوبت اول");
                break;
            case "firstExam":
                holder.type.setText("امتحان : امتحان مستمر");
                break;
            case "secondFinalExam":
                holder.type.setText("امتحان : نوبت دوم");
                break;
            case "secondExam":
                holder.type.setText("امتحان : مستمر دوم");
                break;
        }
        holder.description.setText("توضیحات : " + item.getDescription());
        holder.title.setText("عنوان : " + item.getTitle());
        holder.startDate.setText("تاریخ امتحان : " + item.getStartDate());
        if(id!=null) {
            holder.cardExam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CustomAdapterExam.this.context, ShowSingleExamActivity.class);
                    intent.putExtra("hasUpdated", item.getHasUpdated());
                    intent.putExtra("idExam", item.get_id());
                    intent.putExtra("id", id);
                    intent.putExtra("classContainerId", classContainerId);
                    context.startActivity(intent);
                }
            });
        }
        if(item.getHasUpdated()){
            holder.examStatus.setText("نمره ها ثبت شده");
            holder.examStatus.setBackgroundColor(context.getResources().getColor(R.color.green));
        }else{
            holder.examStatus.setText("در انتظا ثبت نمره ها");
            holder.examStatus.setBackgroundColor(context.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderExam extends RecyclerView.ViewHolder {
        TextView type,startDate,title,description,examStatus;
        CardView cardExam;
        public viewHolderExam(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            startDate = itemView.findViewById(R.id.startDate);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            cardExam = itemView.findViewById(R.id.cardExam);
            examStatus = itemView.findViewById(R.id.examStatus);
        }
    }
}
