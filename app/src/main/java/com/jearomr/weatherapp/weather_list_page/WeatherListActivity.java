package com.jearomr.weatherapp.weather_list_page;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jearomr.weatherapp.R;
import com.jearomr.weatherapp.local_repository.entity.CurrentLocation;
import com.jearomr.weatherapp.location_retriever.LocationRetrieverService;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class WeatherListActivity extends AppCompatActivity {

    public static final int GPS_PERMISSIONS_REQUEST = 1111;

    private TextView tvCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        tvCurrentLocation = findViewById(R.id.tvCurrentLocation);

        WeatherListFragment weatherListFragment = WeatherListFragment.newInstance();
        WeatherButtonFragment weatherButtonFragment = WeatherButtonFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, weatherListFragment);
        fragmentTransaction.replace(R.id.buttonContainer, weatherButtonFragment);
        fragmentTransaction.commit();

        if(hasLocationPermission(this)){
            startLocationService();
            startLocationObserver();
        } else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    GPS_PERMISSIONS_REQUEST);
        }
    }

    private void startLocationObserver(){
        WeatherListViewModel viewModel = getWeatherListViewModel(this);
        viewModel.getCurrentLocation().observe(this, new Observer<CurrentLocation>() {
            @Override
            public void onChanged(@Nullable CurrentLocation location) {
                if(location != null){
                    tvCurrentLocation.setText(getString(R.string.current_location_format, ""+location.getLatitude(), ""+location.getLongitude()));
                }
            }
        });
    }

    public static WeatherListViewModel getWeatherListViewModel(FragmentActivity activity){
        return ViewModelProviders.of(activity).get(WeatherListViewModel.class);
    }

    private void startLocationService(){
        Intent locationServiceIntent = new Intent(this, LocationRetrieverService.class);
        startService(locationServiceIntent);
    }

    private static boolean hasLocationPermission(Context context){
        int permissionToAccessLocation = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionToAccessLocation == PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case GPS_PERMISSIONS_REQUEST:{
                if(grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED){
                    startLocationService();
                    startLocationObserver();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(R.string.permission_not_granted);
                    builder.setPositiveButton(R.string.label_ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }

        }
    }
}
