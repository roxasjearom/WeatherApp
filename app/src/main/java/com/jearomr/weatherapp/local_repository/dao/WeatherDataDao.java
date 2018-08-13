package com.jearomr.weatherapp.local_repository.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.jearomr.weatherapp.local_repository.entity.WeatherData;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface WeatherDataDao {

    @Insert(onConflict = REPLACE)
    void insertWeatherDataList(List<WeatherData> weatherDataList);

    @Insert(onConflict = REPLACE)
    void insertWeatherData(WeatherData weatherData);

    @Query("DELETE FROM WEATHER_DATA")
    void deleteAll();

    @Query("SELECT * FROM WEATHER_DATA")
    LiveData<List<WeatherData>> getWeatherDataList();

    @Query("SELECT * FROM WEATHER_DATA WHERE id = :id")
    LiveData<WeatherData> getWeatherDataById(String id);


}
