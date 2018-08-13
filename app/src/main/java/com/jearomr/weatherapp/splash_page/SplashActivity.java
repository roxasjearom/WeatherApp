package com.jearomr.weatherapp.splash_page;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jearomr.weatherapp.R;
import com.jearomr.weatherapp.weather_list_page.WeatherListActivity;

public class SplashActivity extends AppCompatActivity {

    private Handler delayHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, WeatherListActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        delayHandler.removeCallbacksAndMessages(null);
    }
}
