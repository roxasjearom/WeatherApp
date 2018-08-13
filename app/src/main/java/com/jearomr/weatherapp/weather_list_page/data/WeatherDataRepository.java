package com.jearomr.weatherapp.weather_list_page.data;

import android.arch.lifecycle.LiveData;

import com.jearomr.weatherapp.local_repository.entity.WeatherData;
import com.jearomr.weatherapp.retrofit.ApiClient;
import com.jearomr.weatherapp.retrofit.ApiInterface;
import com.jearomr.weatherapp.retrofit.api_response.WeatherListResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class WeatherDataRepository implements IWeatherDataRepository {

    private ApiInterface apiInterface;
    private WeatherDataDbHelper weatherDataDbHelper;
    private CompositeDisposable compositeDisposable;
    //for simplicity, just a generic error message
    private static final String ERROR_MESSAGE = "An error occurred while fetching data. Showing last retrieved data.";

    public WeatherDataRepository() {
        apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        weatherDataDbHelper = new WeatherDataDbHelper();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public LiveData<List<WeatherData>> getWeatherDataList(String cityIds, String apiKey, final WeatherDataListCallback callback) {
        Disposable disposable = apiInterface
                .getWeatherList(cityIds, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<WeatherListResponse>>() {
                    @Override
                    public void accept(Response<WeatherListResponse> weatherListResponse) {
                        if (weatherListResponse.isSuccessful()) {
                            callback.onSuccess();
                            weatherDataDbHelper.saveWeatherDataList(weatherListResponse.body().getList());
                        } else {
                            callback.onFailed(ERROR_MESSAGE);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        callback.onFailed(ERROR_MESSAGE);
                    }
                });

        compositeDisposable.add(disposable);

        return weatherDataDbHelper.getWeatherDataList();
    }

    @Override
    public void getWeatherDataById(String id, String apiKey, final WeatherDataCallback callback){
        Disposable disposable = apiInterface.getWeatherById(id, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<WeatherData>>() {
                    @Override
                    public void accept(Response<WeatherData> weatherDataResponse) {
                        if (weatherDataResponse.isSuccessful()) {
                            callback.onSuccess(weatherDataResponse.body());
                        } else {
                            callback.onFailed(ERROR_MESSAGE);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        callback.onFailed(ERROR_MESSAGE);
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void cancelRequests() {
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
    }
}
