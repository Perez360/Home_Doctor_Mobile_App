package com.example.home_doctor.Services;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class Save_Appointments extends Service {
    public Save_Appointments() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    CountDownTimer countDownTimer;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
         countDownTimer= new CountDownTimer(5000, 2) {
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(getApplicationContext(), "Ticking", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "counter finished", Toast.LENGTH_LONG).show();
            }


         };
         countDownTimer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        countDownTimer.onFinish();
        super.onDestroy();

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        startService(rootIntent);
        Toast.makeText(this, "Service is restarted", Toast.LENGTH_SHORT).show();
        super.onTaskRemoved(rootIntent);
    }
}