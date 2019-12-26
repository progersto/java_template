package com.myapplication.di.module;

import com.myapplication.data.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    NetworkManager provideNetworkUtils() {
        return new NetworkManager();
    }
}
