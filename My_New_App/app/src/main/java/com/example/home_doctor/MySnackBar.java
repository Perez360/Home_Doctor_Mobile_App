package com.example.home_doctor;

import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MySnackBar {
    public static void showMessageString(Context context, View v, String message) {
        Snackbar.make(context, v, message, Snackbar.LENGTH_SHORT).setAction("View", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patient_home_Controller.bottomNavigationView.setSelectedItemId(R.id.id_appointment_nav);
            }
        }).show();
    }

    public static void showMessageInteger(Context context, View v, int message) {
        Snackbar.make(context, v, "" + message, Snackbar.LENGTH_SHORT).setAction("View", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patient_home_Controller.bottomNavigationView.setSelectedItemId(R.id.id_appointment_nav);
            }
        }).show();
    }
}
