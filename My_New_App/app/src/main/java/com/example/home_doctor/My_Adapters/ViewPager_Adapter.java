package com.example.home_doctor.My_Adapters;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPager_Adapter extends FragmentPagerAdapter {
    List<Fragment> list;


    public ViewPager_Adapter(Activity activity, FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.list = list;


    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);

    }

    @Override
    public int getCount() {
        return list.size();
    }


}
