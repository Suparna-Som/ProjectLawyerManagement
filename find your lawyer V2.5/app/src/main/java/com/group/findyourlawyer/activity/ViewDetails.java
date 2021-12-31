package com.group.findyourlawyer.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group.findyourlawyer.InternetDialog;
import com.group.findyourlawyer.MainActivity;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.central.C;
import com.group.findyourlawyer.central.StoreOnLocalStorage;
import com.group.findyourlawyer.models.Model;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static com.group.findyourlawyer.activity.Login.mSpinner;

public class ViewDetails extends AppCompatActivity {
    private FirebaseFirestore fStore;
    private TextView name, gender, email, address, dob, phone,service,courts,about,education,experience;
    private Button button;
    private LinearLayout linearLayout;
    private ProgressBar progressBar;
    private ImageView img;
    String reference;
    private static final String TAG = "ViewDetails";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        if(new InternetDialog(this).getInternetStatus()){
        }
        fStore = FirebaseFirestore.getInstance();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

      /*  if (sharedPreferences.getBoolean("AccountTypeFindYourLawyer",false)){
            reference = "Client";
        } else {
            reference = "Lawyer";
        }*/
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        final String userTypekey= sh.getString("userType","");
        initViews(userTypekey);
//        final Intent intent = new Intent(this,LawyerOtherActivity.class);
//        if (userTypekey.equals("Lawyer")){
//            button.setVisibility(View.VISIBLE);
//            linearLayout .setVisibility(View.VISIBLE);
//        }
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(intent);
//            }
//        });

    }

    private void initViews(String userTypekey) {
        name = findViewById(R.id.layout_userdetails_name);
        address = findViewById(R.id.layout_userdetails_address);
        dob = findViewById(R.id.layout_userdetails_dob);
        email = findViewById(R.id.layout_userdetails_email);
        phone = findViewById(R.id.layout_userdetails_phone);
        gender = findViewById(R.id.layout_userdetails_gender);
        progressBar = findViewById(R.id.ViewDetailsprogress_circular);
        progressBar.setVisibility(View.VISIBLE);
//        service = findViewById(R.id.layout_userdetails_service);
//        courts = findViewById(R.id.layout_userdetails_courts);
//        about = findViewById(R.id.layout_userdetails_about);
//        education = findViewById( R.id.layout_userdetails_edu);
//        experience = findViewById(R.id.layout_userdetails_exp);
//        linearLayout = findViewById(R.id.LinOther);
//        button = findViewById(R.id.editDetail);

        img = (ImageView) findViewById(R.id.activity_profilepic);
        retrieve(userTypekey);
    }

    public void retrieve(String userTypekey) {
        Log.d(TAG, "retrieve: ");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(userTypekey).child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "onDataChange: ");

                Model model = new Model();
                model.setName(name.getText().toString());
                model.setAddress(address.getText().toString());
                model.setDob(dob.getText().toString());
                model.setGender(gender.getText().toString());
                try {
                    name.setText(Objects.requireNonNull(snapshot.child(C.name).getValue()).toString());
                    gender.setText(Objects.requireNonNull(snapshot.child(C.gender).getValue()).toString());
                    email.setText(Objects.requireNonNull(snapshot.child(C.email).getValue()).toString());
                    address.setText(Objects.requireNonNull(snapshot.child(C.address).getValue()).toString());
                    phone.setText(Objects.requireNonNull(snapshot.child(C.phone).getValue()).toString());
                    dob.setText(snapshot.child("dob").getValue().toString());
//                    service.setText(snapshot.child("service").getValue().toString());
//                    courts.setText(snapshot.child("courts").getValue().toString());
//                    about.setText(snapshot.child("about").getValue().toString());
//                    education.setText(snapshot.child("edu").getValue().toString());
//                    experience.setText(snapshot.child("exp").getValue().toString());

                }
                catch (Exception e){
                    Toast.makeText(ViewDetails.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }

                StoreOnLocalStorage.Store(ViewDetails.this, model);
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        final StorageReference fileRef = storageReference.child("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/profile.jpg");
        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img);
            }
        });
    }

    public void openEditActivity(View view) {
        startActivity(new Intent(this, EditProfile.class));
    }

    public void Export(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Export Details")
                .setMessage("All Your Personal and Professional Details are exported and sent to your registered email address")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}