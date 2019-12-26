package com.myapplication.network.models;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    private Double speed;

    public Double getSpeed() {
        return speed;
    }
}
