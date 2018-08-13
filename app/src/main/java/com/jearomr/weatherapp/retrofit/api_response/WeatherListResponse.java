package com.jearomr.weatherapp.retrofit.api_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jearomr.weatherapp.local_repository.entity.WeatherData;

import java.util.List;

public class WeatherListResponse {

    @SerializedName("cnt")
    @Expose
    private int cnt;

    @SerializedName("list")
    @Expose
    private List<WeatherData> weatherDataList;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherData> getList() {
        return weatherDataList;
    }

    public void setList(java.util.List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }

}
