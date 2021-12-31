package com.group.findyourlawyer.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.group.findyourlawyer.MainActivity;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.activity.FeedActivity;
import com.group.findyourlawyer.activity.FeedbackActivity;
import com.group.findyourlawyer.activity.Incomplete;
import com.group.findyourlawyer.activity.ViewDetails;
import com.group.findyourlawyer.adapters.InfiniteAdepter;
import com.rd.PageIndicatorView;


import java.util.ArrayList;
import java.util.Date;

public class FragmentHome extends Fragment {
    public LoopingViewPager viewPager;
    public InfiniteAdepter adapter;
    Button prfile,news,fav,feedback;
    TextView homedate;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        prfile = view.findViewById(R.id.Button_Profile);
        news = view.findViewById(R.id.Button_Feed);
        fav = view.findViewById(R.id.Button_Like);
        feedback= view.findViewById(R.id.Button_Feedback);
        homedate=view.findViewById(R.id.homedate);

        homedate.setText(java.text.DateFormat.getDateTimeInstance().format(new Date()));
        prfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ViewDetails.class));
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Incomplete.class));
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FeedActivity.class));
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
            }
        });

        adapter = new InfiniteAdepter(getActivity(), createDummyItems(), true);
        viewPager.setAdapter(adapter);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        viewPager.resumeAutoScroll();
    }

    @Override
    public void onPause() {
        viewPager.pauseAutoScroll();
        super.onPause();
    }

    private ArrayList<Integer> createDummyItems () {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        items.add(1, 2);
        items.add(2, 3);
        items.add(3, 4);
        items.add(4, 5);
        items.add(5, 6);
        return items;
    }


}