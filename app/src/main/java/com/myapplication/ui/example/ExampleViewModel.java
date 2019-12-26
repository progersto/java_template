package com.myapplication.ui.example;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapplication.App;
import com.myapplication.data.PreferenceManager;
import com.myapplication.network.ApiService;
import com.myapplication.network.models.User;
import com.myapplication.network.response.WetherResponse;
import com.myapplication.repository.ModelRepository;
import com.myapplication.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;

public class ExampleViewModel extends ViewModel {
    //определяем классы Singleton
    private ApiService api = App.getComponent().getNetworkUtils().getApi();
    private PreferenceManager pref = App.getComponent().getPreference();
    private ModelRepository repo = App.getComponent().getRepository();

    private MutableLiveData<List<User>> users = new MutableLiveData<>();
    public SingleLiveEvent<String> city = new SingleLiveEvent<>();
    public SingleLiveEvent<String> cityFromRepo = repo.city;

    public Single<WetherResponse> getWeather(String cityText) {
        pref.saveToken("332b9fa2a7fc1bef0f5c73f980d5f57b");
        String token = pref.getToken();

        return api.getWeather(cityText)
                .flatMap(weatherResponse -> {
                    // сразу передаем во view
                    city.postValue(weatherResponse.getStrCityName());
                    // передаем в репозиторий и там храним значение, а оттуда передаем в view.
                    repo.city.postValue(weatherResponse.getStrCityName());
                    return Single.just(weatherResponse);
                });
    }

    public Completable getWeather2(String cityText) {
        return api.getWeather2(cityText);
    }
}
