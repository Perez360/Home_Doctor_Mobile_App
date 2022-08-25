package com.example.home_doctor;

import android.content.Context;
import android.widget.Toast;

public class MyToast {
    public static void showMessageString(Context context, String message) {
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show();
    }
    public static void showMessageNumber(Context context, int message) {
        android.widget.Toast.makeText(context, String.valueOf(message), android.widget.Toast.LENGTH_SHORT).show();
    }
}
