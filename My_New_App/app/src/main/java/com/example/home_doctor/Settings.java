package com.example.home_doctor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Settings extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        initComp();

    }

    public void initComp() {
        setSupportActionBar(findViewById(R.id.main_settings_Tool));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        ImageView imageView = findViewById(R.id.themeimg);
        imageView.setImageResource(R.drawable.ic_baseline_theme_6_24);
        TextView textView_text = findViewById(R.id.changethemetext);
        TextView theme_text = findViewById(R.id.theme_hint);

        SharedPreferences sharedPreferences = getSharedPreferences("Mytheme", Context.MODE_PRIVATE);
        String oldTheme = sharedPreferences.getString("Theme", null).trim();
        theme_text.setText(oldTheme);
        RelativeLayout relativeLayout = findViewById(R.id.changetheme);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                View themeDialogue = LayoutInflater.from(getApplicationContext()).inflate(R.layout.theme_layout, null, false);
                RadioGroup radioGroup = themeDialogue.findViewById(R.id.radio_group);
                RadioButton light = themeDialogue.findViewById(R.id.light);
                RadioButton dark = themeDialogue.findViewById(R.id.dark);
                if (oldTheme.equals("Light")) {
                    light.setChecked(true);
                } else {
                    dark.setChecked(true);
                }

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = getSharedPreferences("Mytheme", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        String getTheme = ((RadioButton) themeDialogue.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString().trim();
                        ((TextView) theme_text).setText(getTheme);
                        editor.putString("Theme", getTheme);
                        editor.apply();

                        AlertDialog.Builder builderRestartApp = new AlertDialog.Builder(Settings.this);
                        builderRestartApp.setTitle("Restart");
                        builderRestartApp.setMessage("System must be restarted before theme can be applied, do you want to continue?");
                        builderRestartApp.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                                startActivity(new Intent(getApplicationContext(), StartPage.class));
                            }
                        });
                        builderRestartApp.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        if (radioGroup.getCheckedRadioButtonId() == R.id.light && AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                            builderRestartApp.show();

                        } else if (radioGroup.getCheckedRadioButtonId() == R.id.dark && AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                            builderRestartApp.show();
                        }


                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setView(themeDialogue);
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
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
}