package com.jearomr.weatherapp.local_repository.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "WEATHER_DATA")
public class WeatherData implements Parcelable{

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("coord")
    @Expose
    private Coordinates coordinates;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather;

    @SerializedName("main")
    @Expose
    private Main main;

    @SerializedName("visibility")
    @Expose
    private int visibility;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("dt")
    @Expose
    private int dt;

    @SerializedName("name")
    @Expose
    private String name;

    public WeatherData(int id, Coordinates coordinates, List<Weather> weather, Main main, int visibility, Wind wind, int dt, String name) {
        this.id = id;
        this.coordinates = coordinates;
        this.weather = weather;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.dt = dt;
        this.name = name;
    }


    protected WeatherData(Parcel in) {
        id = in.readInt();
        coordinates = in.readParcelable(Coordinates.class.getClassLoader());
        weather = in.createTypedArrayList(Weather.CREATOR);
        main = in.readParcelable(Main.class.getClassLoader());
        visibility = in.readInt();
        wind = in.readParcelable(Wind.class.getClassLoader());
        dt = in.readInt();
        name = in.readString();
    }

    public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel in) {
            return new WeatherData(in);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeParcelable(coordinates, i);
        parcel.writeTypedList(weather);
        parcel.writeParcelable(main, i);
        parcel.writeInt(visibility);
        parcel.writeParcelable(wind, i);
        parcel.writeInt(dt);
        parcel.writeString(name);
    }
}
