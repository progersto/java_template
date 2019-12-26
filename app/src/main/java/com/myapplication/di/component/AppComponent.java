package com.myapplication.di.component;

import com.myapplication.di.module.ModelRepositoryModule;
import com.myapplication.di.module.NetworkModule;
import com.myapplication.di.module.StorageModule;
import com.myapplication.data.PreferenceManager;
import com.myapplication.data.NetworkManager;
import com.myapplication.repository.ModelRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {StorageModule.class, NetworkModule.class, ModelRepositoryModule.class})
public interface AppComponent {
    NetworkManager getNetworkUtils();
    PreferenceManager getPreference();
    ModelRepository getRepository();
}
