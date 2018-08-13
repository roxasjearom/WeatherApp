package com.jearomr.weatherapp.weather_list_page;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jearomr.weatherapp.R;
import com.jearomr.weatherapp.local_repository.entity.WeatherData;
import com.jearomr.weatherapp.weather_details_page.WeatherDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import static com.jearomr.weatherapp.weather_details_page.WeatherDetailsActivity.SELECTED_WEATHER_DATA;

/**
 * This module uses MVVM (Model-View-ViewModel) pattern for displaying the weather data list.
 *
 */
public class WeatherListFragment extends Fragment {

    private WeatherListAdapter adapter;
    private WeatherListViewModel viewModel;

    public static WeatherListFragment newInstance(){
        return new WeatherListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvWeatherList = view.findViewById(R.id.rvWeatherList);

        adapter = new WeatherListAdapter(weatherListListener);
        rvWeatherList.setAdapter(adapter);
        rvWeatherList.setLayoutManager(new LinearLayoutManager(getActivity()));

        //initialize the view model and start observing
        viewModel = WeatherListActivity.getWeatherListViewModel(getActivity());
        viewModel.getWeatherDataList().observe(this, new Observer<List<WeatherData>>() {
            @Override
            public void onChanged(@Nullable List<WeatherData> weatherDataList) {
                if(weatherDataList != null && weatherDataList.size()>0){
                    adapter.setWeatherDataList(weatherDataList);
                }
            }
        });

        viewModel.getShowToastMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                showToastMessage(message);
            }
        });
    }


    @Subscribe
    public void refreshWeatherList(String value){ //method for Event bus
        viewModel.getWeatherDataList();
        showToastMessage(value);
    }

    private void showToastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private WeatherListListener weatherListListener = new WeatherListListener() {
        @Override
        public void onItemSelected(WeatherData weatherData) {
            showWeatherDetailsPage(weatherData);
        }
    };

    private void showWeatherDetailsPage(WeatherData weatherData){
        Intent intent = new Intent(getActivity(), WeatherDetailsActivity.class);
        intent.putExtra(SELECTED_WEATHER_DATA, weatherData);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(viewModel != null){
            viewModel.cancelRequests();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
