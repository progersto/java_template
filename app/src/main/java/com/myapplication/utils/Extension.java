package com.myapplication.utils;

import android.content.Context;

import com.myapplication.R;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Objects;

public class Extension {

    public static String setMessage(Throwable throwable, Context con) {
        String text = "error";

        if (throwable instanceof UnknownHostException) {
            //нет интернета
            text = con.getResources().getString(R.string.no_internet);
        } else if (throwable instanceof HttpException) {
            //ошибка 4xx
            HttpException ex = (HttpException) throwable;
            if (ex.response() != null) {
                text = Objects.requireNonNull(ex.response()).message();
            }
        } else {
            //неизвестная ошибка
            text = throwable.getMessage();
        }

        return text;
    }
}
