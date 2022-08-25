package com.example.home_doctor.Accounts_Related;

import static com.example.home_doctor.Accounts_Related.RegisterPage_Cont.progressDialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.home_doctor.Check_Connection_Package.Check_For_Internet_Class;
import com.example.home_doctor.Dashboard;
import com.example.home_doctor.R;


public class RegisterPage extends AppCompatActivity {
    public static Spinner getCountrySpinner, getOccupationSpinner;
    public static EditText username, fullname, email, phone, address;
    public static LinearLayout getRegisterContentPane;
    private final String[] occupations = {"I am a", "Doctor", "Patient"};
    private Button getNextBut;
    private Check_For_Internet_Class check_con;
    private AlertDialog.Builder alertBuilder;

    public static void checkFields(AppCompatActivity cont) {
        if (TextUtils.isEmpty(RegisterPage.username.getText().toString())) {
            Toast.makeText(cont, "Username field is empty, Please check field", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(RegisterPage.fullname.getText().toString())) {
            Toast.makeText(cont, "Fullname field is empty, Please check field", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(RegisterPage.email.getText().toString())) {
            Toast.makeText(cont, "Email field is empty, Please check field", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(RegisterPage.phone.getText().toString())) {
            Toast.makeText(cont, "Phone field is empty, Please check field", Toast.LENGTH_SHORT).show();
            return;
        }
        if (((String) getCountrySpinner.getSelectedItem()).equals("Choose Country")) {
            Toast.makeText(cont, "Country field is empty, Please check field", Toast.LENGTH_SHORT).show();
            return;
        }
        if (((String) getOccupationSpinner.getSelectedItem()).equals("I am a")) {
            Toast.makeText(cont, "Account type field is empty, Please check field", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(RegisterPage.address.getText().toString())) {
            Toast.makeText(cont, "Address field is empty, Please check field", Toast.LENGTH_SHORT).show();
            return;
        }
        getRegisterContentPane.startAnimation(AnimationUtils.loadAnimation(cont, R.anim.center_to_down));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(cont, RegisterPage_Cont.class);
                intent.putExtra("fullname", fullname.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("country", getCountrySpinner.getSelectedItem().toString());
                intent.putExtra("address", address.getText().toString());
                intent.putExtra("occupation", getOccupationSpinner.getSelectedItem().toString());

                cont.startActivity(intent);

            }
        }, 200);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        iniComponent();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        getRegisterContentPane.startAnimation(AnimationUtils.loadAnimation(this, R.anim.center_to_down));
        Dashboard.getCenterPane.startAnimation(AnimationUtils.loadAnimation(this, R.anim.down_to_center));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 200);

    }

    public void iniComponent() {
        username=findViewById(R.id.usernameTextR);
        fullname = findViewById(R.id.fullnameTextR);
        email = findViewById(R.id.emailTextR);
        phone = findViewById(R.id.phoneTextR);
        address = findViewById(R.id.addressTextR);

        getCountrySpinner = findViewById(R.id.country_spinner);
        getOccupationSpinner = findViewById(R.id.occupation_spinner);

        setCountries(getCountrySpinner);
        setOccupations(getOccupationSpinner);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getNextBut = findViewById(R.id.nextBut);
        getRegisterContentPane = findViewById(R.id.registerContentPane);


        getRegisterContentPane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        });

        ImageView back = findViewById(R.id.back_toolbarR);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.startAnimation(AnimationUtils.loadAnimation(RegisterPage.this, R.anim.fadeout));
                RegisterPage.super.onBackPressed();
            }
        });
        back.startAnimation(AnimationUtils.loadAnimation(RegisterPage.this, R.anim.fadein));

        getRegisterContentPane.startAnimation(AnimationUtils.loadAnimation(RegisterPage.this, R.anim.down_to_center|R.anim.fadein));

        getNextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNextBut.startAnimation(AnimationUtils.loadAnimation(RegisterPage.this, R.anim.fadein));
                    }
                }, 100);
                getNextBut.startAnimation(AnimationUtils.loadAnimation(RegisterPage.this, R.anim.fadeout));
                check_con = new Check_For_Internet_Class(RegisterPage.this);
                alertBuilder = new AlertDialog.Builder(RegisterPage.this);
                progressDialog = new ProgressDialog(RegisterPage.this);

                progressDialog.setTitle("Please wait...");
                progressDialog.setMessage("Connecting to server");
                progressDialog.show();
                if (Check_For_Internet_Class.isCon) {
                    progressDialog.cancel();
                    checkFields(RegisterPage.this);
                } else {
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

    }



    private void setCountries(Spinner getCountrySpinner) {
        getCountrySpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, All_Countries.countryNames));
    }

    private void setOccupations(Spinner getOccupationSpinner) {
        getOccupationSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, occupations));
    }
}