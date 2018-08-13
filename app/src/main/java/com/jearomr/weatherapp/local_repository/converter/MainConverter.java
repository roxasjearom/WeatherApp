package com.jearomr.weatherapp.local_repository.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jearomr.weatherapp.local_repository.entity.Main;

import java.lang.reflect.Type;

public class MainConverter {

    @TypeConverter
    public static String fromMain(Main main){
        if(main == null){
            return (null);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<Main>(){}.getType();

        return gson.toJson(main, type);
    }

    @TypeConverter
    public static Main toMain(String strMain){
        if(strMain == null){
            return (null);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<Main>(){}.getType();

        return gson.fromJson(strMain, type);
    }
}
