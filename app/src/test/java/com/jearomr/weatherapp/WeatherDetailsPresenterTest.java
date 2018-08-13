package com.jearomr.weatherapp;

import com.jearomr.weatherapp.local_repository.entity.Weather;
import com.jearomr.weatherapp.local_repository.entity.WeatherData;
import com.jearomr.weatherapp.weather_details_page.WeatherDetailsContract;
import com.jearomr.weatherapp.weather_details_page.WeatherDetailsPresenter;
import com.jearomr.weatherapp.weather_list_page.data.IWeatherDataRepository;
import com.jearomr.weatherapp.weather_list_page.data.WeatherDataRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class WeatherDetailsPresenterTest {

    @Mock
    private WeatherData weatherData;

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @Mock
    private WeatherDetailsContract.View weatherDetailsView;

    @Captor
    private ArgumentCaptor<IWeatherDataRepository.WeatherDataCallback> weatherDataCallbackArgumentCaptor;

    private WeatherDetailsContract.Presenter weatherDetailsPresenter;

    @Before
    public void setupWeatherDetailsPresenter(){
        MockitoAnnotations.initMocks(this);

        weatherDetailsPresenter = new WeatherDetailsPresenter(weatherData, weatherDetailsView, weatherDataRepository);

        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(new Weather(123, "Rain", "light rain", "01d"));

        when(weatherData.getWeather()).thenReturn(weatherList);
    }

    @Test
    public void testInitialDisplayOfWeatherDetails(){
        weatherDetailsPresenter.loadInitialWeatherData();

        verify(weatherDetailsView).showWeatherDetails(weatherData);
        verify(weatherDetailsView).showWeatherIcon(any(String.class));
    }

    @Test
    public void testRefreshWeatherDataOnSuccess(){
        weatherDetailsPresenter.refreshWeatherData();

        verify(weatherDataRepository).getWeatherDataById(anyString(), anyString(),
                weatherDataCallbackArgumentCaptor.capture());
        weatherDataCallbackArgumentCaptor.getValue().onSuccess(weatherData);

        InOrder inOrder = Mockito.inOrder(weatherDetailsView);
        inOrder.verify(weatherDetailsView, times(1)).showLoadingMessage();
        inOrder.verify(weatherDetailsView, times(1)).showWeatherDetails(weatherData);
        inOrder.verify(weatherDetailsView, times(1)).showWeatherIcon(anyString());
        inOrder.verify(weatherDetailsView, times(1)).showSuccessMessage();
    }

    @Test
    public void testRefreshWeatherDetailsOnFailed(){
        weatherDetailsPresenter.refreshWeatherData();

        verify(weatherDataRepository).getWeatherDataById(anyString(), anyString(),
                weatherDataCallbackArgumentCaptor.capture());
        weatherDataCallbackArgumentCaptor.getValue().onFailed(anyString());

        InOrder inOrder = Mockito.inOrder(weatherDetailsView);
        inOrder.verify(weatherDetailsView, times(1)).showLoadingMessage();
        inOrder.verify(weatherDetailsView, times(1)).showMessage(anyString());

    }
}
