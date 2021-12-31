package com.group.findyourlawyer.fragments;


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
import com.google.firebase.database.FirebaseDatabase;
import com.group.findyourlawyer.InternetDialog;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.adapters.myadapter;
import com.group.findyourlawyer.models.Model;

public class FragmentAdvocates extends Fragment {

    RecyclerView recview;
    myadapter adapter;
    public FragmentAdvocates() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_advocates, container, false);

        recview=(RecyclerView)view.findViewById(R.id.recview);

        recview.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(new InternetDialog(getActivity()).getInternetStatus()){ }

        final FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Lawyer"), Model.class)
                        .build();

        adapter=new myadapter(options,getActivity());
        recview.setAdapter(adapter);

        return view;
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
