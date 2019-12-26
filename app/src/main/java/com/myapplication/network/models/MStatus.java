package com.myapplication.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MStatus {
    @SerializedName("status")
    Boolean status  = false;

    @SerializedName("message")
    String message;

    @SerializedName("errors")
    List<ErrorObj> errors = new ArrayList<>();
}
