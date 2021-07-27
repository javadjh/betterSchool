package com.betterschool.co.homePage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.classContainer.ClassContainerActivity;
import com.betterschool.co.classPackage.TeachersClassActivity;
import com.betterschool.co.homePage.model.modelHomePage;
import com.betterschool.co.semesters.SemestersActivity;
import com.betterschool.co.students.StudentsActivity;
import com.betterschool.co.teachers.TeachersActivity;

import java.util.List;

public class CustomAdapterHomePage extends RecyclerView.Adapter<CustomAdapterHomePage.viewHodlerHomePage> {
    List<modelHomePage> listHomePage;
    Context context;

    public CustomAdapterHomePage(List<modelHomePage> listHomePage, Context context) {
        this.listHomePage = listHomePage;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHodlerHomePage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHodlerHomePage(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_option,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterHomePage.viewHodlerHomePage holder, int position) {
        modelHomePage itemHomePage = listHomePage.get(position);
        holder.title.setText(itemHomePage.getTitle());
        holder.description.setText(itemHomePage.getDescription());
        holder.cardContainer.setBackgroundResource(itemHomePage.getColor());
        holder.cardIcon.setImageResource(itemHomePage.getIcon());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (itemHomePage.getUniqueId()){
                    case "student":
                        intent = new Intent(CustomAdapterHomePage.this.context, StudentsActivity.class);
                        context.startActivity(intent);
                        break;
                    case "teacher":
                        intent = new Intent(CustomAdapterHomePage.this.context, TeachersActivity.class);
                        context.startActivity(intent);
                        break;
                    case "classContainer":
                        intent = new Intent(CustomAdapterHomePage.this.context, ClassContainerActivity.class);
                        context.startActivity(intent);
                        break;
                    case "semester":
                        intent = new Intent(CustomAdapterHomePage.this.context, SemestersActivity.class);
                        context.startActivity(intent);
                        break;
                        //teachers----------------------------------------------------------------------------------------
                    case "classManager":
                        intent = new Intent(CustomAdapterHomePage.this.context, TeachersClassActivity.class);
                        context.startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHomePage.size();
    }

    public static class viewHodlerHomePage extends RecyclerView.ViewHolder {
        ImageView cardIcon;
        LinearLayout cardContainer;
        TextView description,title;
        CardView card;
        public viewHodlerHomePage(@NonNull View itemView) {
            super(itemView);
            cardIcon = itemView.findViewById(R.id.cardIcon);
            description = itemView.findViewById(R.id.description);
            title = itemView.findViewById(R.id.title);
            cardContainer = itemView.findViewById(R.id.cardContainer);
            card = itemView.findViewById(R.id.card);
        }
    }
}
