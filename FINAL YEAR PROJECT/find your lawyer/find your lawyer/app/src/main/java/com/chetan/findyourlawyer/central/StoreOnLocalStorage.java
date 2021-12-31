package com.chetan.findyourlawyer.central;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.chetan.findyourlawyer.models.Model;

public class StoreOnLocalStorage {
    public static final String name="chetan_name";
    public static final String address="chetan_address";
    public static final String dob="chetan_dob";
    public static final String email="chetan_email";
    public static final String phone="chetan_phone";
    public static final String gender="chetan_gender";
    public static void Store(Context context, Model model){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(name,model.getName());
        editor.putString(address,model.getAddress());
        editor.putString(dob,model.getDob());
        editor.putString(email,model.getEmail());
        editor.putString(phone,model.getPhoneNo());
        editor.putString(gender,model.getGender());
        editor.apply();
    }
    public static Model getDetails(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        Model model=new Model();
        model.setName(sharedPreferences.getString(name,"Name"));
        model.setAddress(sharedPreferences.getString(address,"Address"));
        model.setDob(sharedPreferences.getString(dob,"01/01/2000"));
        model.setEmail(sharedPreferences.getString(email,"user@domail.com"));
        model.setPhoneNo(sharedPreferences.getString(phone,"9925346926"));
        model.setGender(sharedPreferences.getString(gender,"Male"));
        return model;
    }
}
