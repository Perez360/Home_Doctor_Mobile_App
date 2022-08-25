package com.example.home_doctor.Bottom_Navigation_Pages.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.home_doctor.Chat_Feature.Chat_Activity;
import com.example.home_doctor.My_Adapters.Chat_Persons_Adapter;
import com.example.home_doctor.R;
import com.example.home_doctor.Storages.Chat_Person_Details;
import com.example.home_doctor.Storages.DoctorDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Chat_person_Fragment extends Fragment {
    private static final String TAG = "DAMMYLIST";
    public static List<Chat_Person_Details> Personlist;
    public static Toolbar chat_person_tool;
    public static ListView listView;
    public static String holdTypedTextForFuture;
    public static Chat_Persons_Adapter chat_persons_adapter;
    public ActionBar actionBar;
    public TextView chat_title;
    public BottomNavigationView bottomNavigationView;
    public FloatingActionButton floatingActionButton;
    List<DoctorDetails> dammyList;
    private EditText search;

    public static Chat_person_Fragment newInstance(ArrayList<DoctorDetails> dammyList) {

        Chat_person_Fragment chat_person_fragment = new Chat_person_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(TAG, dammyList);
        chat_person_fragment.setArguments(args);
        return chat_person_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            dammyList = new ArrayList<>();
            Personlist = new ArrayList<>();
            dammyList.addAll((ArrayList<DoctorDetails>) bundle.getSerializable(TAG));
            Personlist.addAll((ArrayList<Chat_Person_Details>) bundle.getSerializable(TAG));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View newView;
        newView = inflater.inflate(R.layout.chat_person_activity, container, false);
        chat_person_tool = newView.findViewById(R.id.chat_person_menu);
        search = requireActivity().findViewById(R.id.search);


        return newView;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initComp(requireActivity());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initComp(Activity activity) {


        chat_person_tool = activity.findViewById(R.id.chat_person_menu);
        floatingActionButton = activity.findViewById(R.id.floating_Button);

        bottomNavigationView = requireActivity().findViewById(R.id.navigationButton);

        setListView(activity);
        chat_title = requireActivity().findViewById(R.id.chat_title);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationView.setSelectedItemId(R.id.id_my_doc_nav);
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setListView(Activity activity) {
        listView = activity.findViewById(R.id.chat_person_listView);


        Personlist = new ArrayList<Chat_Person_Details>();

        Personlist.add(new Chat_Person_Details("Dr. Perez", R.drawable.img1, 3));
        Personlist.add(new Chat_Person_Details("Dr. Ella", R.drawable.img11, 1));
        Personlist.add(new Chat_Person_Details("Dr. Mavis", R.drawable.img21, 2));
        Personlist.add(new Chat_Person_Details("Dr. Isaac", R.drawable.img31, 10));
        Personlist.add(new Chat_Person_Details("Dr. Cynthia", R.drawable.img41, 6));
        Personlist.add(new Chat_Person_Details("Dr. Delali", R.drawable.img51, 2));
        Personlist.add(new Chat_Person_Details("Dr. Prince", R.drawable.img61, 5));
        Personlist.add(new Chat_Person_Details("Dr. Luisa", R.drawable.img12, 5));


        chat_persons_adapter = new Chat_Persons_Adapter(getContext(), Personlist);
        listView.setAdapter(chat_persons_adapter);
        //chat_persons_adapter.registerDataSetObserver(new Chats_DataObserver(getContext(), listView));

        //listView.addHeaderView(getLayoutInflater().inflate(R.layout.chat_person_list_header, null, false), null, false);
        //listView.addFooterView(getLayoutInflater().inflate(R.layout.chat_person_list_footer, null, false), null, false);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chat_Person_Details chatPersonDetails = (Chat_Person_Details) view.getTag();
                Intent intent = new Intent(activity.getApplicationContext(), Chat_Activity.class);
                intent.putExtra("username", chatPersonDetails.getName());
                intent.putExtra("type", "chat_person");
                intent.putExtra("image", chatPersonDetails.getImg());
                startActivity(intent);

                Chat_Activity.clear_Badge = activity.findViewById(R.id.chat_badge);
            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void hideInputMethods() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(InputMethodManager.class);
        inputMethodManager.hideSoftInputFromWindow(requireActivity().getWindow().getDecorView().getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
    }



}