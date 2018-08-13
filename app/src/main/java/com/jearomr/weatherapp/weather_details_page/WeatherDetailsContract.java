package com.jearomr.weatherapp.weather_details_page;

import com.jearomr.weatherapp.local_repository.entity.WeatherData;

public interface WeatherDetailsContract {

    interface View{
        void showWeatherDetails(WeatherData weatherData);

        void showWeatherIcon(String iconCode);

        void showLoadingMessage();

        void showSuccessMessage();

        void showMessage(String message);
    }

    interface Presenter{
        void loadInitialWeatherData();

        void refreshWeatherData();

        void cancelRequests();
    }

}
