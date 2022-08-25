package com.example.home_doctor.My_Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.home_doctor.Bottom_Navigation_Pages.Fragments.Doctors_fragment;
import com.example.home_doctor.R;
import com.example.home_doctor.Recycle_Click_Listener;
import com.example.home_doctor.Storages.DoctorDetails;
import com.xeoh.android.texthighlighter.TextHighlighter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Doctors_Adapter extends RecyclerView.Adapter<Doctors_Adapter.Doctors_ViewHolder> {
    Context context;
    List<DoctorDetails> list;
    EditText search;
    TextHighlighter textHighlighter = new TextHighlighter();
    Recycle_Click_Listener recycleClick_listener;

    public Doctors_Adapter(Context context, EditText search, List<DoctorDetails> list, Recycle_Click_Listener recycleClick_listener) {
        this.context = context;
        this.list = list;
        this.search = search;
        this.recycleClick_listener = recycleClick_listener;
    }

    @NonNull
    @Override
    public Doctors_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doctors_list, parent, false);
        Doctors_ViewHolder doctors_viewHolder = new Doctors_ViewHolder(view);
        return doctors_viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("UseCompatTextViewDrawableApis")
    @Override
    public void onBindViewHolder(@NonNull Doctors_ViewHolder holder, int position) {
        DoctorDetails doctorDetails = (DoctorDetails) list.get(position);
        holder.doc_location.setText(doctorDetails.getLocation());
        holder.doc_name.setText("Dr. " + (String) doctorDetails.getName());
        holder.ratingBar.setRating(doctorDetails.getRatings());
        holder.availability.setText(doctorDetails.getAvailability());
        holder.amount.setText(doctorDetails.getAmount());
        holder.working_days.setText(doctorDetails.getWorking_days());
        holder.working_hours.setText(doctorDetails.getWorking_hours());
        holder.speciality.setText(doctorDetails.getSpeciality());


        if (doctorDetails.getAvailability().equals("Not Available")) {
            holder.availability.setTextColor(Color.RED);
            holder.availability.setCompoundDrawableTintList(ColorStateList.valueOf(Color.RED));
        } else {
            holder.availability.setTextColor(context.getColor(R.color.blue));
            holder.availability.setCompoundDrawableTintList(ColorStateList.valueOf(context.getColor(R.color.blue)));
        }


        Glide.with(context).load(doctorDetails.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.doc_img);

        if (Doctors_fragment.FILTER != null) {
            if (Doctors_fragment.FILTER.equals("NAME")) {
                //highlight doctor name
                textHighlighter.setBackgroundColor(context.getResources().getColor(R.color.blue));
                textHighlighter.addTarget(holder.doc_name);
                textHighlighter.setForegroundColor(context.getResources().getColor(R.color.white));
                textHighlighter.highlight(search.getText().toString(), TextHighlighter.CASE_INSENSITIVE_MATCHER);
            }
            if (Doctors_fragment.FILTER.equals("LOCATION")) {
                //highlight location
                textHighlighter.setBackgroundColor(context.getResources().getColor(R.color.blue));
                textHighlighter.addTarget(holder.doc_location);
                textHighlighter.setForegroundColor(context.getResources().getColor(R.color.white));
                textHighlighter.highlight(search.getText().toString(), TextHighlighter.CASE_INSENSITIVE_MATCHER);
            }
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class Doctors_ViewHolder extends RecyclerView.ViewHolder {
        TextView doc_name;
        TextView doc_location;
        RatingBar ratingBar;
        CircleImageView doc_img;
        TextView amount;
        TextView working_days;
        TextView working_hours;
        TextView availability;
        TextView speciality;

        public Doctors_ViewHolder(@NonNull View itemView) {
            super(itemView);
            doc_name = itemView.findViewById(R.id.doc_name);
            doc_location = itemView.findViewById(R.id.doc_location);
            doc_img = itemView.findViewById(R.id.doc_img);
            ratingBar = itemView.findViewById(R.id.ratingbar_my_doctor);
            amount = itemView.findViewById(R.id.doctor_list_amount);
            working_days = itemView.findViewById(R.id.list_working_days);
            working_hours = itemView.findViewById(R.id.list_working_hours);
            availability = itemView.findViewById(R.id.doctor_list_availability);
            speciality = itemView.findViewById(R.id.dos_speciality_list);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recycleClick_listener.onClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recycleClick_listener.onlongClick(getAdapterPosition());
                    return true;
                }
            });


        }

    }
}