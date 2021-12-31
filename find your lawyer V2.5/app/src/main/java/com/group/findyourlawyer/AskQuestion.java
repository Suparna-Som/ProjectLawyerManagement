package com.group.findyourlawyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class AskQuestion extends AppCompatActivity{
    private EditText title,question;
    private TextView text;
    private Button save;
    private DatabaseReference Post;
    private DatabaseReference Post1;
    private FirebaseAuth mAuth;
    final HashMap<String,Object> map = new HashMap<>();
    private String item=null;
    public String author="";
    public SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        init();

        if(new InternetDialog(this).getInternetStatus()){

        }

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        sh= getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String a = sh.getString("userType","");
        Post1.child(a).child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                author=snapshot.child("name").getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        final ArrayList<String> array = new ArrayList<>();
        array.add(0,"Select Area of law");
        //array.add("Select Area of law");
        array.add("Bankruptcy law");
        array.add("Business law");
        array.add("Civil rights law");
        array.add("Criminal law");
        array.add("Environmental law");
        array.add("Family law");
        array.add("Health law");
        array.add("Immigration law");
        array.add("Intellectual property law");
        array.add("Employment law");
        array.add("Personal injury law");
        array.add("Real estate law");
        array.add("Tax law");
        ArrayAdapter<String> arrAdepter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,array);
        spinner.setAdapter(arrAdepter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Select Area of law")){
                    item=null;
                }else {
                    item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(),"Selected: " +item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    private void save() {
        if (title.getText().toString().isEmpty()) {
            title.setError("Enter question title");
            return;
        }
        if(question.getText().toString().isEmpty()){
            question.setError("Enter the question");
            return;
        }

        if(item == null){
            Toast.makeText(this, "Select Law Area", Toast.LENGTH_SHORT).show();
        }
        else
        {
            map.put("title",title.getText().toString());
            map.put("time",java.text.DateFormat.getDateTimeInstance().format(new Date()));
            map.put("user_id",mAuth.getCurrentUser().getUid());
            map.put("question",question.getText().toString());
            map.put("areaOfLaw","In "+item);
            map.put("postId","a");
//            map.put("description","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's " +
//                    "standard dummy text ever since the 1500s." +a
//                    "lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's " +
//                    "lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.");
            map.put("author",author);
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
                            Log.i("Failed", "onFailure: "+e.toString());
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.i("success", "onSuccess: ");
                    Toast.makeText(getBaseContext(), "Question Submitted" , Toast.LENGTH_LONG ).show();
                }
            });
            finish();
        }


    }
    private void init() {
        title = findViewById(R.id.title);
        save = findViewById(R.id.save);
        question = findViewById(R.id.que);
        text = findViewById(R.id.text);
        mAuth = FirebaseAuth.getInstance();
        Post1 =FirebaseDatabase.getInstance().getReference();
        Post = FirebaseDatabase.getInstance().getReference().child("Post");
    }

    /*
    private void readRealTime() {
        Post.child("-Ln9F3Km8HlwqS0oaD9Q")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String post = "Question : "+dataSnapshot.child("title").getValue(String.class);
                       // String time =""+dataSnapshot.child("time").getValue(String.class);

                        //text.setText(time);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void readOneTime() {
        Post.child("-Ln9F3Km8HlwqS0oaD9Q")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String post = "Title : "+dataSnapshot.child("title").getValue(String.class)+"\n"
                                +"Description : "+dataSnapshot.child("description").getValue(String.class)+"\n"
                                +"Author : "+dataSnapshot.child("author").getValue(String.class);

                        text.setText(post);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
*/



}