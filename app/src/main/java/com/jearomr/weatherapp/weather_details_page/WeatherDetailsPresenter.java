package com.jearomr.weatherapp.weather_details_page;

import com.jearomr.weatherapp.local_repository.entity.WeatherData;
import com.jearomr.weatherapp.weather_list_page.data.IWeatherDataRepository;

import static com.jearomr.weatherapp.Constants.API_KEY;

public class WeatherDetailsPresenter implements WeatherDetailsContract.Presenter {

    private WeatherData weatherData;
    private WeatherDetailsContract.View view;
    private IWeatherDataRepository weatherDataRepository;

    public WeatherDetailsPresenter(WeatherData weatherData, WeatherDetailsContract.View view, IWeatherDataRepository weatherDataRepository) {
        this.weatherData = weatherData;
        this.view = view;
        this.weatherDataRepository = weatherDataRepository;
    }

    @Override
    public void loadInitialWeatherData() {
        view.showWeatherDetails(weatherData);
        if(weatherData.getWeather().size()>0){
            view.showWeatherIcon(weatherData.getWeather().get(0).getIcon());
        }
    }

    @Override
    public void refreshWeatherData() {
        view.showLoadingMessage();
        weatherDataRepository.getWeatherDataById("" + weatherData.getId(), API_KEY,
                new IWeatherDataRepository.WeatherDataCallback() {
            @Override
            public void onSuccess(WeatherData weatherData) {
                view.showWeatherDetails(weatherData);
                if(weatherData.getWeather().size()>0){
                    view.showWeatherIcon(weatherData.getWeather().get(0).getIcon());
                }
                view.showSuccessMessage();
            }

            @Override
            public void onFailed(String message) {
                view.showMessage(message);
            }
        });
    }

    @Override
    public void cancelRequests() {
        weatherDataRepository.cancelRequests();
    }
}
