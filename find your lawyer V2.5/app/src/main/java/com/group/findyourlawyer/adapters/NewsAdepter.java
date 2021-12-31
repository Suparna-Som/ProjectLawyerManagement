package com.group.findyourlawyer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.activity.NewsViewActivity;
import com.group.findyourlawyer.models.NewsModel;

public class NewsAdepter extends FirebaseRecyclerAdapter<NewsModel,NewsAdepter.NewsViewHolder> {

   Context context;

    public NewsAdepter(@NonNull FirebaseRecyclerOptions<NewsModel> options, Context context) {
        super(options);
        this.context = context;
    }



    @Override
    protected void onBindViewHolder(@NonNull NewsViewHolder holder, int position, @NonNull final NewsModel model) {
       holder.newsTitle.setText(model.getTitle());
       holder.newsDis.setText(model.getDescription());
       holder.newsTime.setText(model.getTime());


        holder.news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsViewActivity.class);
                intent.putExtra("title",model.getTitle());
                intent.putExtra("dis",model.getDescription());
                intent.putExtra("time",model.getTime());
                intent.putExtra("image",model.getImage());
                context.startActivity(intent);
            }
        });

        Glide.with(context)
                .asBitmap()
                .load(model.getImage())
                .into(holder.newsImage);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_newsfeed, parent, false);
        return new NewsViewHolder(view);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        ImageView newsImage;
        TextView newsTitle,newsDis,newsTime;
        RelativeLayout news;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsDis= itemView.findViewById(R.id.newsDis);
            newsTitle= itemView.findViewById(R.id.newsTitle);
            newsTime= itemView.findViewById(R.id.newsTime);
            newsImage= itemView.findViewById(R.id.newsImage);
            news = itemView.findViewById(R.id.news);
        }
    }
}
