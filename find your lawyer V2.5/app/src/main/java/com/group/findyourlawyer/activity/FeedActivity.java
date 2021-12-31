package com.group.findyourlawyer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.adapters.FeedbackAdepter;
import com.group.findyourlawyer.adapters.NewsAdepter;
import com.group.findyourlawyer.models.FeedbackModel;
import com.group.findyourlawyer.models.NewsModel;

public class FeedActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private NewsAdepter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        recyclerView = findViewById(R.id.recycler_view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<NewsModel> options =
                new FirebaseRecyclerOptions.Builder<NewsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("News"), NewsModel.class)
                        .build();
        adapter = new NewsAdepter(options,this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {

        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter.stopListening();
    }
}