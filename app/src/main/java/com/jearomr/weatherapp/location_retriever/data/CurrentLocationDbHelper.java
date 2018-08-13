package com.jearomr.weatherapp.location_retriever.data;

import android.arch.lifecycle.LiveData;

import com.jearomr.weatherapp.BaseDatabase;
import com.jearomr.weatherapp.local_repository.dao.CurrentLocationDao;
import com.jearomr.weatherapp.local_repository.entity.CurrentLocation;

public class CurrentLocationDbHelper extends BaseDatabase {

    private CurrentLocationDao currentLocationDao;

    public CurrentLocationDbHelper() {
        super();
        currentLocationDao = appDatabase.currentLocationDao();
    }

    public void saveCurrentLocation(final CurrentLocation currentLocation){
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                currentLocationDao.insertLocation(currentLocation);
            }
        });
    }

    public LiveData<CurrentLocation> getCurrentLocation(){
        return currentLocationDao.getCurrentLocation();
    }
}
