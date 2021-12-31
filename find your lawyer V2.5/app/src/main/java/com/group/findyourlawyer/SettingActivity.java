package com.group.findyourlawyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.group.findyourlawyer.activity.ChangePassword;
import com.group.findyourlawyer.activity.EditProfile;
import com.group.findyourlawyer.activity.Incomplete;
import com.group.findyourlawyer.activity.ProfilePicUpload;

public class SettingActivity extends AppCompatActivity {
    private Switch darkModeSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        if(new DarkModePrefManager(this).isNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        setContentView(R.layout.activity_setting);
        //function for enabling dark mode
        setDarkModeSwitch();
    }
    private void setDarkModeSwitch(){
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        darkModeSwitch.setChecked(new DarkModePrefManager(this).isNightMode());
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DarkModePrefManager darkModePrefManager = new DarkModePrefManager(SettingActivity.this);
                darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode());
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
            }
        });
    }

    public void changePassword(View view) {
        startActivity(new Intent(SettingActivity.this, ChangePassword.class));
    }

    public void EDProfile(View view) {
        startActivity(new Intent(SettingActivity.this, EditProfile.class));
    }

    public void pDetailEdit(View view) {
        startActivity(new Intent(SettingActivity.this, ProfilePicUpload.class));
    }
}

class DarkModePrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "education-dark-mode";

    private static final String IS_NIGHT_MODE = "IsNightMode";


    public DarkModePrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setDarkMode(boolean isFirstTime) {
        editor.putBoolean(IS_NIGHT_MODE, isFirstTime);
        editor.commit();
    }

    public boolean isNightMode() {
        return pref.getBoolean(IS_NIGHT_MODE, true);
    }

}
