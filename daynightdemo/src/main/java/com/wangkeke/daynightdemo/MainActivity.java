package com.wangkeke.daynightdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAutoMode;
    private Button btnNightMode;
    private Button btnDayMode;
    private Button btnDayZhihu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Default Night Mode as Auto Mode
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        btnAutoMode = (Button) findViewById(R.id.btnAutoMode);
        btnNightMode = (Button) findViewById(R.id.btnNightMode);
        btnDayMode = (Button) findViewById(R.id.btnDayMode);
        btnDayZhihu = (Button) findViewById(R.id.btn_zhihu);


        btnAutoMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Auto Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                Intent i = new Intent(MainActivity.this, ModeActivity.class);
                startActivity(i);
            }
        });

        btnNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Default Night Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Intent i = new Intent(MainActivity.this, ModeActivity.class);
                startActivity(i);
            }
        });

        btnDayMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Default Day Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Intent i = new Intent(MainActivity.this, ModeActivity.class);
                startActivity(i);
            }
        });

        btnDayZhihu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ZhiHuActivity.class);
                startActivity(i);
            }
        });
    }
}