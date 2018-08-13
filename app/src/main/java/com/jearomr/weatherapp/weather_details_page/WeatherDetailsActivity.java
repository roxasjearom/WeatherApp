package com.jearomr.weatherapp.weather_details_page;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jearomr.weatherapp.GlideApp;
import com.jearomr.weatherapp.R;
import com.jearomr.weatherapp.local_repository.entity.WeatherData;
import com.jearomr.weatherapp.weather_list_page.data.WeatherDataRepository;

public class WeatherDetailsActivity extends AppCompatActivity implements WeatherDetailsContract.View {

    public static final String SELECTED_WEATHER_DATA = "SELECTED_WEATHER_DATA";

    private TextView tvLocationName, tvTemperature, tvWeather,
            tvWind, tvHumidity, tvAtmosphericPressure, tvMinTemp, tvMaxTemp;

    private ImageView ivWeatherIcon;

    private FloatingActionButton fabRefreshDetails;

    private WeatherDetailsContract.Presenter presenter;

    private WeatherData weatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        initializeViews();

        Intent intent = getIntent();
        if(intent != null){
            weatherData = intent.getParcelableExtra(SELECTED_WEATHER_DATA);
        }

        presenter = new WeatherDetailsPresenter(weatherData, this, new WeatherDataRepository());
        presenter.loadInitialWeatherData();
    }

    private void initializeViews(){
        tvLocationName = findViewById(R.id.tvLocationName);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvWeather = findViewById(R.id.tvWeather);
        tvWind = findViewById(R.id.tvWindValue);
        tvHumidity = findViewById(R.id.tvHumidityValue);
        tvAtmosphericPressure = findViewById(R.id.tvAtmosphericPressureValue);
        tvMinTemp = findViewById(R.id.tvMinTempValue);
        tvMaxTemp = findViewById(R.id.tvMaxTempValue);
        ivWeatherIcon = findViewById(R.id.ivWeatherIcon);
        fabRefreshDetails = findViewById(R.id.fabRefreshDetails);
        fabRefreshDetails.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == fabRefreshDetails){
                presenter.refreshWeatherData();
            }
        }
    };

    @Override
    public void showWeatherDetails(WeatherData weatherData) {
        tvLocationName.setText(weatherData.getName());
        tvTemperature.setText(getString(R.string.temperature_format, ""+weatherData.getMain().getTemp()));
        tvWeather.setText(weatherData.getWeather().get(0).getMain());
        tvWind.setText(getString(R.string.wind_format, ""+weatherData.getWind().getSpeed(), ""+weatherData.getWind().getDeg()));
        tvHumidity.setText(getString(R.string.humidity_format, ""+weatherData.getMain().getHumidity()));
        tvAtmosphericPressure.setText(getString(R.string.pressure_format, ""+weatherData.getMain().getPressure()));
        tvMinTemp.setText(getString(R.string.temperature_format, ""+weatherData.getMain().getTempMin()));
        tvMaxTemp.setText(getString(R.string.temperature_format, ""+weatherData.getMain().getTempMax()));
    }

    @Override
    public void showWeatherIcon(String iconCode) {
        GlideApp.with(this)
                .load(getString(R.string.weather_icon_url_format, iconCode))
                .into(ivWeatherIcon);
    }

    @Override
    public void showLoadingMessage() {
        Toast.makeText(this, getString(R.string.refresh_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(this, R.string.weather_details_updated, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelRequests();
    }
}
