package com.group.findyourlawyer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.adapters.PagerAdapter;
import com.group.findyourlawyer.central.C;
import com.group.findyourlawyer.models.Model;

import java.util.ArrayList;

public class ConsultActivity extends AppCompatActivity {

    private TabLayout tab;
    private TabItem tabItem1,tabItem2,tabItem3;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private ImageView backImage;
    private TextView name;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference root = database.getReference().child("Lawyer");
    public String key, gender1, email1, address1, dob1, law,service1,courts1,about1,education1,experience1;
    public SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        backImage = findViewById(R.id.backImage);
        tab = findViewById(R.id.tab);
        tabItem1 = findViewById(R.id.tabItem1);
        tabItem2 = findViewById(R.id.tabItem2);
        tabItem3 = findViewById(R.id.tabItem3);
        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tab.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        name = findViewById(R.id.name);
        Intent intent = getIntent();
        key= intent.getStringExtra("key");
        sh= getSharedPreferences("MySharedPref", MODE_PRIVATE);

        root.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child("name").getValue().toString());
//                gender1 = snapshot.child("gender").getValue().toString();
//                service1 = snapshot.child("service").getValue().toString();
//                courts1 = snapshot.child("courts").getValue().toString();
//                about1 = snapshot.child("about").getValue().toString();
//                education1 = snapshot.child("edu").getValue().toString();
//                experience1 = snapshot.child("exp").getValue().toString();
//                address1 = snapshot.child("address").getValue().toString();
//                dob1 = snapshot.child("dob").getValue().toString();
//                law= snapshot.child("lawPractices").getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 ){
                    pagerAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));


        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}