package com.group.findyourlawyer.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group.findyourlawyer.MainActivity;
import com.group.findyourlawyer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        fAuth=FirebaseAuth.getInstance();
        if (fAuth!=null && fAuth.getCurrentUser()!=null && fAuth.getCurrentUser().isEmailVerified()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public void createAccount(View view) {
        startActivity(new Intent(this, AccountType.class));
    }

    public void forgotPassword(View view) {
        final EditText resetMail = new EditText(view.getContext());
        final androidx.appcompat.app.AlertDialog.Builder passwordResetDialog = new androidx.appcompat.app.AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Reset Password ?");
        passwordResetDialog.setMessage("Enter Your Email Address \n To Receive Password Reset Link.");
        passwordResetDialog.setView(resetMail);

        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // extract the email and send reset link
                String mail = resetMail.getText().toString();
                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Login.this, "Reset Link Sent To Your Email.", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // close the dialog
            }
        });

        passwordResetDialog.create().show();
    }

    public void login(View view) {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email is Required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Password is Required.");
            return;
        }

        if (password.length() < 6) {
            mPassword.setError("Password Must be >= 6 Characters");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (!Objects.requireNonNull(fAuth.getCurrentUser()).isEmailVerified()) {
                        final AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
                        alert.setTitle("Not Verified");

                        alert.setMessage("This Account is not verified yet, You have to verify this account first to use this app. \"Check your Email for verify your account\"");
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alert.show();
                    } else {
                        Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                        Login.this.finish();
                    }
                } else {
                    Toast.makeText(Login.this, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}