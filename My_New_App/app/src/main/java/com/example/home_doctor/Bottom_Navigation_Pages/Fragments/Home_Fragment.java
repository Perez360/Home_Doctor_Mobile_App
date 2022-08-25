package com.example.home_doctor.Bottom_Navigation_Pages.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.denzcoskun.imageslider.ImageSlider;
import com.example.home_doctor.Doctor_info;
import com.example.home_doctor.My_Adapters.Recommended_RecycleAdapter_home;
import com.example.home_doctor.R;
import com.example.home_doctor.Recycle_Click_Listener;
import com.example.home_doctor.SetSliders_Active;
import com.example.home_doctor.Settings;
import com.example.home_doctor.Storages.DoctorDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Fragment extends Fragment {
    private final static String TAG = "DAMMYLIST";
    public RecyclerView recyclerViewRecommendedDoc;
    public BottomNavigationView bottomNavigationView;
    ImageSlider imageSlider;
    CircleImageView profile_pic;
    NestedScrollView scrollView;
    SetSliders_Active setSliders_active;
    Recommended_RecycleAdapter_home recommended_recycleAdapter_home;
    List<DoctorDetails> dammyList;


    public Home_Fragment() {

    }

    public static Home_Fragment newInstance(ArrayList<DoctorDetails> dammyList) {
        Home_Fragment home_fragment = new Home_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(TAG, dammyList);
        home_fragment.setArguments(args);
        return home_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            dammyList = new ArrayList<DoctorDetails>();
            dammyList.addAll((ArrayList<DoctorDetails>) bundle.getSerializable(TAG));
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageSlider = requireActivity().findViewById(R.id.slider);
        profile_pic = requireActivity().findViewById(R.id.home_toolbar_profile_pic);
        recyclerViewRecommendedDoc = requireActivity().findViewById(R.id.recRecycleView);
        scrollView = requireActivity().findViewById(R.id.homeScroll);
        bottomNavigationView = requireActivity().findViewById(R.id.navigationButton);


        setToolbar();
        setUpRecommendedDoctors();

        setSliders_active = new SetSliders_Active(requireContext(), imageSlider);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, viewGroup, false);
        return view;
    }

    public void setToolbar() {
        Toolbar toolbar = requireActivity().findViewById(R.id.home_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.my_doctors_home:
                        ViewPager viewPager = requireActivity().findViewById(R.id.view_Pager);
                        viewPager.setCurrentItem(1);
                        return true;

                    case R.id.settings_home:
                        startActivity(new Intent(requireContext(), Settings.class));
                        return true;

                    case R.id.help_and_support_home:
                        Uri uri = Uri.parse("http://www.google.com");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        return true;

                    default:
                        return onOptionsItemSelected(item);
                }
            }
        });
        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationView.setSelectedItemId(R.id.id_more_nav);

            }
        });

    }


    private void setUpRecommendedDoctors() {
        List<DoctorDetails> recommendedDoctorsList = new ArrayList<>();
        recommendedDoctorsList.clear();

        for (int i = 0; i < dammyList.size(); i++) {
            if (dammyList.get(i).getRatings() > 2) {
                recommendedDoctorsList.add(dammyList.get(i));
            }
        }
        Collections.shuffle(recommendedDoctorsList);
        Recycle_Click_Listener recycle_click_listener = new Recycle_Click_Listener() {
            @Override
            public void onClick(int position) {
                DoctorDetails doctorDetails = (DoctorDetails) recommendedDoctorsList.get(position);
                Intent intent = new Intent(getContext(), Doctor_info.class);
                intent.putExtra("name", (doctorDetails.getName()));
                intent.putExtra("location", (doctorDetails.getLocation()));
                intent.putExtra("image", doctorDetails.getImage());
                intent.putExtra("ratings", doctorDetails.getRatings());

                requireActivity().startActivity(intent);

            }

            @SuppressLint("ResourceAsColor")

            @Override
            public void onlongClick(int position) {
                Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK));
                    }
                }

                AlertDialog.Builder buildPic = new AlertDialog.Builder(getContext(), R.style.AlertDialog_has_no_background);
                AlertDialog alertDialog = buildPic.create();
                View view = getLayoutInflater().inflate(R.layout.home_profile_viewer, null);
                ImageView imageView = view.findViewById(R.id.home_show_pic_long_press);
                CardView cardView=view.findViewById(R.id.rec_Img_layout);
                Glide.with(requireContext()).load(recommendedDoctorsList.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.zoomin));
                Animation animation = view.getAnimation();
                animation.setDuration(50);


                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //alertDialog.dismiss();
                    }
                });
                imageView.setOnTouchListener(new View.OnTouchListener() {
                    int dx;
                    int dy;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            dx = (int) (v.getX()-event.getRawX());
                            dy = (int) ((int) v.getY()-event.getRawY());
                            return true;
                        }
                        if (event.getAction()==MotionEvent.ACTION_MOVE){
                           /* v.animate().
                                    x(dx+event.getRawX()).
                                    y(dy+event.getRawY()).setDuration(0).start();*/
                            cardView.animate().
                                    x(dx+event.getRawX()).
                                    y(dy+event.getRawY()).setDuration(0).start();

                            return true;
                        }

                        if (event.getAction()==MotionEvent.ACTION_UP){
                            Animation animation1=AnimationUtils.loadAnimation(getContext(),R.anim.fadeout);
                            animation1.setDuration(50);
                            animation1.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    alertDialog.dismiss();
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            view.startAnimation(animation1);

                            return true;
                        }
                        return false;
                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    int a=requireActivity().getWindowManager().getCurrentWindowMetrics().getBounds().width();
                    int b=requireActivity().getWindowManager().getCurrentWindowMetrics().getBounds().height();
                    alertDialog.setView(view);
                    alertDialog.setCancelable(true);
                    alertDialog.show();
                    //alertDialog.getWindow().setAttributes(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT).copyFrom(alertDialog.getWindow().getAttributes()));
                }


            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recommended_recycleAdapter_home = new Recommended_RecycleAdapter_home(recommendedDoctorsList, recycle_click_listener, getContext());
        recyclerViewRecommendedDoc.setLayoutManager(linearLayoutManager);
        recyclerViewRecommendedDoc.setAdapter(recommended_recycleAdapter_home);
        recyclerViewRecommendedDoc.setHasFixedSize(true);
        // new LinearSnapHelper().attachToRecyclerView(recyclerViewRecommendedDoc);


    }


    @Override
    public void onResume() {
        super.onResume();
        recommended_recycleAdapter_home.notifyDataSetChanged();

    }
}
