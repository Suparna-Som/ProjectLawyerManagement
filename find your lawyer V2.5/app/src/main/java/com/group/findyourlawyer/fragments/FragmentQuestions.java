package com.group.findyourlawyer.fragments;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group.findyourlawyer.AskQuestion;
import com.group.findyourlawyer.InternetDialog;
import com.group.findyourlawyer.PostAdapter;
import com.group.findyourlawyer.PostListActivity;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.models.Post;

import java.util.HashMap;

public class FragmentQuestions extends Fragment {
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    HashMap<String,Object> map = new HashMap<>();
    private DatabaseReference Post;
    public FragmentQuestions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);

        recyclerView = view.findViewById(R.id.recycler);

        Post = FirebaseDatabase.getInstance().getReference().child("Post");

        if(new InternetDialog(getActivity()).getInternetStatus()){
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        FirebaseRecyclerOptions<com.group.findyourlawyer.models.Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Post"), Post.class)
                        .build();

        adapter = new PostAdapter(options,getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {

        super.onStart();
        adapter.startListening();
    }

    @Override
    public  void onStop() {
        super.onStop();

        adapter.stopListening();
    }

}
