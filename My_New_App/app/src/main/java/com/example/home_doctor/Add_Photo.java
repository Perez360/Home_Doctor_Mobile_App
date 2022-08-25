package com.example.home_doctor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class Add_Photo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
    }
    private void  initComp() {
        Toolbar addPhoto_Toolbar = findViewById(R.id.add_photo_toolbar);
        setSupportActionBar(addPhoto_Toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        addPhoto_Toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == android.R.id.home) {
                    Add_Photo.super.onBackPressed();
                    return true;
                }
                return onMenuItemClick(item);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}