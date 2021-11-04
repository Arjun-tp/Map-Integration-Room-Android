package com.example.test2map.DaoClass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.test2map.EntityClass.LocationModel;

import java.util.List;

@Dao
public interface Daoclass {

    // Queries

    @Insert
    void insertAllData(LocationModel model);

    @Query("select * from location")
    List<LocationModel> getAllData();

    @Query("delete from location where `key`= :id")
    void deleteData(int id);

    @Query("update location set title= :title, subTitle= :subTitle, lat= :lat, lng= :lng where `key`= :key")
    void updateData(String title, String subTitle, String lat, String lng, int key);
}
