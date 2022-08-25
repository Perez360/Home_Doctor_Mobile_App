package com.example.home_doctor;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.home_doctor.Bottom_Navigation_Pages.Fragments.Appointment_Fragment;
import com.example.home_doctor.Bottom_Navigation_Pages.Fragments.Chat_person_Fragment;
import com.example.home_doctor.Bottom_Navigation_Pages.Fragments.Doctors_fragment;
import com.example.home_doctor.Bottom_Navigation_Pages.Fragments.Home_Fragment;
import com.example.home_doctor.Bottom_Navigation_Pages.Fragments.More_Page_Fragment;
import com.example.home_doctor.My_Adapters.ViewPager_Adapter;
import com.example.home_doctor.Storages.DoctorDetails;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Patient_home_Controller extends AppCompatActivity {
    public static BottomNavigationView bottomNavigationView;
    public  ViewPager viewPager;
    public Doctors_fragment doctors_fragment;
    public Home_Fragment home_fragment;
    public Appointment_Fragment appointment_fragment;
    public More_Page_Fragment more_page_fragment;
    public Chat_person_Fragment chat_person_fragment;
    public BadgeDrawable badgeDrawableChat;
    public BadgeDrawable badgeDrawableAppointment;
    public BadgeDrawable badgeDrawableMore;
    public BadgeDrawable badgeDrawableHome;
    public BadgeDrawable badgeDrawableDoctors;
    private LinearLayout bottom_underline;
    private FloatingActionButton floatingActionButton;
    private MenuItem prevMenuItem;
    private ImageView prevDot;
    private ArrayList<DoctorDetails> dammyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_home_controller);
        initComp();
    }

    private void setUpDammyAccounts() {
        Dammy_Data dammy_data;
        dammy_data = new Dammy_Data(new Random());
        dammyList = new ArrayList<>();
        dammyList.addAll(dammy_data.createDammy_Data());
    }

    private void initComp() {
        bottomNavigationView = findViewById(R.id.navigationButton);
        bottom_underline = findViewById(R.id.home_bottom_layout);
        floatingActionButton = findViewById(R.id.floating_Button);
        setUpDammyAccounts();
        setUpViewPager();
        setUpBadgeDrawable();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint({"ResourceType"})
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.id_home_nav:
                        viewPager.setCurrentItem(0);

                        return true;

                    case R.id.id_my_doc_nav:
                        viewPager.setCurrentItem(1);

                        return true;

                    case R.id.id_appointment_nav:
                        viewPager.setCurrentItem(2);
                        return true;

                    case R.id.id_chats_nav:
                        viewPager.setCurrentItem(3);

                        return true;
                    case R.id.id_more_nav:
                        viewPager.setCurrentItem(4);

                        return true;
                    default:
                        return onNavigationItemSelected(item);
                }

            }
        });


        //set item reselected listener for buttonNavigationView
        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

            }
        });

        prevMenuItem = bottomNavigationView.getMenu().findItem(R.id.id_home_nav).setChecked(true);


    }

    private void setFloatingButton(int position) {
        switch (position) {
            case 0:
            case 2:
            case 1:
            case 4:
                if (floatingActionButton.isShown()) {
                    floatingActionButton.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.center_to_down));
                    floatingActionButton.hide();
                }
                break;
            case 3:
                if (!floatingActionButton.isShown()) {
                    floatingActionButton.show();
                    floatingActionButton.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down_to_center));
                }
                break;

        }
    }


    public void setUpViewPager() {
        viewPager = findViewById(R.id.view_Pager);
        viewPager.setClipToOutline(true);


        List<Fragment> listFragment = new ArrayList<>();

        home_fragment = Home_Fragment.newInstance(dammyList);
        doctors_fragment = Doctors_fragment.newInstance(dammyList);
        appointment_fragment = Appointment_Fragment.newInstance(dammyList);
        chat_person_fragment = Chat_person_Fragment.newInstance(dammyList);
        more_page_fragment = new More_Page_Fragment();

        listFragment.add(home_fragment);
        listFragment.add(doctors_fragment);
        listFragment.add(appointment_fragment);
        listFragment.add(chat_person_fragment);
        listFragment.add(more_page_fragment);

        ViewPager_Adapter viewPager_class = new ViewPager_Adapter(this, getSupportFragmentManager(), listFragment);
        viewPager.setAdapter(viewPager_class);
        viewPager.setOffscreenPageLimit(5);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int lastScroll;
            int lastPos;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //bottomNavigationView.getMenu().findItem(R.id.id_home_nav).setTitle("" + position + " - " + positionOffset + " - " + positionOffsetPixels);

                lastScroll = positionOffsetPixels;


            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageSelected(int position) {
                setFloatingButton(position);
                if (listFragment.get(position) instanceof Home_Fragment) {
                    if (prevMenuItem != null) {
                        MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.id_home_nav);
                        menuItem.setChecked(true);
                        prevMenuItem = menuItem;
                        prevMenuItem.setChecked(false);

                        if (prevDot != null) {
                            prevDot.setImageDrawable(null);
                            ImageView imageView1 = ((ImageView) bottom_underline.getChildAt(position));
                            imageView1.setImageResource(R.drawable.dots);
                            prevDot = imageView1;
                        }
                        if (badgeDrawableHome != null) {

                            new Handler(getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    badgeDrawableHome.clearNumber();
                                    badgeDrawableHome.setVisible(false);
                                }
                            }, 500);
                        }

                    }
                } else if (listFragment.get(position) instanceof Doctors_fragment) {
                    if (prevMenuItem != null) {
                        MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.id_my_doc_nav);
                        menuItem.setChecked(true);
                        prevMenuItem = menuItem;
                        prevMenuItem.setChecked(false);

                        if (prevDot != null) {
                            prevDot.setImageDrawable(null);
                            ImageView imageView2 = ((ImageView) bottom_underline.getChildAt(position));
                            imageView2.setImageResource(R.drawable.dots);
                            prevDot = imageView2;
                        }


                        if (badgeDrawableDoctors != null) {
                            new Handler(getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    badgeDrawableDoctors.clearNumber();
                                    badgeDrawableDoctors.setVisible(false);
                                }
                            }, 500);

                        }

                    }
                } else if (listFragment.get(position) instanceof Appointment_Fragment) {
                    if (prevMenuItem != null) {
                        MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.id_appointment_nav);
                        menuItem.setChecked(true);
                        prevMenuItem = menuItem;
                        prevMenuItem.setChecked(false);

                        if (prevDot != null) {
                            prevDot.setImageDrawable(null);
                            ImageView imageView3 = ((ImageView) bottom_underline.getChildAt(position));
                            imageView3.setImageResource(R.drawable.dots);
                            prevDot = imageView3;
                        }

                        if (badgeDrawableAppointment != null) {

                            new Handler(getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    badgeDrawableAppointment.clearNumber();
                                    badgeDrawableAppointment.setVisible(false);
                                }
                            }, 500);
                        }

                    }
                } else if (listFragment.get(position) instanceof Chat_person_Fragment) {
                    if (prevMenuItem != null) {
                        MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.id_chats_nav);
                        menuItem.setChecked(true);
                        prevMenuItem = menuItem;
                        prevMenuItem.setChecked(false);

                        if (prevDot != null) {
                            prevDot.setImageDrawable(null);
                            ImageView imageView4 = ((ImageView) bottom_underline.getChildAt(position));
                            imageView4.setImageResource(R.drawable.dots);
                            prevDot = imageView4;
                        }
                        if (badgeDrawableChat != null) {

                            new Handler(getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    badgeDrawableChat.clearNumber();
                                    badgeDrawableChat.setVisible(false);
                                }
                            }, 500);
                        }
                    }


                } else if (listFragment.get(position) instanceof More_Page_Fragment) {
                    if (prevMenuItem != null) {
                        MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.id_more_nav);
                        menuItem.setChecked(true);
                        prevMenuItem = menuItem;
                        prevMenuItem.setChecked(false);


                        if (prevDot != null) {
                            prevDot.setImageDrawable(null);
                            ImageView imageView5 = ((ImageView) bottom_underline.getChildAt(position));
                            imageView5.setImageResource(R.drawable.dots);
                            prevDot = imageView5;
                        }

                        if (badgeDrawableMore != null) {

                            new Handler(getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    badgeDrawableMore.clearNumber();
                                    badgeDrawableMore.setVisible(false);
                                }
                            }, 500);
                        }

                    }
                }

                lastPos = position;
                hideKeyboard();

            }


            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
        viewPager.setCurrentItem(0);

        ImageView imageView = ((ImageView) bottom_underline.getChildAt(0));
        imageView.setImageResource(R.drawable.dots);
        prevDot = imageView;


    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
        }
    }


    private void setUpBadgeDrawable() {
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                badgeDrawableChat = bottomNavigationView.getOrCreateBadge(R.id.id_chats_nav);
                badgeDrawableChat.setVisible(true);
                badgeDrawableChat.setNumber(33);
                badgeDrawableChat.setBackgroundColor(getColor(R.color.badgecolor));
                badgeDrawableChat.setVerticalOffset(Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics())));

                badgeDrawableAppointment = bottomNavigationView.getOrCreateBadge(R.id.id_appointment_nav);
                badgeDrawableAppointment.setVisible(true);
                badgeDrawableAppointment.setNumber(7);
                badgeDrawableAppointment.setBackgroundColor(getColor(R.color.badgecolor));
                badgeDrawableAppointment.setVerticalOffset(Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics())));

               /* badgeDrawableHome=bottomNavigationView.getOrCreateBadge(R.id.id_appointment_nav);
                badgeDrawableHome.setVisible(true);
                badgeDrawableHome.setNumber(7);
                badgeDrawableHome.setBackgroundColor(getColor(R.color.red));
                badgeDrawableHome.setVerticalOffset(Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics())));
*/
              /*  badgeDrawableMore=bottomNavigationView.getOrCreateBadge(R.id.id_appointment_nav);
                badgeDrawableMore.setVisible(true);
                badgeDrawableMore.setNumber(7);
                badgeDrawableMore.setBackgroundColor(getColor(R.color.red));
                badgeDrawableMore.setVerticalOffset(Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics())));
*/

//                badgeDrawableDoctors=bottomNavigationView.getOrCreateBadge(R.id.id_appointment_nav);
//                badgeDrawableDoctors.setVisible(true);
//                badgeDrawableDoctors.setNumber(1);
//                badgeDrawableDoctors.setBackgroundColor(getColor(R.color.red));
//                badgeDrawableDoctors.setVerticalOffset(Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics())));
            }
        }, 15000);


    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 1) {
            viewPager.setCurrentItem(0);
        } else if (viewPager.getCurrentItem() == 2) {
            viewPager.setCurrentItem(1);
        } else if (viewPager.getCurrentItem() == 3) {
            viewPager.setCurrentItem(0);

        } else if (viewPager.getCurrentItem() == 4) {
            viewPager.setCurrentItem(0);
        } else {
            super.onBackPressed();
        }
    }
}
