package com.group.findyourlawyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.findyourlawyer.models.Post;


import java.util.Date;
import java.util.HashMap;

import static com.group.findyourlawyer.PostAdapter.user_id;

public class PostListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    HashMap<String,Object> map = new HashMap<>();
    FloatingActionButton Button_Floating;

    private DatabaseReference Post;
    private DatabaseReference root;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        Button_Floating = findViewById(R.id.Button_Floating);
        recyclerView = findViewById(R.id.recycler);

        Post = FirebaseDatabase.getInstance().getReference().child("Post");


        final SwipeRefreshLayout refreshQA = (SwipeRefreshLayout) findViewById(R.id.refreshQA);

        refreshQA.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshQA.setRefreshing(false);
                    }
                }, 3000);

            }
        });
        if(new InternetDialog(this).getInternetStatus()){
        }
        Button_Floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostListActivity.this, AskQuestion.class));
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Post"), Post.class)
                        .build();

        adapter = new PostAdapter(options,this);
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