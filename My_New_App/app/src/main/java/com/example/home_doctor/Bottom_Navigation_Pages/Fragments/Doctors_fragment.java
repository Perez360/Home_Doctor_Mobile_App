package com.example.home_doctor.Bottom_Navigation_Pages.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home_doctor.Chat_Feature.Chat_Activity;
import com.example.home_doctor.Custom_Views.CustomEdittext;
import com.example.home_doctor.DataObserver.DocotorRecycle_Observer;
import com.example.home_doctor.Doctor_info;
import com.example.home_doctor.My_Adapters.Doctors_Adapter;
import com.example.home_doctor.R;
import com.example.home_doctor.Recycle_Click_Listener;
import com.example.home_doctor.Storages.AppointmentDetails;
import com.example.home_doctor.Storages.Chat_Person_Details;
import com.example.home_doctor.Storages.DoctorDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nex3z.flowlayout.FlowLayout;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
public class Doctors_fragment extends Fragment implements Recycle_Click_Listener, Serializable {
    private static final String TAG = "DAMMYLIST";
    public static String FILTER;
    public BottomNavigationView bottomNavigationView;
    public CustomEdittext search_text;
    public List<DoctorDetails> detailsHolder;
    public List<DoctorDetails> newDetailsList;
    public Doctors_Adapter doctors_adapter;
    public RecyclerView recycleView_doctors;
    public ImageView clear;
    public ImageButton search_btn;
    public String FILTER_TYPE = "";
    private LinearLayout filterHolder;
    private FlowLayout filter_flw;
    private TextView filter_sel;
    private Toolbar doctors_toolbar;
    private ImageView back;


    public static Doctors_fragment newInstance(ArrayList<DoctorDetails> list) {
        Doctors_fragment doctors_fragment = new Doctors_fragment();
        Bundle args = new Bundle();
        args.putSerializable(TAG, list);
        doctors_fragment.setArguments(args);
        return doctors_fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            detailsHolder = new ArrayList<>();
            newDetailsList = new ArrayList<DoctorDetails>();
            detailsHolder.addAll((ArrayList<DoctorDetails>) bundle.getSerializable(TAG));
            newDetailsList.addAll(detailsHolder);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View newView = inflater.inflate(R.layout.doctors_fragment, viewGroup, false);
        return newView;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        setToolbar();

        search_text = requireActivity().findViewById(R.id.search);
        clear = requireActivity().findViewById(R.id.clear_text);
        recycleView_doctors = requireActivity().findViewById(R.id.recycleView_my_doctors);
        search_btn = requireActivity().findViewById(R.id.search_doctor_button);
        filterHolder = requireActivity().findViewById(R.id.filterHolder);
        filter_flw = requireActivity().findViewById(R.id.filter_flw);
        filter_sel = requireActivity().findViewById(R.id.filter_sel);
        doctors_toolbar = requireActivity().findViewById(R.id.doctors_toolbar);
        back = requireActivity().findViewById(R.id.doctors_back);


        doctors_adapter = new Doctors_Adapter(getContext(), search_text, detailsHolder, this);
        recycleView_doctors.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycleView_doctors.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recycleView_doctors.setAdapter(doctors_adapter);


        //recycleView_doctors.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(10f)));

        DocotorRecycle_Observer doctors_observer = new DocotorRecycle_Observer();
        doctors_adapter.registerAdapterDataObserver(doctors_observer);

        search_text.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().startsWith(" ") && s.toString().length() == 1) {
                    search_text.setText("");
                }
                if (search_text.getText().toString().length() > 0) {
                    if (clear.getVisibility() == View.GONE) {
                        clear.setVisibility(View.VISIBLE);

                    }

                    search_btn.setVisibility(View.VISIBLE);
                    filterHolder.setVisibility(View.GONE);
                    makeSearch(s.toString());

                } else {
                    search_btn.setVisibility(View.GONE);
                    detailsHolder.clear();
                    detailsHolder.addAll(newDetailsList);
                    filterHolder.setVisibility(View.VISIBLE);
                    if (filter_sel.getVisibility() == View.GONE) {
                        clear.setVisibility(View.GONE);
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        search_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {

                if (search_text.getText().toString().length() > 0) {
                    hideKeyboard();
                    search_text.clearFocus();
                    filterHolder.setVisibility(View.GONE);

                    makeSearch(search_text.getText().toString());

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recycleView_doctors.scrollToPosition(0);
                        }
                    }, 200);
                }
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter_sel.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        search_text.setText("");

