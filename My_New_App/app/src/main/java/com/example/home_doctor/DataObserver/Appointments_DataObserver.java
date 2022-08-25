package com.example.home_doctor.DataObserver;


import android.database.DataSetObserver;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Appointments_DataObserver extends DataSetObserver {

    ListView listView;

    public Appointments_DataObserver(ListView listView) {
        this.listView = listView;
    }

    @Override
    public void onChanged() {
        super.onChanged();
       ArrayAdapter arrayAdapter= (ArrayAdapter) listView.getAdapter();
    }
}
