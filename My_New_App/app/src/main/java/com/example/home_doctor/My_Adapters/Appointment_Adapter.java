package com.example.home_doctor.My_Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.home_doctor.Bottom_Navigation_Pages.Fragments.Appointment_Fragment;
import com.example.home_doctor.R;
import com.example.home_doctor.Storages.AppointmentDetails;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Appointment_Adapter extends ArrayAdapter {
    List<AppointmentDetails> list;

    public Appointment_Adapter(Context context, List<AppointmentDetails> list) {
        super(context, R.layout.appointment_layout_list, list);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newView = convertView;
        if (newView == null) {
            newView = LayoutInflater.from(getContext()).inflate(R.layout.appointment_layout_list, parent, false);
        }


        AppointmentDetails appointmentDetails = (AppointmentDetails) getItem(position);
        // Name
        TextView name = newView.findViewById(R.id.appoint_doc_name);
        name.setText(appointmentDetails.getName());

        //Image
        CircleImageView profile = newView.findViewById(R.id.appoint_doc_img);
        Glide.with(getContext()).load(appointmentDetails.getImg()).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(profile);

        //Button cancel
        Button cancel = newView.findViewById(R.id.appoint_doc_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Cancel Appointment");
                builder.setCancelable(false);
                builder.setMessage("Are you sure you want to cancel this appointment with " + name.getText() + "?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String getName=appointmentDetails.getName();
                        list.remove(position);

                        notifyDataSetChanged();
                        Snackbar.make(getContext(),cancel,"Appointment with " + getName + " has successfully been canceld",Snackbar.LENGTH_SHORT).show();
                        //Toast.makeText(getContext(), , Toast.LENGTH_SHORT).show();
                        if (list.isEmpty()) {
                            Appointment_Fragment.empty_appointment.setVisibility(View.VISIBLE);
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });

        // Time text
        TextView time = newView.findViewById(R.id.appoint_doc_time_text);

        time.setText(appointmentDetails.getTime());


        return newView;
    }


}
