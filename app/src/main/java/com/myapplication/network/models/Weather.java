package com.myapplication.network.models;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("description")
    private String strDesc;

    @SerializedName("icon")
    private String strIconName;

    public String getStrDesc() {
        return strDesc;
    }

    public void setStrDesc(String strDesc) {
        this.strDesc = strDesc;
    }

    public String getStrIconName() {
        return strIconName;
    }

    public void setStrIconName(String strIconName) {
        this.strIconName = strIconName;
    }
}
