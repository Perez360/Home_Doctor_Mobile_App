package com.example.home_doctor;

import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.home_doctor.Accounts_Related.LoginPage;
import com.example.home_doctor.Accounts_Related.RegisterPage;
import com.example.home_doctor.Check_Connection_Package.Check_For_Internet_Class;


public class Dashboard extends AppCompatActivity {
    public static Button getLoginBut;
    public static Button getRegisterBut;
    public static LinearLayout getCenterPane;
    private Animation ani;
    private ImageView getToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        ani = AnimationUtils.loadAnimation(Dashboard.this, R.anim.down_to_center|R.anim.fadein);
        getCenterPane = findViewById(R.id.centerPane);
        getCenterPane.startAnimation(ani);
        initComp();


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        AlertDialog.Builder askToExit = new AlertDialog.Builder(this);
        askToExit.setTitle("Exit");
        askToExit.setMessage("Are you sure you want to exit?");
        askToExit.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        askToExit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getCenterPane.startAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.pop_out));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 200);

            }
        });
        askToExit.show();
    }

    public void initComp() {
        getRegisterBut = findViewById(R.id.registerbutD);
        getLoginBut = findViewById(R.id.loginbutD);
        getToolbar = findViewById(R.id.back_toolbarD);

        getToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder askToExit = new AlertDialog.Builder(Dashboard.this);
                askToExit.setTitle("Exit");
                askToExit.setMessage("Are you sure you want to exit?");
                askToExit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                askToExit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getCenterPane.startAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.pop_out));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 200);

                    }
                });
                askToExit.show();
            }
        });
        getLoginBut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                getToolbar.startAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.righttoleft));
                getCenterPane.startAnimation(AnimationUtils.loadAnimation(Dashboard.this,R.anim.down_to_center|R.anim.fadeout));
                //getLoginBut.startAnimation(AnimationUtils.loadAnimation(Dashboard.this,R.anim.button_effects));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //      getLoginBut.startAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.fadein));
                        Check_For_Internet_Class check_for_internet_class = new Check_For_Internet_Class(Dashboard.this);
                        if (Check_For_Internet_Class.isCon) {
                            startActivity(new Intent(Dashboard.this, LoginPage.class));
                        }
                    }
                }, 200);
            }
        });

        getRegisterBut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                getToolbar.startAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.righttoleft));
                getCenterPane.startAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.center_to_down));
                getRegisterBut.startAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.button_effects));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getRegisterBut.startAnimation(AnimationUtils.loadAnimation(Dashboard.this, R.anim.fadein));
                        Check_For_Internet_Class check_for_internet_class = new Check_For_Internet_Class(Dashboard.this);
                        if (Check_For_Internet_Class.isCon) {
                            startActivity(new Intent(Dashboard.this, RegisterPage.class));
                        }

                    }
                }, 200);

            }
        });
    }
}