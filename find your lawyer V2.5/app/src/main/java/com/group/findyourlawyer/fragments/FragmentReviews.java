package com.group.findyourlawyer.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.activity.ConsultActivity;
import com.group.findyourlawyer.activity.LawyerReviewsActivity;
import com.group.findyourlawyer.adapters.FeedbackAdepter;
import com.group.findyourlawyer.models.FeedbackModel;


public class FragmentReviews extends Fragment {
    private RecyclerView recyclerView;
    private FeedbackAdepter adapter;
    private Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        button = view.findViewById(R.id.reviews);
        recyclerView = view.findViewById(R.id.recycler_view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ConsultActivity consultActivity = (ConsultActivity) getActivity();
        FirebaseRecyclerOptions<FeedbackModel> options =
                new FirebaseRecyclerOptions.Builder<FeedbackModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Lawyer").child(consultActivity.key).child("Reviews"), FeedbackModel.class)
                        .build();

        adapter = new FeedbackAdepter(options);
        recyclerView.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LawyerReviewsActivity.class);
                intent.putExtra("key",consultActivity.key);
                getActivity().startActivity(intent);
            }
        });
        return  view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}