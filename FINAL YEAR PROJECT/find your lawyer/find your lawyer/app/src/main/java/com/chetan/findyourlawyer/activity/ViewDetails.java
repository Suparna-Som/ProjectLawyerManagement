package com.chetan.findyourlawyer.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.chetan.findyourlawyer.R;
import com.chetan.findyourlawyer.central.C;
import com.chetan.findyourlawyer.central.StoreOnLocalStorage;
import com.chetan.findyourlawyer.models.Model;
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

public class ViewDetails extends AppCompatActivity {
    FirebaseFirestore fStore;
    TextView name, gender, email, address, dob, phone;
    ProgressBar progressBar;
    ImageView img;
    String reference;
    private static final String TAG = "ViewDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        fStore = FirebaseFirestore.getInstance();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean("AccountTypeFindYourLawyer", false)) {
            reference = "Client";
        } else {
            reference = "Lawyer";
        }
        initViews();
    }

    private void initViews() {
        name = findViewById(R.id.layout_userdetails_name);
        address = findViewById(R.id.layout_userdetails_address);
        dob = findViewById(R.id.layout_userdetails_dob);
        email = findViewById(R.id.layout_userdetails_email);
        phone = findViewById(R.id.layout_userdetails_phone);
        gender = findViewById(R.id.layout_userdetails_gender);
        progressBar = findViewById(R.id.ViewDetailsprogress_circular);
        progressBar.setVisibility(View.VISIBLE);
        img = (ImageView) findViewById(R.id.activity_profilepic);
        retrieve();
    }

    public void retrieve() {
        Log.d(TAG, "retrieve: ");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(reference).child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "onDataChange: ");
                name.setText(Objects.requireNonNull(snapshot.child(C.name).getValue()).toString());
                gender.setText(Objects.requireNonNull(snapshot.child(C.gender).getValue()).toString());
                email.setText(Objects.requireNonNull(snapshot.child(C.email).getValue()).toString());
                address.setText(Objects.requireNonNull(snapshot.child(C.address).getValue()).toString());
                phone.setText(Objects.requireNonNull(snapshot.child(C.phone).getValue()).toString());
                dob.setText(snapshot.child("dob").getValue().toString());
                Model model = new Model();
                model.setName(name.getText().toString());
                model.setAddress(address.getText().toString());
                model.setDob(dob.getText().toString());
                model.setGender(gender.getText().toString());
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