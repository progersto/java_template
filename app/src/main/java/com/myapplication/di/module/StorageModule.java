package com.myapplication.di.module;

import android.content.Context;

import com.myapplication.data.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    private Context context;

    public StorageModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    PreferenceManager providePreferenceManager(Context context) {
        return new PreferenceManager(context);
    }

}
