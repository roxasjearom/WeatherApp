package com.jearomr.weatherapp.local_repository.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jearomr.weatherapp.local_repository.entity.Weather;

import java.lang.reflect.Type;
import java.util.List;

public class WeatherConverter {

    @TypeConverter
    public static String fromWeatherList(List<Weather> weather){
        if(weather == null){
            return (null);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>(){}.getType();

        return gson.toJson(weather, type);
    }

    @TypeConverter
    public static List<Weather> toWeather(String strWeather){
        if(strWeather == null){
            return (null);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>(){}.getType();
        return gson.fromJson(strWeather, type);
    }

}
