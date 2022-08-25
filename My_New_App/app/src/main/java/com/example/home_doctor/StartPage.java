package com.example.home_doctor;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;

public class StartPage extends AppCompatActivity {
    static final int REQUEST_CODE = 100;
    public static Button getGoBut;
    public ConstraintLayout cons;
    public boolean isCon;
    private Animation ani;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  new ThemeChecker(this);

        setSplashScreen();
    }

    public void setSplashScreen() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.startpage);
            initComponent();
        } else {

            startActivity(new Intent(getApplicationContext(), Patient_home_Controller.class));
            finish();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) == false) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            } else {
                loadTOpOpenNewActivity();
                findViewById(R.id.start).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein));
            }
        }
    }

    private void checkConnection() {
        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMan.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            isCon = true;

        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Error");
            alert.setIcon(R.drawable.ic_baseline_error_outline_24);
            alert.setCancelable(false);
            alert.setMessage("No Internet Connection");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            //alert.show();
        }
    }

    private void initComponent() {
        getGoBut = findViewById(R.id.gobut);
        cons = findViewById(R.id.start);

        getGoBut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                getGoBut.setBackground(getApplication().getDrawable(R.drawable.continueapp));
                ani = AnimationUtils.loadAnimation(StartPage.this, R.anim.fadeout);
                permmitGPS();

            }
        });
    }

    private void loadTOpOpenNewActivity() {
        ProgressDialog proDiag = new ProgressDialog(StartPage.this);
        proDiag.setTitle("Please wait");
        proDiag.setMessage("Connecting to server...");
        proDiag.setCancelable(false);
        proDiag.show();
        cons.startAnimation(ani);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                proDiag.cancel();
                startActivity(new Intent(StartPage.this, Dashboard.class));
                finish();
            }
        }, 400);
    }

    public void permmitGPS() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);

    }

}