package com.jearomr.weatherapp;

import com.jearomr.weatherapp.local_repository.AppDatabase;

public class BaseDatabase {

    protected AppExecutors appExecutors;
    protected AppDatabase appDatabase;

    public BaseDatabase() {
        appExecutors = App.getAppExecutors();
        appDatabase = App.getAppDatabase();
    }
}
