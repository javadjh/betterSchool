package com.betterschool.co.teacherClassPackage.adapter;

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
import com.betterschool.co.teacherClassPackage.mainPageClass.MainPageClassActivity;
import com.betterschool.co.teacherClassPackage.model.teachersClassesRoot;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterTeachersClass extends RecyclerView.Adapter<CustomAdapterTeachersClass.viewHolderTeachersClass> {
    List<teachersClassesRoot> list ;
    Context context;

    public CustomAdapterTeachersClass(List<teachersClassesRoot> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NotNull
    @Override
    public viewHolderTeachersClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderTeachersClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher_classes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderTeachersClass holder, int position) {
        teachersClassesRoot item = list.get(position);
        holder.ClassContainerName.setText(item.getClassContainer().getName());
        holder.className.setText(item.getName());
        holder.timeStart.setText(item.getTimeStart());
        switch (item.getDayStart()){
            case 0:
                holder.dayShower.setBackgroundColor(context.getResources().getColor(R.color.green));
                holder.dayTitle.setText("شنبه");
                break;
            case 1:
                holder.dayShower.setBackgroundColor(context.getResources().getColor(R.color.or));
                holder.dayTitle.setText("یکشنبه");
                break;
            case 2:
                holder.dayShower.setBackgroundColor(context.getResources().getColor(R.color.red));
                break;
            case 3:
                holder.dayShower.setBackgroundColor(context.getResources().getColor(R.color.black));
                holder.dayTitle.setText("دوشنبه");
                break;
            case 4:
                holder.dayShower.setBackgroundColor(context.getResources().getColor(R.color.yellow));
                holder.dayTitle.setText("سه شنبه");
                break;
            case 5:
                holder.dayShower.setBackgroundColor(context.getResources().getColor(R.color.purple_500));
                holder.dayTitle.setText("چهارشنبه");
                break;
            case 6:
                holder.dayShower.setBackgroundColor(context.getResources().getColor(R.color.teal_700));
                holder.dayTitle.setText("جمعه");
                break;
        }
        holder.classBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomAdapterTeachersClass.this.context, MainPageClassActivity.class);
                intent.putExtra("id",item.get_id());
                intent.putExtra("classContainerId",item.getClassContainer().get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderTeachersClass extends RecyclerView.ViewHolder {
        TextView className,timeStart,ClassContainerName,dayTitle;
        LinearLayout dayShower;
        RelativeLayout classBlock;
        public viewHolderTeachersClass(@NonNull View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.className);
            timeStart = itemView.findViewById(R.id.timeStart);
            ClassContainerName = itemView.findViewById(R.id.ClassContainerName);
            dayShower = itemView.findViewById(R.id.dayShower);
            dayTitle = itemView.findViewById(R.id.dayTitle);
            classBlock = itemView.findViewById(R.id.classBlock);
        }
    }
}
