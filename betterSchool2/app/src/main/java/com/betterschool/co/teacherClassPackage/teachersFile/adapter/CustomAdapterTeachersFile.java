package com.betterschool.co.teacherClassPackage.teachersFile.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.teacherClassPackage.teachersFile.model.teachersFile;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterTeachersFile extends RecyclerView.Adapter<CustomAdapterTeachersFile.viewHolderTeachersFile> {
    List<teachersFile> list;
    Context context;

    public CustomAdapterTeachersFile(List<teachersFile> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NotNull
    @Override
    public viewHolderTeachersFile onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderTeachersFile(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher_file,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderTeachersFile holder, int position) {
        teachersFile item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.createDate.setText(item.getCreateDate());
        holder.fileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://192.168.1.33:1000/" + item.getFile());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderTeachersFile extends RecyclerView.ViewHolder {
        TextView title,createDate;
        CardView fileCard;
        public viewHolderTeachersFile(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            createDate = itemView.findViewById(R.id.createDate);
            fileCard = itemView.findViewById(R.id.fileCard);
        }
    }
}
