package com.jearomr.weatherapp.local_repository.converter;

import android.arch.persistence.room.TypeConverter;

import com.jearomr.weatherapp.local_repository.entity.Coordinates;

import java.util.Locale;

public class CoordinatesConverter {

    @TypeConverter
    public static Coordinates toCoordinates(String strCoordinates){
        if(strCoordinates == null){
            return null;
        }

        String[] contents = strCoordinates.split(",");
        return new Coordinates(Double.parseDouble(contents[0]), Double.parseDouble(contents[1]));
    }

    @TypeConverter
    public static String fromCoordinates(Coordinates coordinates){
        if(coordinates == null){
            return null;
        }
        return (String.format(Locale.getDefault(),
                "%f,%f", coordinates.getLongitude(), coordinates.getLatitude()));
    }

}
