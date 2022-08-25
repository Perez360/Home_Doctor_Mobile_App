package com.example.home_doctor.My_Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.home_doctor.R;
import com.example.home_doctor.Recycle_Click_Listener;
import com.example.home_doctor.Storages.DoctorDetails;

import java.util.List;

public class Recommended_RecycleAdapter_home extends RecyclerView.Adapter<Recommended_RecycleAdapter_home.Recommended_Image_Holder> {
    List<DoctorDetails> listData;
    Context context;
    Recycle_Click_Listener recycle_click_listener;

    public Recommended_RecycleAdapter_home(List<DoctorDetails> listData, Recycle_Click_Listener recycle_click_listener, Context context) {
        this.listData = listData;
        this.context = context;
        this.recycle_click_listener = recycle_click_listener;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public Recommended_Image_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newVIew = LayoutInflater.from(context).inflate(R.layout.recommended_doctore_layout, null);
        Recommended_Image_Holder myRecommended_image_holder = new Recommended_Image_Holder(newVIew, recycle_click_listener);
        return myRecommended_image_holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull Recommended_Image_Holder holder, int position) {
        holder.textViewName.setText("Dr. " + listData.get(position).getName());
        holder.textViewSpec.setText(listData.get(position).getSpeciality());
        Glide.with(context).load(listData.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(holder.imageView);
        holder.ratingBar.setRating(listData.get(position).getRatings());

        holder.availabilty.startAnimation(AnimationUtils.loadAnimation(context, R.anim.blink_anim));
        if (listData.get(position).getAvailability().toUpperCase().equals("AVAILABLE")) {
            holder.availabilty.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.light_blue)));
        } else {
            holder.availabilty.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.red)));
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class Recommended_Image_Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewSpec;
        RatingBar ratingBar;
        ImageView availabilty;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @SuppressLint({"ResourceType", "UseCompatLoadingForDrawables"})
        public Recommended_Image_Holder(@NonNull View itemView, Recycle_Click_Listener recycle_click_listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rec_doc_img);
            textViewName = itemView.findViewById(R.id.rec_doc_name);
            textViewSpec = itemView.findViewById(R.id.rec_doc_spec);
            ratingBar = itemView.findViewById(R.id.rec_doc_rate);
            availabilty = itemView.findViewById(R.id.rec_Availabily);


            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    recycle_click_listener.onClick(getAdapterPosition());
                }
            });

            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recycle_click_listener.onlongClick(getAdapterPosition());
                    return true;
                }
            });

        }
    }


}
