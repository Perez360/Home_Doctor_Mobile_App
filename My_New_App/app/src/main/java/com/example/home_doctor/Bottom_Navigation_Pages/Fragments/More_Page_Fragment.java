package com.example.home_doctor.Bottom_Navigation_Pages.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.home_doctor.Dashboard;
import com.example.home_doctor.R;
import com.google.firebase.auth.FirebaseAuth;

public class More_Page_Fragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_page, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Toolbar toolbar = requireActivity().findViewById(R.id.more_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.exit_app:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        AlertDialog alertDialog = builder.create();
                        builder.setTitle("Exit");
                        builder.setMessage("Are you sure you want to sign out?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth fireBase = FirebaseAuth.getInstance();
                                fireBase.signOut();
                                requireActivity().finish();
                                startActivity(new Intent(requireContext(), Dashboard.class));
                            }
                        });
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                        return true;
                    default:

                        return false;
                }

            }
        });

        Button about = getActivity().findViewById(R.id.more_about_us);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("About us");
                builder.setCancelable(true);
                builder.setMessage("A study from the Ghana Health Service has revealed self medications as a common habit among most Ghanaians when they feel indisposed. In this act, people use drugs without the advice of a medical practitioner.\n" +
                        "Consequently, this said self medication habit is usually accompanied by a couple of dangers which include overdosing unprescribed drugs and in worst cases causing death.\n" +
                        "Moreover, without some kind of “protocol”, a patient is usually stacked in long queues to wait their turn, there fore this mobile app is invented to address any of the instances above. \n");

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });


        Button mission = getActivity().findViewById(R.id.more_our_mission);
        mission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Our mission");
                builder.setCancelable(true);
                builder.setMessage("The mobile app is developed to assist patients to consult a medical practitioner at the comfort of their homes with ease and at a fast customer service.");
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }


}
