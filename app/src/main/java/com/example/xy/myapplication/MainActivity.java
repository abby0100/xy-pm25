package com.example.xy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.xy.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    private final String LOG_TAG = "MainActivity";
    public static final String TIME = "com.example.xy.time";

    private TextView version;

    private TextView city;
    private TextView city_now;
    private TextView date;
    private TextView date_today;
    private TextView weather;
    private TextView weather_today;
    private TextView pm25;
    private TextView pm25_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        city = (TextView) findViewById(R.id.city);
        city_now = (TextView) findViewById(R.id.city_now);

        date = (TextView) findViewById(R.id.date);
        date_today = (TextView) findViewById(R.id.date_today);

        weather = (TextView) findViewById(R.id.weather);
        weather_today = (TextView) findViewById(R.id.weather_today);

        pm25 = (TextView) findViewById(R.id.pm25);
        pm25_now = (TextView) findViewById(R.id.pm25_now);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        String currentDate = Utils.getCurrentDate();
        Intent intent = new Intent();
        intent.putExtra(TIME,currentDate );
        setResult(RESULT_OK, intent);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
