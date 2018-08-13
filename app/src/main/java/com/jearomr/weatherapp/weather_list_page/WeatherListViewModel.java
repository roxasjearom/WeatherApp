package com.jearomr.weatherapp.weather_list_page;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.jearomr.weatherapp.StringMessageEvent;
import com.jearomr.weatherapp.local_repository.entity.CurrentLocation;
import com.jearomr.weatherapp.local_repository.entity.WeatherData;
import com.jearomr.weatherapp.location_retriever.data.CurrentLocationDbHelper;
import com.jearomr.weatherapp.weather_list_page.data.IWeatherDataRepository;
import com.jearomr.weatherapp.weather_list_page.data.WeatherDataRepository;

import java.util.List;

import static com.jearomr.weatherapp.Constants.API_KEY;
import static com.jearomr.weatherapp.Constants.CITY_IDS;

public class WeatherListViewModel extends AndroidViewModel {

    private IWeatherDataRepository weatherDataRepository;
    private StringMessageEvent showToastMessage = new StringMessageEvent();
    private CurrentLocationDbHelper currentLocationDbHelper;

    public WeatherListViewModel(@NonNull Application application) {
        super(application);

        weatherDataRepository = new WeatherDataRepository();
        currentLocationDbHelper = new CurrentLocationDbHelper();
    }

    public LiveData<List<WeatherData>> getWeatherDataList(){
        return weatherDataRepository.getWeatherDataList(CITY_IDS, API_KEY,
                new IWeatherDataRepository.WeatherDataListCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed(String message) {
                showToastMessage.postValue(message);
            }
        });
    }

    public LiveData<CurrentLocation> getCurrentLocation(){
        return currentLocationDbHelper.getCurrentLocation();
    }

    public StringMessageEvent getShowToastMessage(){
        return showToastMessage;
    }

    public void cancelRequests(){
        weatherDataRepository.cancelRequests();
    }
}
