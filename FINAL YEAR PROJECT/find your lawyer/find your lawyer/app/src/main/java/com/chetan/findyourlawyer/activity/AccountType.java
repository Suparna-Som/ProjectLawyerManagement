package com.chetan.findyourlawyer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.chetan.findyourlawyer.R;
import com.chetan.findyourlawyer.central.C;

public class AccountType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);
    }

    public void client(View view) {
        C.createProfileWithClient = true;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("AccountTypeFindYourLawyer", true);
        editor.apply();
        startActivty();
    }

    public void lawyer(View view) {
        C.createProfileWithClient = false;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("AccountTypeFindYourLawyer", false);
        editor.apply();
        startActivty();
    }

    void startActivty() {
        startActivity(new Intent(this, Registration.class));
    }
}