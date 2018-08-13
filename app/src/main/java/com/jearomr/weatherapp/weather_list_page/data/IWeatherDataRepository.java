package com.jearomr.weatherapp.weather_list_page.data;

import android.arch.lifecycle.LiveData;

import com.jearomr.weatherapp.local_repository.entity.WeatherData;

import java.util.List;

public interface IWeatherDataRepository {

    interface WeatherDataListCallback {
        void onSuccess();
        void onFailed(String message);
    }

    /**
     * Used on Weather List page which uses MVVM pattern. Returns a live data for observing.
     *
     * @param cityIds string value for API city params
     * @param apiKey API key from api.openweather.org
     * @param callback callback for handling the request result
     * @return Live Data response from API
     */
    LiveData<List<WeatherData>> getWeatherDataList(String cityIds, String apiKey, WeatherDataListCallback callback);

    interface WeatherDataCallback{
        void onSuccess(WeatherData weatherData);
        void onFailed(String message);
    }

    /**
     * Used on Weather Details page which uses MVP pattern. The result is being returned through the callback.
     * @param cityId city ID params for the API request
     * @param apiKey API key from api.openweather.org
     * @param callback callback for handling the request result
     */
    void getWeatherDataById(String cityId, String apiKey, WeatherDataCallback callback);

    void cancelRequests();

}
