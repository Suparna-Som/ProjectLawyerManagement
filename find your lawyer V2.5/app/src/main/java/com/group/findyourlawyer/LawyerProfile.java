package com.group.findyourlawyer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.group.findyourlawyer.adapters.myadapter;
import com.group.findyourlawyer.models.Model;
import com.squareup.picasso.Picasso;

public class LawyerProfile extends AppCompatActivity {
    RecyclerView recview;
    myadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_profile);

        recview=(RecyclerView)findViewById(R.id.recview);
        final SwipeRefreshLayout refreshLawyer = (SwipeRefreshLayout) findViewById(R.id.refreshLawyer);

        refreshLawyer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLawyer.setRefreshing(false);
                    }
                }, 3000);

            }
        });


        recview.setLayoutManager(new LinearLayoutManager(this));

        if(new InternetDialog(this).getInternetStatus()){ }

        final FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Lawyer"), Model.class)
                        .build();

        adapter=new myadapter(options,this);
        recview.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
