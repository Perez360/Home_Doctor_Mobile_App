package com.example.home_doctor.Accounts_Related;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.home_doctor.Add_Photo;
import com.example.home_doctor.Check_Connection_Package.Check_For_Internet_Class;
import com.example.home_doctor.FireBase_Connection.FireBase_Con;
import com.example.home_doctor.R;

public class RegisterPage_Cont extends AppCompatActivity {
    public static LinearLayout getReigisterCont_ContentPage;
    public static AlertDialog.Builder alertBuilder;
    static public ProgressDialog progressDialog;
    public Check_For_Internet_Class check_con;
    private TextView getGotoLoginPage;
    private Button getCreateAcc;
    private EditText password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page_cont_security);
        initComp();
    }

    @Override
    public void onBackPressed() {

        getReigisterCont_ContentPage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.center_to_down));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 200);
    }

    private void initComp() {

        getReigisterCont_ContentPage = findViewById(R.id.registerCont_ContentPane);
        getGotoLoginPage = findViewById(R.id.gotoLoginPage);
        getCreateAcc = findViewById(R.id.createAccBut);
        password = findViewById(R.id.passwordText);
        confirmPassword = findViewById(R.id.confimPasswordText);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        findViewById(R.id.back_toolbarRC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getReigisterCont_ContentPage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.down_to_center));

        getGotoLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_con = new Check_For_Internet_Class(RegisterPage_Cont.this);
                if (Check_For_Internet_Class.isCon) {
                    alertBuilder = new AlertDialog.Builder(RegisterPage_Cont.this);
                    progressDialog = new ProgressDialog(RegisterPage_Cont.this);

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
                    startActivity(new Intent(RegisterPage_Cont.this, LoginPage.class));
                } else {


                }
            }
        });
        getCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getCreateAcc.startAnimation(AnimationUtils.loadAnimation(RegisterPage_Cont.this, R.anim.fadein));
                    }
                }, 100);
                getCreateAcc.startAnimation(AnimationUtils.loadAnimation(RegisterPage_Cont.this, R.anim.fadeout));

                if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    Toast.makeText(RegisterPage_Cont.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().contains(" ") || confirmPassword.getText().toString().contains(" ")) {
                    Toast.makeText(RegisterPage_Cont.this, "Password must not contain space.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().length() < 7) {
                    Toast.makeText(RegisterPage_Cont.this, "Password length is invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                check_con = new Check_For_Internet_Class(RegisterPage_Cont.this);
                progressDialog = new ProgressDialog(RegisterPage_Cont.this);
                if (Check_For_Internet_Class.isCon) {
                    startActivity(new Intent(getApplicationContext(), Add_Photo.class));
                    FireBase_Con fireBaseCon = new FireBase_Con(RegisterPage_Cont.this);

                    String Fullname = getIntent().getExtras().get("fullname").toString();
                    String Email = getIntent().getExtras().get("email").toString();
                    String Phone = getIntent().getExtras().get("phone").toString();
                    String Country = getIntent().getExtras().get("country").toString();
                    String Occupation = getIntent().getExtras().get("occupation").toString();
                    String Address = getIntent().getExtras().get("address").toString();
                    String Password = password.getText().toString();

                    //fireBaseCon.createUser(RegisterPage.fullname, RegisterPage.email, RegisterPage.phone, RegisterPage.getCountrySpinner, RegisterPage.address,RegisterPage.getOccupationSpinner, password);
                    fireBaseCon.createUser(Fullname, Email, Phone, Country, Occupation, Address, Password);
                } else {
                    alertBuilder = new AlertDialog.Builder(RegisterPage_Cont.this);
                    alertBuilder.setTitle("Error");
                    alertBuilder.setMessage("An internal occurred.");
                    alertBuilder.setIcon(R.drawable.ic_baseline_error_outline_24);
                    alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertBuilder.show();
                }
            }
        });
    }
}