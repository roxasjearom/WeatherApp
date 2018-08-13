package com.jearomr.weatherapp.weather_list_page.data;

import android.arch.lifecycle.LiveData;

import com.jearomr.weatherapp.BaseDatabase;
import com.jearomr.weatherapp.local_repository.dao.WeatherDataDao;
import com.jearomr.weatherapp.local_repository.entity.WeatherData;

import java.util.List;

public class WeatherDataDbHelper extends BaseDatabase {

    private WeatherDataDao weatherDataDao;

    public WeatherDataDbHelper() {
        super();
        weatherDataDao = appDatabase.weatherDataDao();
    }

    public LiveData<List<WeatherData>> getWeatherDataList(){
        return weatherDataDao.getWeatherDataList();
    }

    public void saveWeatherDataList(final List<WeatherData> weatherDataList){
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                weatherDataDao.insertWeatherDataList(weatherDataList);
            }
        });
    }

    public void saveWeatherData(final WeatherData weatherData){
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                weatherDataDao.insertWeatherData(weatherData);
            }
        });
    }

    public void deleteAll(){
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                weatherDataDao.deleteAll();
            }
        });
    }

    public LiveData<WeatherData> getWeatherDataById(String id){
        return weatherDataDao.getWeatherDataById(id);
    }
}
