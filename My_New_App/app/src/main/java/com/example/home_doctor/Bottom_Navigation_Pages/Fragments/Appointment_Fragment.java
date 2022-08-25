package com.example.home_doctor.Bottom_Navigation_Pages.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.home_doctor.DataObserver.Appointments_DataObserver;
import com.example.home_doctor.My_Adapters.Appointment_Adapter;
import com.example.home_doctor.R;
import com.example.home_doctor.Storages.AppointmentDetails;
import com.example.home_doctor.Storages.DoctorDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Appointment_Fragment extends Fragment {
    private static final String TAG = "DAMMYLIST";
    public static Appointment_Adapter appointment_adapter;
    public static ListView listView;
    public BottomNavigationView bottomNavigationView;
    public static List<AppointmentDetails> AppointmentList;
    public static TextView empty_appointment;
    public List<DoctorDetails> dammyList;
    SwipeRefreshLayout swipeRefreshLayout;

    public Appointment_Fragment() {


    }

    public static Appointment_Fragment newInstance(ArrayList<DoctorDetails> dammyList) {

        Appointment_Fragment appointment_fragment = new Appointment_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(TAG, dammyList);
        appointment_fragment.setArguments(args);
        return appointment_fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            dammyList=new ArrayList<>();
            dammyList.addAll((ArrayList<DoctorDetails>) bundle.getSerializable(TAG));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appointment_fragment, viewGroup, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout = getActivity().findViewById(R.id.refresh_appointment);
        empty_appointment = getActivity().findViewById(R.id.empty_appointments);

        bottomNavigationView = requireActivity().findViewById(R.id.navigationButton);
        empty_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationView.setSelectedItemId(R.id.id_my_doc_nav);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                appointment_adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        listView = getActivity().findViewById(R.id.appoitment_listview);
        AppointmentList = new ArrayList<AppointmentDetails>();
        appointment_adapter = new Appointment_Adapter(getContext(), AppointmentList);
        listView.setAdapter(appointment_adapter);
        appointment_adapter.registerDataSetObserver(new Appointments_DataObserver(listView));

    }
}