package com.group.findyourlawyer;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.content.Intent;

import com.group.findyourlawyer.activity.ViewDetails;


public class InternetDialog {
    private Context context;

    public InternetDialog(){

    }
    public InternetDialog(Context context){
        this.context = context;
    }

    public void showNoInternetDialog(){
        final Dialog dialog1 = new Dialog(context, R.style.df_dialog);
        dialog1.setContentView(R.layout.dialog_no_internet);
        dialog1.setCancelable(true);
        dialog1.setCanceledOnTouchOutside(true);
        dialog1.show();
    }

    public  boolean getInternetStatus() {

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(!isConnected) {
            //show no internet dialog
            showNoInternetDialog();
        }
        return isConnected;
    }


}
