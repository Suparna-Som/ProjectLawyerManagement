package com.group.findyourlawyer.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.group.findyourlawyer.R;
import com.group.findyourlawyer.central.C;
import com.group.findyourlawyer.central.StoreOnLocalStorage;
import com.group.findyourlawyer.models.Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EditProfile extends AppCompatActivity {
    TextView textView;
    TextView name, address, dob, phone;
    ProgressBar progressBar;
    Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initViews();
    }

    private void initViews() {
        address = findViewById(R.id.layout_userdetails_address);
        phone = findViewById(R.id.edit_profile_phone);
        progressBar = findViewById(R.id.editprofile_progressbar);
        setListeners();
        progressBar.setVisibility(View.INVISIBLE);
        model = StoreOnLocalStorage.getDetails(this);
        phone.setText("cbwjbvjer");
    }

    private void setListeners() {
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogue(address, "Enter Address", false);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogue(phone, "Enter New Phone Number", true);
            }
        });

    }

    void dialogue(final TextView view, String message, boolean isphone) {
        final EditText resetMail = new EditText(view.getContext());
        if (isphone) {
            resetMail.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        final androidx.appcompat.app.AlertDialog.Builder passwordResetDialog = new androidx.appcompat.app.AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Edit Details");
        passwordResetDialog.setMessage(message);
        passwordResetDialog.setView(resetMail);

        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) view;
                textView.setText(resetMail.getText().toString().trim());
            }
        });

        passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        passwordResetDialog.create().show();
    }

    public void uploadPick(View view) {
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(openGalleryIntent, 1000);
    }

    public void openActivity(View view) {
        startActivity(new Intent(this, ViewDetails.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                //profileImage.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);

            }
        }

    }

    private void uploadImageToFirebase(final Uri imageUri) {
        // uplaod image to firebase storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        final StorageReference fileRef = storageReference.child("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
//                          Picasso.get().load(uri).into(profileImageView);
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(EditProfile.this);
                        sharedPreferences.edit().putString("chetanimageuri", imageUri.toString()).apply();
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

    public void changePassword(View view) {
        startActivity(new Intent(this, ChangePassword.class));
    }

    public void sync(View view) {
        progressBar.setVisibility(View.VISIBLE);
        FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseRef;
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("AccountTypeFindYourLawyer", false)) {
            mDatabaseRef = database.getReference("Client");
        } else {
            mDatabaseRef = database.getReference("Lawyer");
        }
        mDatabaseRef.child(auth.getUid()).child(C.address).setValue(address.getText().toString().trim());
        mDatabaseRef.child(auth.getUid()).child(C.phone).setValue(phone.getText().toString().trim());
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        address.setText(sharedPreferences.getString(StoreOnLocalStorage.address, "Click Here to change address"));
        phone.setText(sharedPreferences.getString(StoreOnLocalStorage.phone, "Click Here to change Mobile no."));

    }
}
