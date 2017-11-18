package com.android.appliation.intermediate_iak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.GsonBuilder;

/**
 * Created by adesanto on 11/11/17.
 */

public class WeatherDetailActivity extends AppCompatActivity {
    private static final String TAG = "WeatherDetailActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        String weatherJson = getIntent().getStringExtra("weather");
        Log.d(TAG, "onCreate" + weatherJson);

        Weather weather = new GsonBuilder().create().fromJson(weatherJson, Weather.class);
//        Log.d(TAG, "Call Desc" + weather.getWeatherDesc());



    }
}
