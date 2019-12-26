package com.myapplication.repository;

import androidx.lifecycle.MutableLiveData;

import com.myapplication.utils.SingleLiveEvent;

public class ModelRepository {
    public SingleLiveEvent<String> city = new SingleLiveEvent<>();
    public MutableLiveData<String> town = new MutableLiveData<>();
}
