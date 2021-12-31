package com.group.findyourlawyer.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.group.findyourlawyer.InternetDialog;
import com.group.findyourlawyer.MainActivity;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.central.C;
import com.group.findyourlawyer.central.StoreOnLocalStorage;
import com.group.findyourlawyer.models.Model;
import com.group.findyourlawyer.models.StoreOnDatabase;

import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Registration extends AppCompatActivity {
    public static final String TAG = "TAG";
    final Calendar myCalendar = Calendar.getInstance();
    EditText mFullName, mEmail, mPassword, mPhone, maddress1, maddress2, maddresscity, mzipcode, mGender;
    TextView mLoginBtn;
    ProgressBar progressBar;
    FirebaseAuth fauth;
    FirebaseFirestore fStore;
    EditText editText;
    Model user = new Model();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            editText.setText(sdf.format(myCalendar.getTime()));
            user.setDob(sdf.format(myCalendar.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFullName = findViewById(R.id.Name);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
//        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);
        progressBar = findViewById(R.id.progress_circular_register);
        maddress1 = findViewById(R.id.addressline1);
        maddress2 = findViewById(R.id.addressline2);
        maddresscity = findViewById(R.id.city);
        mzipcode = findViewById(R.id.zip);
        mGender = findViewById(R.id.Gender);

        if (new InternetDialog(this).getInternetStatus()) {

        }

        fauth = FirebaseAuth.getInstance();
        if (fauth.getCurrentUser() != null) {
            if (fauth.getCurrentUser().isEmailVerified()) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }
    }

    public void next(View view) {
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        final String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email is Required.");

            return;
        }
        if (!email.matches(regex)) {
            mEmail.setError("Invalid Email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mPassword.setError(getString(R.string.passwordRequired));
            return;
        }

        if (password.length() < 6) {
            mPassword.setError(getString(R.string.passwordError));
            return;
        }

        final String fullName = mFullName.getText().toString();
        final String phone = mPhone.getText().toString();
        final String address = maddress1.getText().toString();
        String address2 = " ";
        if (maddress2 != null && !maddress2.getText().toString().equals("")) {
            address2 = maddress2.getText().toString();
        }
        final String addresscity = maddresscity.getText().toString();
        final String zipcode = mzipcode.getText().toString();

        final String gender = mGender.getText().toString();
        String city = maddresscity.getText().toString();
        if (gender.isEmpty()) {
            mGender.setError("Please Select Gender");
            return;
        }
        if (address.isEmpty()) {
            maddress1.setError("Please Select Gender");
            return;
        }
        if (zipcode.isEmpty()) {
            mzipcode.setError("Please Select Gender");
            return;
        }
        if (phone.length() != 10) {
            mPhone.setError("Phone Number Length must be 10 digit");
            return;
        }
        if (gender.isEmpty()) {
            mGender.setError("This field cant be empty");
            return;
        }
        user.setName(fullName);
        user.setEmail(email);
        String stringBuilder = "Address : " + address + "\n" +
                address2 + "\n" +
                addresscity + "\n" +
                zipcode + "\n";

        user.setAddress(stringBuilder.toString());
        user.setGender(gender);
        user.setPhoneNo(phone);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Registration.this);
        user.setAccountType(sharedPreferences.getString("AccountTypeFindYourLawyer", ""));
        C.model = user;

        progressBar.setVisibility(View.VISIBLE);
        // register the user in firebase
        fauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // send verification link
                    FirebaseUser fuser = fauth.getCurrentUser();
                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Registration.this, "Verification Email Has been Sent.", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Registration.this, getString(R.string.ErrorVerificatio), Toast.LENGTH_LONG).show();
                        }
                    });

                    Toast.makeText(Registration.this, "User Created.", Toast.LENGTH_SHORT).show();

                    user.setCreatedOn(System.currentTimeMillis() + "");
                    user.setPhoneNo(phone);
                    user.setId(fuser.getUid());
                    StoreOnDatabase storeOnDatabase = new StoreOnDatabase();
                    storeOnDatabase.store(user);

                    C.model = user;
                    StoreOnLocalStorage.Store(Registration.this, user);
                    progressBar.setVisibility(View.INVISIBLE);
                    if (!C.createProfileWithClient) {
                        startActivity(new Intent(Registration.this, LawPractices.class));
                    } else {
                        startActivity(new Intent(Registration.this, ProfilePicUpload.class));
                    }
                } else {
                    Toast.makeText(Registration.this, "Already Have an Account.", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    public void login(View view) {

        startActivity(new Intent(this, Login.class));
        finish();
    }

    public void showGenderOption(View view) {
        final TextView gender = findViewById(R.id.Gender);
        PopupMenu popup = new PopupMenu(this, view);
        // Inflate the menu from xml
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
        // Setup menu item selection
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.gender_male:
                        user.setGender("Male");
                        gender.setText("Gender : Male");

                        return true;
                    case R.id.gender_female:
                        user.setGender("Female");
                        gender.setText("Gender : Female");
                        return true;
                    default:
                        return false;
                }
            }
        });
        // Handle dismissal with: popup.setOnDismissListener(...);
        // Show the menu
        popup.show();
    }

    public void datePick(View view) {
        editText = (EditText) view;
        new DatePickerDialog(this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}