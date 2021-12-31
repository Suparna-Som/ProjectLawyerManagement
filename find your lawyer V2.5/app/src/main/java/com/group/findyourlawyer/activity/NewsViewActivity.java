package com.group.findyourlawyer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.group.findyourlawyer.R;

public class NewsViewActivity extends AppCompatActivity {
    private TextView title,dis,time;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);


        title = findViewById(R.id.tv1);
        dis = findViewById(R.id.tv2);
        time = findViewById(R.id.tv3);
        imageView = findViewById(R.id.iv1);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));;
        dis.setText(intent.getStringExtra("dis"));
        time.setText(intent.getStringExtra("time"));
        String a=intent.getStringExtra("image");
        Glide.with(this)
                .asBitmap()
                .load(a)
                .into(imageView);



    }
}