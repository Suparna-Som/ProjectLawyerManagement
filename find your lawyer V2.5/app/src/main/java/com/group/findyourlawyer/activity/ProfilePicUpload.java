package com.group.findyourlawyer.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.findyourlawyer.InternetDialog;
import com.group.findyourlawyer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.group.findyourlawyer.central.C;
import com.group.findyourlawyer.central.StoreOnLocalStorage;
import com.group.findyourlawyer.models.Model;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static com.group.findyourlawyer.central.C.model;

public class ProfilePicUpload extends AppCompatActivity {
    TextView name, address, dob, email, phone, gender;
    ImageView imageView;
    ProgressBar progressBar;
    String reference;
    ImageView img;
    private static final String TAG = "ViewDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic_upload);

        if(new InternetDialog(this).getInternetStatus()){

        }

        imageView = findViewById(R.id.activity_profilepic);
        name = findViewById(R.id.layout_userdetails_name);
        address = findViewById(R.id.layout_userdetails_address);
        dob = findViewById(R.id.layout_userdetails_dob);
        email = findViewById(R.id.layout_userdetails_email);
        phone = findViewById(R.id.layout_userdetails_phone);
        gender = findViewById(R.id.layout_userdetails_gender);
        progressBar = findViewById(R.id.profilepicuploadprogressbar);
//        if(progressBar!=null)
        progressBar.setVisibility(View.INVISIBLE);

    }




    @Override
    protected void onStart() {
        super.onStart();
        if(model!=null) {
            name.setText(model.getName());
            address.setText(model.getAddress());
            dob.setText(model.getDob());
            email.setText(model.getEmail());
            phone.setText(model.getPhoneNo());
            gender.setText(model.getGender());
        }
    }

    public void uploadPick(View view) {
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(openGalleryIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                //profileImage.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
                progressBar.setVisibility(View.VISIBLE);

            }
        }
    }

    private void uploadImageToFirebase(final Uri imageUri) {
        // uplaod image to firebase storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        FirebaseAuth fauth = FirebaseAuth.getInstance();
        final StorageReference fileRef = storageReference.child("users/" + fauth.getCurrentUser().getUid() + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Picasso.get().load(uri).into((ImageView) findViewById(R.id.activity_profilepic));
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProfilePicUpload.this);
                        sharedPreferences.edit().putString("chetanimageuri", imageUri.toString()).apply();
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(ProfilePicUpload.this, "wait a moment \n we are Setting up, \nYour Profile", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login(View view) {
        startActivity(new Intent(this, Login.class));
    }
}