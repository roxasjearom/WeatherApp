package com.jearomr.weatherapp;

import android.app.Application;

import com.jearomr.weatherapp.local_repository.AppDatabase;

public class App extends Application {

    private static AppExecutors appExecutors;
    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appExecutors = new AppExecutors();
        appDatabase = AppDatabase.getDatabase(this);
    }

    public static AppExecutors getAppExecutors(){
        return appExecutors;
    }

    public static AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
