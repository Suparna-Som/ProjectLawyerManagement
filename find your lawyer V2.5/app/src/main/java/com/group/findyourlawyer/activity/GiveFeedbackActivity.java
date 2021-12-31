package com.group.findyourlawyer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.findyourlawyer.InternetDialog;
import com.group.findyourlawyer.R;

import java.util.Date;
import java.util.HashMap;

public class GiveFeedbackActivity extends AppCompatActivity {
    private Button save,close;
    private EditText editText;
    private RatingBar ratingBar;
    private DatabaseReference Post;
    private FirebaseAuth mAuth;
    private double rat;
    final HashMap<String, Object> map = new HashMap<>();
    private String  authorName1="user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_feedback);
        editText = findViewById(R.id.editText1);
        Post = FirebaseDatabase.getInstance().getReference().child("Feedback");
        mAuth = FirebaseAuth.getInstance();
        ratingBar = findViewById(R.id.ratingbar);

        if (new InternetDialog(this).getInternetStatus()) {
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rat = rating;
            }
        });




        save = findViewById(R.id.saveButton);
        close=findViewById(R.id.closeButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().isEmpty()){
                    editText.setError("Enter feedback");
                }
                else {
                    save();
                }

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void save() {
        map.put("rating",rat);
        map.put("feedback",editText.getText().toString());
        map.put("time", java.text.DateFormat.getDateTimeInstance().format(new Date()));
        map.put("reply","");
        map.put("user_id",mAuth.getCurrentUser().getUid());
        map.put("username",authorName1);
        Post.push()
                .setValue(map)
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
        finish();

    }
}