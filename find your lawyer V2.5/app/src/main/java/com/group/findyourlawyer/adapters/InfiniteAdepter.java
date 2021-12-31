package com.group.findyourlawyer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.group.findyourlawyer.R;

import java.util.ArrayList;

public class InfiniteAdepter extends LoopingPagerAdapter<Integer> {

    public InfiniteAdepter(Context context, ArrayList<Integer> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected View getItemView(View convertView, int listPosition, ViewPager container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pager, null);
            container.addView(convertView);
        }
        convertView.findViewById(R.id.image).setBackgroundColor(context.getResources().getColor(getBackgroundColor(listPosition)));

        return convertView;
    }

    private int getBackgroundColor (int number) {
        switch (number) {
            case 0:
                return android.R.color.holo_red_light;
            case 1:
                return android.R.color.holo_orange_light;
            case 2:
                return android.R.color.holo_green_light;
            case 3:
                return android.R.color.holo_blue_light;
            case 4:
                return android.R.color.holo_purple;
            case 5:
                return android.R.color.black;
            default:
                return android.R.color.black;
        }
    }
}