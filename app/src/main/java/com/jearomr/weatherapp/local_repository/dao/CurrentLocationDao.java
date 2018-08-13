package com.jearomr.weatherapp.local_repository.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.jearomr.weatherapp.local_repository.entity.CurrentLocation;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CurrentLocationDao {

    @Insert(onConflict = REPLACE)
    void insertLocation(CurrentLocation location);

    @Query("SELECT * FROM CURRENT_LOCATION WHERE id = 1")
    LiveData<CurrentLocation> getCurrentLocation();

}
