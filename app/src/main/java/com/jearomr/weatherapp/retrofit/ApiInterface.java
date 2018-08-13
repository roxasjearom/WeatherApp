package com.jearomr.weatherapp.retrofit;

import com.jearomr.weatherapp.local_repository.entity.WeatherData;
import com.jearomr.weatherapp.retrofit.api_response.WeatherListResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/data/2.5/group")
    Single<Response<WeatherListResponse>> getWeatherList(@Query("id") String cityIds, @Query("appid") String apiKey);

    @GET("/data/2.5/weather")
    Single<Response<WeatherData>> getWeatherById(@Query("id") String cityId, @Query("appid") String apiKey);

}
