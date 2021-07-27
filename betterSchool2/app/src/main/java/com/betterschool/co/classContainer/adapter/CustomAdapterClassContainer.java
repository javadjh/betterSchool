package com.betterschool.co.classContainer.adapter;

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
import com.betterschool.co.classContainer.classes.ClassesActivity;
import com.betterschool.co.classContainer.model.ClassContainerRoot;
import com.betterschool.co.classContainer.moveStudent.ModeStudentActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterClassContainer extends RecyclerView.Adapter<CustomAdapterClassContainer.viewHolderClassContainer> {
    Context context;
    List<ClassContainerRoot> list;

    public CustomAdapterClassContainer(Context context, List<ClassContainerRoot> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolderClassContainer onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderClassContainer(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_container,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderClassContainer holder, int position) {
        ClassContainerRoot item = list.get(position);
        holder.name.setText(item.getName());
        holder.classCount.setText("تعداد کلاس ها : " + item.getClasses().size() + "");
        holder.studentsCount.setText("تعداد دانش آموزان : " + item.getStudents().size() + "");
        holder.cardClassContainer.setOnClickListener(v -> {
            Intent intent = new Intent(CustomAdapterClassContainer.this.context, ClassesActivity.class);
            intent.putExtra("id",item.get_id());
            context.startActivity(intent);
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomAdapterClassContainer.this.context, ModeStudentActivity.class);
                intent.putExtra("id",item.get_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderClassContainer extends RecyclerView.ViewHolder {
        TextView name,classCount,studentsCount,edit;
        CardView cardClassContainer;
        public viewHolderClassContainer(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            classCount = itemView.findViewById(R.id.classCount);
            studentsCount = itemView.findViewById(R.id.studentsCount);
            edit = itemView.findViewById(R.id.edit);
            cardClassContainer = itemView.findViewById(R.id.cardClassContainer);
        }
    }
}
