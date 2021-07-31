package com.betterschool.co.deputyNote.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.deputyNote.model.deputyNote;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterDeputyNote extends RecyclerView.Adapter<CustomAdapterDeputyNote.viewHolderDeputyNote> {
    List<deputyNote> list;
    Context context;

    public CustomAdapterDeputyNote(List<deputyNote> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolderDeputyNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderDeputyNote(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deputy_note,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolderDeputyNote holder, int position) {
        deputyNote item = list.get(position);
        holder.title.setText("عنوان : " + item.getTypeId().getTitle());
        holder.createDate.setText(item.getCreateDate());
        holder.description.setText("توضیحات : " + item.getDescription());
        holder.indexCounter.setText(position + 1 + "");
        holder.score.setText("کسر نمره : " + item.getTypeId().getScore());
        holder.name.setText("نام : " + item.getStudentId().getName() + " " + item.getStudentId().getLastName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolderDeputyNote extends RecyclerView.ViewHolder {
        TextView indexCounter,name,createDate,score,title,description;
        public viewHolderDeputyNote(@NonNull View itemView) {
            super(itemView);
            indexCounter = itemView.findViewById(R.id.indexCounter);
            name = itemView.findViewById(R.id.name);
            createDate = itemView.findViewById(R.id.createDate);
            score = itemView.findViewById(R.id.score);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }
}
