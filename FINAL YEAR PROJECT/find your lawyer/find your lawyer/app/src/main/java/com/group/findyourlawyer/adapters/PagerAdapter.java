package com.group.findyourlawyer.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.group.findyourlawyer.fragments.FragmentAdvocates;
import com.group.findyourlawyer.fragments.FragmentFeeds;
import com.group.findyourlawyer.fragments.FragmentQuestions;


public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs){
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentQuestions();
            case 1:
                return new FragmentAdvocates();
            case 2:
                return new FragmentFeeds();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
