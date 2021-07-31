package com.betterschool.co.deputyNote.insertDeputyNote.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.deputyNote.insertDeputyNote.model.violations;
import com.betterschool.co.utilityClass.payloadId;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterViolations extends RecyclerView.Adapter<CustomAdapterViolations.viewHolderViolation> {
    List<violations> list;
    payloadViolation payloadViolation;

    public CustomAdapterViolations(List<violations> list, com.betterschool.co.deputyNote.insertDeputyNote.adapter.payloadViolation payloadViolation) {
        this.list = list;
        this.payloadViolation = payloadViolation;
    }

    @NotNull
    @Override
    public viewHolderViolation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderViolation(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_students,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolderViolation holder, int position) {
        violations item = list.get(position);
        holder.fullName.setText(item.getTitle() + " ( " + item.getScore() + " ) ");
        holder.fullName.setOnClickListener(v -> payloadViolation.onViolation(item));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderViolation extends RecyclerView.ViewHolder {
        TextView fullName;
        public viewHolderViolation(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
        }
    }
}
