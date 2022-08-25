package com.example.home_doctor.DataObserver;

import android.database.DataSetObserver;
import android.widget.ListView;

public class Doctors_Observer extends DataSetObserver {
    ListView listView;

    public Doctors_Observer(ListView listView) {
        this.listView = listView;
    }

    @Override
    public void onChanged() {
        super.onChanged();
    }
}
