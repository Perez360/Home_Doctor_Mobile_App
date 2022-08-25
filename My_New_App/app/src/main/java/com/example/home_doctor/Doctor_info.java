package com.example.home_doctor;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.home_doctor.Bottom_Navigation_Pages.Fragments.Appointment_Fragment;
import com.example.home_doctor.Chat_Feature.Chat_Activity;
import com.example.home_doctor.Storages.AppointmentDetails;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Doctor_info extends AppCompatActivity {
    Button book, send_message, report_doctor;
    Toolbar toolbar;
    RatingBar ratingBar;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //getWindow().setStatusBarColor(android.R.color.transparent);
        }
        setContentView(R.layout.doctor_info);
        initComp();

    }

    public void initComp() {
        book = findViewById(R.id.book_in_view_doctor);
        toolbar = findViewById(R.id.view_doctor_toolbar);
        report_doctor = findViewById(R.id.report_doctor_in_view_doctor);
        send_message = findViewById(R.id.send_message_in_view_doctor);
        ratingBar = findViewById(R.id.getRatings_doctor_info);


        setToolBar();


        String name = (String) getIntent().getExtras().get("name");
        String location = (String) getIntent().getExtras().get("location");
        int img = (int) getIntent().getExtras().get("image");
        int rating = (int) getIntent().getExtras().get("ratings");

        if (Appointment_Fragment.AppointmentList.contains(name)) {
            book.setEnabled(false);
            book.setText("Booked");
            send_message.setEnabled(true);
        }

        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(name, img);
            }
        });

        report_doctor.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                report();
            }
        });


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBookCommand(name, img);
            }
        });

        ImageView circleImageView = findViewById(R.id.doc_info_img);
        Glide.with(getApplicationContext()).load(img).into(circleImageView);
        circleImageView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein));

        TextView getUsername = findViewById(R.id.view_doctor_getUsername);
        getUsername.setText(name);

        TextView getLocation = findViewById(R.id.view_doctor_getLocation);
        getLocation.setText(location);


        ratingBar.setRating(rating);
    }

    private void report() {
        AlertDialog.Builder builderReport = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builderReport.create();
        builderReport.setCancelable(false);
        builderReport.setTitle("Why are you reporting this doctor?");

        final View reportView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.report_doctor_view, null, false);
        EditText reportText = reportView.findViewById(R.id.edit_report);


        builderReport.setView(reportView);
        builderReport.setPositiveButton("Report", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                View view = getWindow().getDecorView().getRootView();
                MySnackBar.showMessageString(getApplicationContext(), view, "Thanks for your feedback, This Doctor has successfully been reported.\nThank you.");

            }
        });
        builderReport.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderReport.show();


    }

    private void sendMessage(String name, int img) {

        Intent chatPage = new Intent(getApplicationContext(), Chat_Activity.class);
        chatPage.putExtra("username", name);
        chatPage.putExtra("image", img);
        startActivity(chatPage);
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void doBookCommand(String name, int img) {

        AlertDialog.Builder builderBook = new AlertDialog.Builder(Doctor_info.this);
        builderBook.setTitle("Book an appointment");
        builderBook.setCancelable(false);
        builderBook.setMessage("Are you sure you want to book an appointment with " + name + " ?");
        builderBook.setPositiveButton("Book", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                String getTime = simpleDateFormat.format(calendar.getTime());
                if (getTime.charAt(0) == '0') {
                    getTime = getTime.substring(1, getTime.length());
                }
                Appointment_Fragment.empty_appointment.setVisibility(View.GONE);
                AppointmentDetails appointmentDetails = new AppointmentDetails(name, getTime, img);
                Appointment_Fragment.AppointmentList.add(appointmentDetails);
                Appointment_Fragment.appointment_adapter.notifyDataSetChanged();


                book.setText("Booked");

                AlertDialog.Builder builderSucces = new AlertDialog.Builder(Doctor_info.this);
                builderSucces.setCancelable(true);
                builderSucces.setView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.succes_book_layout, null, false));

                builderSucces.setMessage("You have successfully booked this appointment");
                builderSucces.setPositiveButton("Check Appointments", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Patient_home_Controller.bottomNavigationView.setSelectedItemId(R.id.id_appointment_nav);

                    }
                });
                builderSucces.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

            }
        });
        builderBook.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderBook.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}