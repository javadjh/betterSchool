package com.betterschool.co.otherClass.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.otherClass.model.otherClasses;
import com.betterschool.co.otherClass.singleOtherClass.SingleOtherClassActivity;
import com.betterschool.co.teacherClassPackage.otherClass.TeachersOtherClassSingle;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterOtherClass extends RecyclerView.Adapter<CustomAdapterOtherClass.viewHolderOtherClass> {
    List<otherClasses> list;
    Context context;
    String type ;
    Boolean isJoined = false;

    public CustomAdapterOtherClass(List<otherClasses> list, Context context, String type, Boolean isJoined) {
        this.list = list;
        this.context = context;
        this.type = type;
        this.isJoined = isJoined;
    }

    public CustomAdapterOtherClass(List<otherClasses> list, Context context, String type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }

    @NotNull
    @Override
    public viewHolderOtherClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderOtherClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_other_class,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolderOtherClass holder, int position) {
        otherClasses item = list.get(position);
        switch(item.getGrade()){
            case 1:
                holder.grade.setText("پایه : اول");
                break;
            case 2:
                holder.grade.setText("پایه : دوم");
                break;
            case 3:
                holder.grade.setText("پایه : سوم");
                break;
        }
        holder.title.setText(item.getTitle());
        holder.minMax.setText("حداقل "+ item.getMinParticipant() + " و حداکثر : " + item.getMaxParticipant());
        holder.defMinMax.setText(" ("+item.getStudentRegistered()+ ") ");
        holder.startDate.setText("شروع کلاس : " + item.getStartDate());
        holder.teacherName.setText("معلم : " + item.getTeacher().getName() + " " + item.getTeacher().getLastName());
        holder.price.setText("هزینه : " + item.getPrice());
        holder.defDate.setText(" (" +item.getDefDate() + ") ");
        Picasso.get().load("http://192.168.1.33:1000/" + item.getImage()).into(holder.image);
        holder.cardOtherClass.setOnClickListener(v -> {
            Intent intent = new Intent(CustomAdapterOtherClass.this.context, SingleOtherClassActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("id", item.get_id());
            intent.putExtra("joined", isJoined);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderOtherClass extends RecyclerView.ViewHolder {
        TextView grade,title,minMax,defMinMax,startDate,defDate,teacherName,price;
        CardView cardOtherClass;
        ImageView image;
        public viewHolderOtherClass(@NonNull View itemView) {
            super(itemView);
            grade = itemView.findViewById(R.id.grade);
            title = itemView.findViewById(R.id.title);
            minMax = itemView.findViewById(R.id.minMax);
            defMinMax = itemView.findViewById(R.id.defMinMax);
            startDate = itemView.findViewById(R.id.startDate);
            defDate = itemView.findViewById(R.id.defDate);
            teacherName = itemView.findViewById(R.id.teacherName);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);
            cardOtherClass = itemView.findViewById(R.id.cardOtherClass);
        }
    }
}
