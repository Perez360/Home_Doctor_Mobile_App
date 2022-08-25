package com.example.home_doctor.Accounts_Related;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.home_doctor.Check_Connection_Package.Check_For_Internet_Class;
import com.example.home_doctor.Dashboard;
import com.example.home_doctor.FireBase_Connection.FireBase_Con;
import com.example.home_doctor.R;


public class LoginPage extends AppCompatActivity {
    public static Button getLogin;
    public static LinearLayout getLoginContentPane;
    public EditText getEmail, getPassword;
    ImageView getToolbar;
    private TextView getGotoSignUp;
    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertBuilder;
    private Check_For_Internet_Class check_con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        initComp();
    }

    @SuppressLint("ResourceType")
    public void initComp() {
        getEmail = findViewById(R.id.emailText);
        getPassword = findViewById(R.id.passwordText);
        getGotoSignUp = findViewById(R.id.gotoSignUp);
        getLogin = findViewById(R.id.loginButL);
        getLoginContentPane = findViewById(R.id.loginContentPane);
        getToolbar = findViewById(R.id.back_toolbarL);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        getLoginContentPane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        });

        getLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Check_For_Internet_Class check_for_internet_class = new Check_For_Internet_Class(LoginPage.this);
                if (Check_For_Internet_Class.isCon) {
                    FireBase_Con fireBaseCon = new FireBase_Con(LoginPage.this);
                    fireBaseCon.loginType(getEmail, getPassword);
                } else {

                    alertBuilder = new AlertDialog.Builder(LoginPage.this);
                    progressDialog = new ProgressDialog(LoginPage.this);

                    progressDialog.cancel();
                    alertBuilder.setTitle("Error");
                    alertBuilder.setIcon(R.drawable.ic_baseline_error_outline_24);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setMessage("No Internet Connection.\nPlease check your network settings.");
                    alertBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            RegisterPage_Cont.progressDialog.cancel();
                        }
                    });
                    alertBuilder.show();
                }

            }
        });

        getToolbar.startAnimation(AnimationUtils.loadAnimation(LoginPage.this, R.anim.lefttoright));
        getToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getToolbar.startAnimation(AnimationUtils.loadAnimation(LoginPage.this, R.anim.righttoleft));
                onBackPressed();
            }
        });
        getLogin.startAnimation(AnimationUtils.loadAnimation(LoginPage.this, R.anim.button_effects));
        getLogin.startAnimation(AnimationUtils.loadAnimation(LoginPage.this, R.anim.fadein));

        getLoginContentPane.startAnimation(AnimationUtils.loadAnimation(LoginPage.this, R.anim.down_to_center));
        getGotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getToolbar.startAnimation(AnimationUtils.loadAnimation(LoginPage.this, R.anim.righttoleft));
                getLoginContentPane.startAnimation(AnimationUtils.loadAnimation(LoginPage.this, R.anim.center_to_down));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LoginPage.this, RegisterPage.class));
                        finish();
                    }
                }, 200);

            }
        });
    }

    @Override
    public void openOptionsMenu() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        getLoginContentPane.startAnimation(AnimationUtils.loadAnimation(this, R.anim.center_to_down));
        //Dashboard.getLoginBut.setBackground(getApplication().getDrawable(R.drawable.login_unclicked_blue));
        getToolbar.startAnimation(AnimationUtils.loadAnimation(LoginPage.this, R.anim.righttoleft));
        Dashboard.getLoginBut.setAnimation(null);
        Dashboard.getCenterPane.startAnimation(AnimationUtils.loadAnimation(this, R.anim.down_to_center));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 200);
    }
}