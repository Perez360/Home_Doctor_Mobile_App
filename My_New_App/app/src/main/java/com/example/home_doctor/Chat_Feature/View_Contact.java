package com.example.home_doctor.Chat_Feature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.home_doctor.R;

public class View_Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_contact);
    }
    @Override
    public void onBackPressed(){
        finish();

    }
}