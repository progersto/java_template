package com.myapplication.network;


public interface SingleListener<T> {
    void data(T data);
    void error(Throwable error);
}
