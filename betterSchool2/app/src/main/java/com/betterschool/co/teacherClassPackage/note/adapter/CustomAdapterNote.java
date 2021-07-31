package com.betterschool.co.teacherClassPackage.note.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.teacherClassPackage.note.model.notes;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterNote extends RecyclerView.Adapter<CustomAdapterNote.viewHolderNote> {
    List<notes> list;

    public CustomAdapterNote(List<notes> list) {
        this.list = list;
    }

    @NotNull
    @Override
    public viewHolderNote onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderNote(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderNote holder, int position) {
        notes item = list.get(position);
        holder.title.setText("عنوان : " + item.getTitle());
        holder.createDate.setText(item.getCreateDate());
        holder.description.setText("توضیحات : " + item.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderNote extends RecyclerView.ViewHolder {
        TextView title,description,createDate;
        public viewHolderNote(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            createDate = itemView.findViewById(R.id.createDate);
        }
    }
}
