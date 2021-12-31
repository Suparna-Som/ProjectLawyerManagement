package com.group.findyourlawyer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group.findyourlawyer.R;

import java.util.HashMap;

public class LawyerOtherActivity extends AppCompatActivity {
    HashMap<String,Object> map = new HashMap<>();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Lawyer").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_other);
           EditText service,courts,about,edu,exp;
         Button button;
        service = findViewById(R.id.service1);
        courts = findViewById(R.id.courts1);
        about = findViewById(R.id.about1);
        edu = findViewById(R.id.edu1);
        exp = findViewById(R.id.exp1);
        button = findViewById(R.id.update);

    String a=service.getText().toString();
        map.put("service",a);
        map.put("courts",courts.getText().toString());
        map.put("about",about.getText().toString());
        map.put("edu",edu.getText().toString());
        map.put("exp",exp.getText().toString());






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.updateChildren(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("Complete", "onComplete: ");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("Failed", "onFailure: " + e.toString());
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("success", "onSuccess: ");
                        Toast.makeText(getBaseContext(), "Submitted", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });






    }
}