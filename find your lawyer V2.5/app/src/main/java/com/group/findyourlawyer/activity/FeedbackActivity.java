package com.group.findyourlawyer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.group.findyourlawyer.PostAdapter;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.adapters.FeedbackAdepter;
import com.group.findyourlawyer.models.FeedbackModel;
import com.group.findyourlawyer.models.Post;

public class FeedbackActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    private FeedbackAdepter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        recyclerView = findViewById(R.id.recycler_view1);
        floatingActionButton = findViewById(R.id.floatingActionButton2);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedbackActivity.this , GiveFeedbackActivity.class));
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<FeedbackModel> options =
                new FirebaseRecyclerOptions.Builder<FeedbackModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Feedback"), FeedbackModel.class)
                        .build();

        adapter = new FeedbackAdepter(options);
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