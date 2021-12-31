package com.group.findyourlawyer.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.activity.ConsultActivity;
import com.group.findyourlawyer.models.Model;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;
import static com.group.findyourlawyer.activity.Login.mSpinner;


public class FragmentConsultation extends Fragment {


    Button button3,button1,button2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    private FirebaseAuth mAuth;
    String call;
    public FragmentConsultation() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_consultation, container, false);
        button3 = view.findViewById(R.id.meetingButton);
        button1 = view.findViewById(R.id.buttonMassage);
        button2 = view.findViewById(R.id.callButton);
        mAuth = FirebaseAuth.getInstance();
        final ConsultActivity consultActivity = (ConsultActivity) getActivity();
        final String userTypekey= consultActivity.sh.getString("userType","");

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO add custon dailog for all booking details
                        root.child("Lawyer").child(consultActivity.key).child("Appointment").push().setValue(mAuth.getCurrentUser().getUid());
                        root.child(userTypekey).child(mAuth.getCurrentUser().getUid()).child("Appointment").push().setValue(consultActivity.key);
                        Toast.makeText(consultActivity, userTypekey, Toast.LENGTH_SHORT).show();
                }
            });

        root.child("Lawyer").child(consultActivity.key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               call = Objects.requireNonNull(snapshot.child("phoneNo").getValue()).toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        String uri = "tel:" + call;
        final Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));


            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + call));
//                    getActivity().startActivity(intent);
                }
            });
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });



        return  view;
    }

}