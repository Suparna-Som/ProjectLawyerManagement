package com.group.findyourlawyer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.findyourlawyer.PostAdapter;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.adapters.AnswerAdepter;
import com.group.findyourlawyer.models.AnswerModel;
import com.group.findyourlawyer.models.Post;

import java.util.Date;
import java.util.HashMap;

public class answerActivity extends AppCompatActivity {
    String postID;
    DatabaseReference post = FirebaseDatabase.getInstance().getReference();
    private TextView author,areaOfLaw,title,question,time;
    private AnswerAdepter adapter;
    private Button sendButton;
    private EditText editText;
    private RecyclerView recyclerView;
    private String authorName;
    private String authorType;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final HashMap<String,String> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        author = findViewById(R.id.author);
        areaOfLaw = findViewById(R.id.areaOfLaw);
        title = findViewById(R.id.title);
        question = findViewById(R.id.question);
        time = findViewById(R.id.time);
        sendButton = findViewById(R.id.sendButton);
        editText = findViewById(R.id.edit_text);
        recyclerView = findViewById(R.id.recyclerViewAns);
        Intent intent = getIntent();
        postID = intent.getStringExtra("postId");

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String a = sh.getString("userType","");
        post.child(a).child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                authorName=snapshot.child("name").getValue().toString();
                authorType=snapshot.child("accountType").getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        if (postID.equals("a")){
            Toast.makeText(this, "Something went wrong!!!", Toast.LENGTH_LONG).show();
        }else {



            post.child("Post").child(postID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    author.setText(snapshot.child("author").getValue().toString());
                    areaOfLaw.setText(snapshot.child("areaOfLaw").getValue().toString());
                    title.setText(snapshot.child("title").getValue().toString());
                    question.setText(snapshot.child("question").getValue().toString());
                    time.setText(snapshot.child("time").getValue().toString());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            FirebaseRecyclerOptions<AnswerModel> options =
                    new FirebaseRecyclerOptions.Builder<AnswerModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Post").child(postID).child("Answer"), AnswerModel.class)
                            .build();

            adapter = new AnswerAdepter(options);
            recyclerView.setAdapter(adapter);
        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (postID.equals("a")){
                }else {

                    map.put("username", authorName);
                    map.put("useranswer", editText.getText().toString());
                    map.put("accountType",authorType);
                    map.put("time",java.text.DateFormat.getDateTimeInstance().format(new Date()));
                    post.child("Post").child(postID).child("Answer").push()
                            .setValue(map)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Log.i("Complete", "onComplete: ");

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.i("Failed", "onFailure: " + e.toString());
                                }
                            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.i("success", "onSuccess: ");
                            Toast.makeText(getBaseContext(), "Answer Submitted", Toast.LENGTH_LONG).show();
                        }
                    });
                    editText.setText("");
                }
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (postID.equals("a")){
        }else {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (postID.equals("a")){
        }else {
            adapter.stopListening();
        }
    }
}