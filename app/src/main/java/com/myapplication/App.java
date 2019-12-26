package com.myapplication;

import android.app.Application;
import com.myapplication.di.component.AppComponent;
import com.myapplication.di.component.DaggerAppComponent;
import com.myapplication.di.module.ModelRepositoryModule;
import com.myapplication.di.module.NetworkModule;
import com.myapplication.di.module.StorageModule;

public class App extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .storageModule(new StorageModule(getApplicationContext()))
                .modelRepositoryModule(new ModelRepositoryModule())
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}