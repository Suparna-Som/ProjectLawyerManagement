package com.chetan.findyourlawyer.central;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.chetan.findyourlawyer.activity.Login;
import com.chetan.findyourlawyer.models.Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class C {
    public static boolean demo=false;
    public static boolean createProfileWithClient=false;
    public static Model model=null;


    public static final String name="name";
    public static final String address="address";
    public static final String dob="dob";
    public static final String email="email";
    public static final String phone="phoneNo";
    public static final String lawPractices="lawPractices";
    public static final String gender="gender";
}
