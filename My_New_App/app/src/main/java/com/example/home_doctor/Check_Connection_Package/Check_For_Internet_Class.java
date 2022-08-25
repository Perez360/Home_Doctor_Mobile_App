package com.example.home_doctor.Check_Connection_Package;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

public class Check_For_Internet_Class {
    public static boolean isCon = false;

    public Check_For_Internet_Class(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMan.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            isCon = true;
        }else {
            AlertDialog.Builder alBuilder=new AlertDialog.Builder(context);
            alBuilder.setTitle("Error");
            alBuilder.setMessage("No Internet, Check network settings");
            alBuilder.setNegativeButton("Check connections", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    context.startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));
                }
            });
            alBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });;
            alBuilder.show();
            isCon=false;
        }
    }
}