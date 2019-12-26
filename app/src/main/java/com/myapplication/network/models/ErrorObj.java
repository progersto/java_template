package com.myapplication.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ErrorObj {
    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    @SerializedName("parameters")
    Map<String, String> parameters;
}
