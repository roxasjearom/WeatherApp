package com.jearomr.weatherapp.local_repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.jearomr.weatherapp.local_repository.converter.CoordinatesConverter;
import com.jearomr.weatherapp.local_repository.converter.MainConverter;
import com.jearomr.weatherapp.local_repository.converter.WeatherConverter;
import com.jearomr.weatherapp.local_repository.converter.WindConverter;
import com.jearomr.weatherapp.local_repository.dao.CurrentLocationDao;
import com.jearomr.weatherapp.local_repository.dao.WeatherDataDao;
import com.jearomr.weatherapp.local_repository.entity.CurrentLocation;
import com.jearomr.weatherapp.local_repository.entity.WeatherData;

@Database(entities = {WeatherData.class, CurrentLocation.class}, version = 1, exportSchema = false)
@TypeConverters({CoordinatesConverter.class, WeatherConverter.class, MainConverter.class, WindConverter.class})
public abstract class AppDatabase extends RoomDatabase{

    public abstract WeatherDataDao weatherDataDao();

    public abstract CurrentLocationDao currentLocationDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "weather_db")
                        .build();
            }
        }
        return INSTANCE;
    }

}
