package com.example.home_doctor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class Home_Scroll extends ScrollView {
    boolean mDisablScrolling=false;
    public Home_Scroll(Context context) {
        super(context);
    }

    public Home_Scroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Home_Scroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Home_Scroll(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public  void setScrollEnabled(boolean status){
        mDisablScrolling=!status;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mDisablScrolling){
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
