package com.wangkeke.daynightdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.widget.TextView;

public class ModeActivity extends AppCompatActivity {

    private TextView txtModeType;
    int modeType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
        txtModeType = (TextView) findViewById(R.id.txtModeType);
        modeType = AppCompatDelegate.getDefaultNightMode();

        if (modeType == AppCompatDelegate.MODE_NIGHT_AUTO) {
            txtModeType.setText("Default Mode: Auto");
        } else if (modeType == AppCompatDelegate.MODE_NIGHT_YES) {
            txtModeType.setText("Default Mode: Night");
        } else if (modeType == AppCompatDelegate.MODE_NIGHT_NO) {
            txtModeType.setText("Default Mode: Day");
        }
    }
}