package com.group.findyourlawyer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.group.findyourlawyer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    EditText mOldpass, mNewpass, mNnewpass2;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mOldpass = findViewById(R.id.oldpass);
        mNewpass = findViewById(R.id.password);
        mNnewpass2 = findViewById(R.id.password2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_changepass);
    }

    public void changePassword(final View view) {
        if (mNewpass.getText().toString().isEmpty()) {
            mNewpass.setError("this field is Required");
            return;
        }
        if (mOldpass.getText().toString().isEmpty()) {
            mOldpass.setError("this field is Required");
            return;
        }
        String oldPass = mOldpass.getText().toString().trim();
        final String newPass;
        progressBar.setVisibility(View.VISIBLE);
        if (mNewpass.getText().toString().trim().equals(mNnewpass2.getText().toString().trim())) {
            newPass = mNewpass.getText().toString().trim();
        } else {
            Snackbar.make(view, "Password not matches", Snackbar.LENGTH_LONG).show();
            return;
        }

        final FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();
        final String email = user.getEmail();
        AuthCredential credential = EmailAuthProvider.getCredential(email, oldPass);

        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (!task.isSuccessful()) {
                                Snackbar snackbar_fail = Snackbar
                                        .make(view, "Something went wrong. Please try again later", Snackbar.LENGTH_LONG);
                                snackbar_fail.show();
                            } else {
                                Snackbar snackbar_su = Snackbar
                                        .make(view, "Password Successfully Modified", Snackbar.LENGTH_LONG);
                                snackbar_su.show();
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                } else {
                    Snackbar snackbar_su = Snackbar
                            .make(view, "Authentication Failed", Snackbar.LENGTH_LONG);
                    snackbar_su.show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}