package com.myapplication.data;

import com.google.gson.Gson;
import com.myapplication.network.ApiService;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.myapplication.utils.Constants.BASE_URL;
import static com.myapplication.utils.Constants.KEY_TOKEN;
import static com.myapplication.utils.Constants.VALUE_TOKEN;

public class NetworkManager {
    private ApiService apiService;

    public NetworkManager() {
        apiService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder()
                       .addInterceptor(chain ->
                               chain.proceed(
                                       chain.request().newBuilder().url(
                                               chain.request().url().newBuilder().addQueryParameter(KEY_TOKEN, VALUE_TOKEN).build()
                                       ).build()
                               )
                       )
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build()
                .create(ApiService.class);
    }

    public ApiService getApi() {
        return apiService;
    }
}
