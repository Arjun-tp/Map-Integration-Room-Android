package com.example.test2map.EntityClass;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "location")
public class LocationModel {

    // Location Schema

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int key;

    @ColumnInfo(name="title")
    private String title;

    @ColumnInfo(name="subTitle")
    private String subTitle;

    @ColumnInfo(name="lat")
    private String lat;

    @ColumnInfo(name="lng")
    private String lng;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
