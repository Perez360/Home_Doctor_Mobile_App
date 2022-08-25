package com.example.home_doctor.FireBase_Connection;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.home_doctor.Accounts_Related.LoginPage;
import com.example.home_doctor.Accounts_Related.RegisterPage;
import com.example.home_doctor.Patient_home_Controller;
import com.example.home_doctor.R;
import com.example.home_doctor.Register_package.Register_Accounts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FireBase_Con {
    public static ProgressDialog progressDialog;
    public static AlertDialog.Builder alertBuilder;
    private static boolean isRegistered;
    public FirebaseAuth firebaseAuth;
    public FirebaseDatabase firebaseDatabase;
    Activity cont;

    public FireBase_Con(Activity activity) {
        cont = activity;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void loginType(EditText usernameBox, EditText passwordBox) {
        String username = usernameBox.getText().toString();
        String password = passwordBox.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(cont, "Username field is empty, Please check field", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(cont, "Password field is empty, Please check field", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 8) {
            Toast.makeText(cont, "Invalid password length, Length must be 8 or more ", Toast.LENGTH_SHORT).show();
            return;
        }

        Task<AuthResult> loginTask = firebaseAuth.signInWithEmailAndPassword(username, password);
        progressDialog = new ProgressDialog(cont);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Signing in");
        progressDialog.show();
        loginTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    progressDialog.cancel();
                                                    LoginPage.getLoginContentPane.startAnimation(AnimationUtils.loadAnimation(cont, R.anim.center_to_down));
                                                    cont.startActivity(new Intent(cont, Patient_home_Controller.class));
                                                    cont.finish();
                                                }
                                            }
                                        }
        );

        loginTask.addOnFailureListener(new OnFailureListener() {
                                           @Override
                                           public void onFailure(@NonNull Exception e) {
                                               progressDialog.dismiss();
                                               alertBuilder = new AlertDialog.Builder(cont.getApplicationContext());
                                               alertBuilder.setTitle("Internal Error");
                                               alertBuilder.setIcon(R.drawable.ic_baseline_error_outline_24);
                                               alertBuilder.setMessage(e.getMessage());
                                               alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                   @Override
                                                   public void onClick(DialogInterface dialogInterface, int i) {
                                                   }
                                               });
                                               FireBase_Con.progressDialog.cancel();
                                               alertBuilder.show();

                                           }
                                       }
        );

    }

    public void createUser(String Fullname, String Email, String Phone, String Country, String Occupation, String Address, String Password) {


        Register_Accounts register_accounts = new Register_Accounts(Fullname, Email, Phone, Country, Occupation, Address, Password);
        register_accounts.setFullName(Fullname);
        register_accounts.setEmail(Email);
        register_accounts.setPhone(Phone);
        register_accounts.setCountry(Country);
        register_accounts.setLocation(Address);
        register_accounts.setOccupation(Occupation);
        register_accounts.setPassword(Password);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users");
        Task<AuthResult> createAthenticationtask = firebaseAuth.createUserWithEmailAndPassword(Email, Password);
        alertBuilder = new AlertDialog.Builder(cont);
        progressDialog = new ProgressDialog(cont);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Signing up");
        progressDialog.setButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                progressDialog.dismiss();
            }
        });
        progressDialog.show();
        CountDownTimer countDownTimer = new CountDownTimer(50000, 5000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if (isRegistered == false) {
                    progressDialog.cancel();
                    alertBuilder.setTitle("Error");
                    alertBuilder.setMessage("There was error connecting server");
                    alertBuilder.show();
                    alertBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                }
            }
        };
        countDownTimer.start();
        Task<AuthResult> task = createAthenticationtask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Task<Void> task1 = databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(register_accounts);

                    task1.addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                alertBuilder = new AlertDialog.Builder(cont);
                                alertBuilder.setTitle("Done!!!");
                                alertBuilder.setMessage("Account succesfully created");
                                alertBuilder.setPositiveButton("Add another user.", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                        cont.startActivity(new Intent(cont, RegisterPage.class));
                                    }
                                });
                                alertBuilder.setNegativeButton("Sign in", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        cont.startActivity(new Intent(cont, LoginPage.class));
                                        dialogInterface.dismiss();
                                        cont.finish();
                                    }
                                });
                                alertBuilder.show();
                            }
                        }
                    });
                }
            }
        });
        createAthenticationtask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e != null) {
                    progressDialog.cancel();
                    //cont.finish();
                    alertBuilder = new AlertDialog.Builder(cont);
                    alertBuilder.setTitle("Error");
                    alertBuilder.setMessage("An error occurred.\n" + e.getMessage());
                    alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alertBuilder.show();

                }
            }
        });


    }
}