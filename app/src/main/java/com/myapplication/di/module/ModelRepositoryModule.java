package com.myapplication.di.module;

import com.myapplication.repository.ModelRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelRepositoryModule {

    @Singleton
    @Provides
    ModelRepository provideModelRepository() {
        return new ModelRepository();
    }
}
