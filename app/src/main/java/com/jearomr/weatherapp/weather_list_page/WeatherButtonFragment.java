package com.jearomr.weatherapp.weather_list_page;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jearomr.weatherapp.R;

import org.greenrobot.eventbus.EventBus;

public class WeatherButtonFragment extends Fragment {

    public static WeatherButtonFragment newInstance(){
        return new WeatherButtonFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_button, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fabRefreshList = view.findViewById(R.id.fabRefreshList);
        fabRefreshList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(getString(R.string.refresh_message));
            }
        });
    }

}