                        search_text.clearFocus();
                    }
                }, 300);


            }
        });
        search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_text.requestFocus();

                if (filter_sel.getVisibility() == View.VISIBLE) {
                    clear.setVisibility(View.VISIBLE);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        filterHolder.setVisibility(LinearLayout.VISIBLE);
                    }
                }, 300);


            }
        });

        recycleView_doctors.setOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (search_text.hasFocus()) {

                    if (recyclerView.getScrollState() == 1) {
                        if (dy < 0) {
                            if (filterHolder.getVisibility() == View.VISIBLE) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        filterHolder.setVisibility(View.GONE);
                                    }
                                }, 200);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        doctors_toolbar.setVisibility(View.GONE);
                                    }
                                }, 300);

                            }

                        } else {

                            hideKeyboard();
                            search_text.clearFocus();
                            if (filterHolder.getVisibility() == View.VISIBLE) {

                                filterHolder.setVisibility(View.GONE);

                            }

                        }
                    }
                } else {

                    hideKeyboard();
                    search_text.clearFocus();
                }

            }
        });
        search_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (filterHolder.getVisibility() == View.GONE) {
                        filterHolder.setVisibility(LinearLayout.VISIBLE);
                    }
                    back.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doctors_toolbar.setVisibility(View.GONE);

                        }
                    }, 300);

                    if (filter_sel.getVisibility() == View.VISIBLE || search_text.getText().toString().length() > 0) {
                        clear.setVisibility(View.VISIBLE);
                    }


                } else {
                    filterHolder.setVisibility(View.GONE);
                    hideKeyboard();

                    back.setVisibility(View.GONE);
                    clear.setVisibility(View.GONE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doctors_toolbar.setVisibility(View.VISIBLE);
                        }
                    }, 300);

                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear.setVisibility(View.GONE);
                search_text.setText("");
                filter_sel.setVisibility(View.GONE);
                search_text.setCompoundDrawablesWithIntrinsicBounds(getContext().getDrawable(R.drawable.ic_search_942), null, null, null);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        filterHolder.setVisibility(View.VISIBLE);
                    }
                }, 200);

                detailsHolder.clear();
                detailsHolder.addAll(newDetailsList);
                doctors_adapter.notifyDataSetChanged();


                FILTER_TYPE = "";
            }
        });


        filter_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterHolder.getVisibility() == View.GONE) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            filterHolder.setVisibility(View.VISIBLE);
                        }
                    }, 200);

                }
            }
        });
        for (int i = 0; i < filter_flw.getChildCount(); i++) {
            TextView getTextView = (TextView) filter_flw.getChildAt(i);
            getTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (filter_sel.getVisibility() == View.GONE) {
                        Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.zoomin);
                        a.setDuration(200);
                        filter_sel.startAnimation(a);
                    }
                    search_text.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                    filter_sel.setVisibility(TextView.VISIBLE);
                    filter_sel.setText(getTextView.getText());
                    clear.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            filterHolder.setVisibility(LinearLayout.GONE);
                        }
                    }, 100);

                    detailsHolder.clear();

                    String getText = search_text.getText().toString().toLowerCase();
                    String getType = (String) ((TextView) v).getText();
                    if (getType.equals("Locations")) {
                        FILTER_TYPE = "LOCATIONS";
                        if (search_text.getText().toString().length() == 0) {
                            detailsHolder.clear();
                            detailsHolder.addAll(newDetailsList);
                        } else {

                            for (DoctorDetails doctor_info : newDetailsList) {
                                if (doctor_info.getLocation().toLowerCase().contains(getText)) {
                                    detailsHolder.add(doctor_info);
                                }
                            }
                        }
                    } else if (getType.equals("Specialists")) {
                        FILTER_TYPE = "SPECIALISTS";
                        if (search_text.getText().toString().length() == 0) {
                            detailsHolder.clear();
                            detailsHolder.addAll(newDetailsList);
                        } else {

                            for (DoctorDetails doctor_info : newDetailsList) {
                                if (doctor_info.getSpeciality().toLowerCase().contains(getText)) {
                                    detailsHolder.add(doctor_info);
                                }
                            }
                        }
                    } else if (getType.equals("Available")) {
                        FILTER_TYPE = "AVAILABLE";
                        if (search_text.getText().toString().length() == 0) {
                            detailsHolder.clear();
                            detailsHolder.addAll(newDetailsList);
                        } else {

                            for (DoctorDetails doctor_info : newDetailsList) {
                                if (doctor_info.getAvailability().toLowerCase().contains(getText)) {
                                    detailsHolder.add(doctor_info);
                                }
                            }
                        }
                    } else if (getType.equals("Charges")) {
                        FILTER_TYPE = "CHARGES";
                        if (search_text.getText().toString().length() == 0) {
                            detailsHolder.clear();
                            detailsHolder.addAll(newDetailsList);
                        } else {

                            for (DoctorDetails doctor_info : newDetailsList) {
                                if (doctor_info.getAmount().toLowerCase().contains(getText)) {
                                    detailsHolder.add(doctor_info);
                                }
                            }
                        }

                    }

                    doctors_adapter.notifyDataSetChanged();
                }
            });
        }


    }


    private void setToolbar() {
        doctors_toolbar = requireActivity().findViewById(R.id.doctors_toolbar);
        doctors_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.doctor_menu:
                        return true;
                    case R.id.help_doctor:
                        return true;
                    case R.id.text_highlighter_doctor:
                        return true;
                    default:
                        return onMenuItemClick(item);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(requireActivity().getWindow().getDecorView().getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
        }
    }

    public void sort(List list) {
        Collections.sort(list, new Comparator<DoctorDetails>() {
            @Override
            public int compare(DoctorDetails o1, DoctorDetails o2) {
                return o1.name.compareTo(o2.name);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(int position) {
        hideKeyboard();
        search_text.clearFocus();
        DoctorDetails doctorDetails = (DoctorDetails) detailsHolder.get(position);
        Intent intent = new Intent(getContext(), Doctor_info.class);
        intent.putExtra("name", (doctorDetails.getName()));
        intent.putExtra("location", (doctorDetails.getLocation()));
        intent.putExtra("image", doctorDetails.getImage());
        intent.putExtra("ratings", doctorDetails.getRatings());

        requireActivity().startActivity(intent);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onlongClick(int position) {
        recycleView_doctors.setLayoutFrozen(true);
        hideKeyboard();
        search_text.clearFocus();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertDialog_has_no_background);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        DoctorDetails doctorDetails = (DoctorDetails) detailsHolder.get(position);
        View alertview = getLayoutInflater().inflate(R.layout.view_doctor_dialog, null, false);
        TextView title = alertview.findViewById(R.id.view_doc_dialog_title);
        TextView bookAlert = alertview.findViewById(R.id.book_doctor_alert);
        TextView sendMessageAlert = alertview.findViewById(R.id.send_message_alert);
        TextView viewDoctorAlert = alertview.findViewById(R.id.view_doctor_alert);
        TextView reportAlert = alertview.findViewById(R.id.report_alert);
        ImageView likeAlert = alertview.findViewById(R.id.like);
        ImageView unlikeAlert = alertview.findViewById(R.id.unlike);

        bottomNavigationView = requireActivity().findViewById(R.id.navigationButton);


        title.setText(doctorDetails.getName());
        alertview.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alert_dialog_anim_zoom_in));
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                recycleView_doctors.setLayoutFrozen(false);
            }
        });
        bookAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(requireActivity());
                builder1.setCancelable(true);
                builder1.setTitle("Book and appointment");
                builder1.setMessage("Are you sure you want to book an appointment with " + doctorDetails.getName() + "?");
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Appointment_Fragment.empty_appointment.setVisibility(View.GONE);
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                        String getTime = simpleDateFormat.format(calendar.getTime());
                        if (getTime.charAt(0) == '0') {
                            getTime = getTime.substring(1, getTime.length());
                        }
                        AppointmentDetails appointmentDetails = new AppointmentDetails(doctorDetails.getName(), getTime, doctorDetails.getImage());


                        if (bookAlert.isEnabled()) {
                            Appointment_Fragment.AppointmentList.add(appointmentDetails);
                            Appointment_Fragment.listView.setAdapter(Appointment_Fragment.appointment_adapter);
                            Appointment_Fragment.appointment_adapter.notifyDataSetChanged();
                            bookAlert.setEnabled(false);
                            bookAlert.setText("Booked");
                            bookAlert.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_bookmark_24, 0, 0, 0);

                            Toast.makeText(getContext(), "You have successfully booked " + appointmentDetails.getName(), Toast.LENGTH_SHORT).show();
                        } else {
                            AlertDialog.Builder errorBuilder2 = new AlertDialog.Builder(requireActivity());
                            errorBuilder2.setTitle("Error");
                            errorBuilder2.setCancelable(true);
                            errorBuilder2.setMessage("Failed to book this appointment, this doctor has a pending appointment");
                            errorBuilder2.show();
                        }
                    }
                });
                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder1.show();

            }
        });
        sendMessageAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent chatPage = new Intent(requireActivity().getApplicationContext(), Chat_Activity.class);
                chatPage.putExtra("username", doctorDetails.getName());
                chatPage.putExtra("image", doctorDetails.getImage());

                requireActivity().startActivity(chatPage);

                Chat_person_Fragment.Personlist.add(new Chat_Person_Details(doctorDetails.getName(), doctorDetails.getImage(), new Random().nextInt(20) + 1));
                Chat_person_Fragment.chat_persons_adapter.notifyDataSetChanged();

            }
        });

        viewDoctorAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();

                Intent intent1 = new Intent(getContext(), Doctor_info.class);
                intent1.putExtra("name", (doctorDetails.getName()));
                intent1.putExtra("location", (doctorDetails.getLocation()));
                intent1.putExtra("image", doctorDetails.getImage());
                intent1.putExtra("ratings", doctorDetails.getRatings());

                requireActivity().startActivity(intent1);
                //requireActivity().overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });
        reportAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                AlertDialog.Builder builderReport = new AlertDialog.Builder(getContext());
                builderReport.setCancelable(false);
                builderReport.setTitle("Why are you reporting this doctor?");

                final View reportView = LayoutInflater.from(requireActivity()).inflate(R.layout.report_doctor_view, null, false);
                EditText editText = reportView.findViewById(R.id.edit_report);


                builderReport.setView(reportView);
                builderReport.setPositiveButton("Report", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editText.getText().length() == 0) {

                        } else {

                            dialog.cancel();
                            Toast.makeText(requireActivity(), "Thanks for your feedback, This Doctor has successfully been reported.\nThank you.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builderReport.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        bottomNavigationView.setVisibility(View.VISIBLE);
                    }
                });

                builderReport.show();
            }
        });


        if (doctorDetails.getInterest().equals("Like")) {
            likeAlert.setImageResource(R.drawable.ic_baseline_thumb_up_24);
            unlikeAlert.setImageResource(R.drawable.ic_outline_thumb_down_24);
        } else if (doctorDetails.getInterest().equals("Dislike")) {
            likeAlert.setImageResource(R.drawable.ic_outline_thumb_up_24);
            unlikeAlert.setImageResource(R.drawable.ic_baseline_thumb_down_24);
        }


        likeAlert.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)

            @Override

            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(VibrationEffect.EFFECT_HEAVY_CLICK);
                likeAlert.setImageResource(R.drawable.ic_baseline_thumb_up_24);
                doctorDetails.setInterest("Like");
                alertDialog.dismiss();
            }
        });
        unlikeAlert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(VibrationEffect.EFFECT_HEAVY_CLICK);
                unlikeAlert.setImageResource(R.drawable.ic_baseline_thumb_down_24);
                doctorDetails.setInterest("Dislike");
                alertDialog.dismiss();
            }
        });


        Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(VibrationEffect.EFFECT_CLICK);

        alertDialog.setView(alertview);

        alertDialog.show();
    }

    private void makeSearch(String s) {
        detailsHolder.clear();

        switch (FILTER_TYPE) {
            case "":
                for (DoctorDetails doctor_info : newDetailsList) {
                    if (doctor_info.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                        detailsHolder.add(doctor_info);
                    }
                }

                break;
            case "LOCATIONS":
                for (DoctorDetails doctor_info : newDetailsList) {
                    if (doctor_info.getLocation().toLowerCase().contains(s.toString().toLowerCase())) {
                        detailsHolder.add(doctor_info);
                    }
                }
                break;
            case "SPECIALISTS":
                for (DoctorDetails doctor_info : newDetailsList) {
                    if (doctor_info.getSpeciality().toLowerCase().contains(s.toString().toLowerCase())) {
                        detailsHolder.add(doctor_info);
                    }
                }
                break;
            case "AVAILABLE":
                for (DoctorDetails doctor_info : newDetailsList) {
                    if (doctor_info.getAvailability().toLowerCase().contains(s.toString().toLowerCase())) {
                        detailsHolder.add(doctor_info);
                    }
                }
                break;
            case "CHARGES":
                for (DoctorDetails doctor_info : newDetailsList) {
                    if (doctor_info.getAmount().toLowerCase().substring(3).contains(s.toString().toLowerCase())) {
                        detailsHolder.add(doctor_info);
                    }
                }
                break;
            default:
                break;
        }
        doctors_adapter.notifyDataSetChanged();
    }


}



