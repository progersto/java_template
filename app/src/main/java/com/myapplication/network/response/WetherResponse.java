package com.myapplication.network.response;

import com.google.gson.annotations.SerializedName;
import com.myapplication.network.models.Coord;
import com.myapplication.network.models.MainInfos;
import com.myapplication.network.models.Sys;
import com.myapplication.network.models.Weather;
import com.myapplication.network.models.Wind;

import java.util.ArrayList;

public class WetherResponse {
    @SerializedName("coord")
    private Coord coord;

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("weather")
    private ArrayList<Weather> weather;

    @SerializedName("main")
    private MainInfos mainInfos;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("name")
    private String strCityName;

    public Coord getCoord() {
        return coord;
    }

    public Sys getSys() {
        return sys;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public MainInfos getMainInfos() {
        return mainInfos;
    }

    public Wind getWind() {
        return wind;
    }

    public String getStrCityName() {
        return strCityName;
    }
}
