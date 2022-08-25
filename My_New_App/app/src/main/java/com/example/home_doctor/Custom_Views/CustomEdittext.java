package com.example.home_doctor.Custom_Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class CustomEdittext extends EditText {
    Context context;

    public CustomEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
                clearFocus();
                if (getText().toString().length() > 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
