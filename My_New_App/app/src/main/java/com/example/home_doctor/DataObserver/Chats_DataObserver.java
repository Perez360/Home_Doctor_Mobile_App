package com.example.home_doctor.DataObserver;


import android.content.Context;
import android.database.DataSetObserver;
import android.widget.ListView;

public class Chats_DataObserver extends DataSetObserver {
    Context context;
    ListView listView;

    public Chats_DataObserver(Context context, ListView listView) {
        this.listView = listView;
        this.context = context;
    }

    @Override
    public void onChanged() {
        super.onChanged();
    }
}
