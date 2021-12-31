package com.group.findyourlawyer.central;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.group.findyourlawyer.models.Model;

public class StoreOnLocalStorage {
    public static final String name="name";
    public static final String address="address";
    public static final String dob="dob";
    public static final String email="email";
    public static final String phone="phone";
    public static final String gender="gender";
    public static final String mAccountType="";
    public static final String id="";
    public static void Store(Context context, Model model){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(name,model.getName());
        editor.putString(address,model.getAddress());
        editor.putString(dob,model.getDob());
        editor.putString(email,model.getEmail());
        editor.putString(id,model.getId());
        editor.putString(phone,model.getPhoneNo());
        editor.putString(gender,model.getGender());
        editor.putString(mAccountType,model.getAccountType());
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
        model.setAccountType(sharedPreferences.getString(mAccountType,""));
        return model;
    }
}
