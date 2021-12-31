package com.group.findyourlawyer.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.findyourlawyer.PostAdapter;
import com.group.findyourlawyer.PostListActivity;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.activity.Registration;

import java.util.HashMap;

public class FragmentFeeds extends Fragment {
/*
    private EditText title,description,author;
    private TextView text;
    private Button save,read;
    private DatabaseReference Post;
*/

    public FragmentFeeds() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_feeds, container, false);

        Intent i = new Intent(getActivity(), PostListActivity.class);
        startActivity(i);

       /* title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        author = view.findViewById(R.id.author);
        save = view.findViewById(R.id.save);
        read = view.findViewById(R.id.read);
        text = view.findViewById(R.id.text);

        Post = FirebaseDatabase.getInstance().getReference().child("Post");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                Toast.makeText(getActivity(),"Data enter",Toast.LENGTH_SHORT).show();
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // readOneTime();
                readRealTime();
            }
        });*/
        return view;

    }

/*
    private void readRealTime() {
            Post.child("-Ln9F3Km8HlwqS0oaD9Q")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String post = "Title : "+dataSnapshot.child("title").getValue(String.class)+"\n"
                                    +"Description : "+dataSnapshot.child("description").getValue(String.class)+"\n"
                                    +"Author : "+dataSnapshot.child("author").getValue(String.class);

                            text.setText(post);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }
        private void readOneTime() {
            Post.child("-Ln9F3Km8HlwqS0oaD9Q")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String post = "Title : "+dataSnapshot.child("title").getValue(String.class)+"\n"
                                    +"Description : "+dataSnapshot.child("description").getValue(String.class)+"\n"
                                    +"Author : "+dataSnapshot.child("author").getValue(String.class);

                            text.setText(post);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }

        private void save() {
            HashMap<String,Object> map = new HashMap<>();
            map.put("title",title.getText().toString());
            map.put("description",description.getText().toString());
            map.put("author",author.getText().toString());
        Post.push()
                .setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("jfbvkj", "onComplete: ");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("jfbvkj", "onFailure: "+e.toString());
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i("jfbvkj", "onSuccess: ");
            }
        });


    }
*/
}