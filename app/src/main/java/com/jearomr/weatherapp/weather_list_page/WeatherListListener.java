package com.jearomr.weatherapp.weather_list_page;

import com.jearomr.weatherapp.local_repository.entity.WeatherData;

public interface WeatherListListener {

    void onItemSelected(WeatherData weatherData);

}
