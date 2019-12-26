package com.myapplication.network.models;

import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    private Double longitude;

    @SerializedName("lat")
    private Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}
