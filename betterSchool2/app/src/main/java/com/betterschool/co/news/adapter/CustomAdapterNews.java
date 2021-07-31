package com.betterschool.co.news.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.news.model.news;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomAdapterNews extends RecyclerView.Adapter<CustomAdapterNews.viewHolderNews> {
    List<news> list ;

    public CustomAdapterNews(List<news> list) {
        this.list = list;
    }

    @NotNull
    @Override
    public viewHolderNews onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderNews(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolderNews holder, int position) {
        news item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.createDate.setText(item.getCreateDate());
        holder.description.setText(item.getDescription());
        Picasso.get().load("http://192.168.1.33:1000/" + item.getImage()).into(holder.imageNews);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderNews extends RecyclerView.ViewHolder {
        ImageView imageNews;
        TextView title,createDate,description;

        public viewHolderNews(@NonNull @NotNull View itemView) {
            super(itemView);
            imageNews = itemView.findViewById(R.id.imageNews);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            createDate = itemView.findViewById(R.id.createDate);
        }
    }
}
