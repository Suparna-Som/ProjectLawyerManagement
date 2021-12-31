package com.group.findyourlawyer.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group.findyourlawyer.R;
import com.group.findyourlawyer.activity.ConsultActivity;


public class FragmentProfile extends Fragment {
    private TextView s1,v1,a1,e1,ex1,d1,g1,l1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ConsultActivity consultActivity = new ConsultActivity();
//        s1 =view.findViewById(R.id.s1);
//        v1 =view.findViewById(R.id.v1);
//        a1 =view.findViewById(R.id.a1);
//        e1 =view.findViewById(R.id.e1);
//        ex1 =view.findViewById(R.id.ex1);
//        d1 =view.findViewById(R.id.d1);
//        g1 =view.findViewById(R.id.g1);
//        l1 =view.findViewById(R.id.l1);
//
//        s1.setText(consultActivity.service1);
//        v1.setText(consultActivity.courts1);
//        a1.setText(consultActivity.about1);
//        e1.setText(consultActivity.education1);
//        ex1.setText(consultActivity.experience1);
//        d1.setText(consultActivity.dob1);
//        g1.setText(consultActivity.gender1);
//        l1.setText(consultActivity.law);





        return view;
    }
}