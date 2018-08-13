package com.jearomr.weatherapp.local_repository.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jearomr.weatherapp.local_repository.entity.Wind;

import java.lang.reflect.Type;

public class WindConverter {

    @TypeConverter
    public static String fromWind(Wind wind){
        if(wind == null){
            return (null);
        }

        Type type = new TypeToken<Wind>(){}.getType();
        Gson gson = new Gson();
        return gson.toJson(wind, type);
    }

    @TypeConverter
    public static Wind toWind(String strWind){
        if(strWind == null){
            return (null);
        }

        Type type = new TypeToken<Wind>(){}.getType();
        Gson gson = new Gson();
        return gson.fromJson(strWind, type);
    }
}
