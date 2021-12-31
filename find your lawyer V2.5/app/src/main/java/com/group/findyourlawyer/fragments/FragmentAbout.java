package com.group.findyourlawyer.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group.findyourlawyer.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class FragmentAbout extends Fragment {



    public FragmentAbout() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_about, container, false);
        Element versionElement = new Element();
        versionElement.setTitle("Version 6.2");
        String dis = "It is an android application for lawyers and clients to search for each other digitally." +
                " This system offers feature to book appointment with lawyer of your city to deal with your legalproblems.";

        View aboutPage = new AboutPage(getActivity())
                .isRTL(false)
                .enableDarkMode(false)
                .setImage(R.drawable.dummy_image)
                .setDescription(dis)
                .addItem(versionElement)
                .addGroup("Connect with us")
                .addEmail("170860116004@laxmi.edu.in")
                .addWebsite("")
                .addFacebook("")
                .addTwitter("")
                .addYoutube("")
                .addPlayStore("")
                .addGitHub("")
                .addInstagram("")
                .create();




        return aboutPage;
    }
}