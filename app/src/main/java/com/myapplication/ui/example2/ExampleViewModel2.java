package com.myapplication.ui.example2;

import androidx.lifecycle.ViewModel;

import com.myapplication.App;
import com.myapplication.repository.ModelRepository;
import com.myapplication.utils.SingleLiveEvent;

public class ExampleViewModel2 extends ViewModel {
    private ModelRepository repo = App.getComponent().getRepository();

    public SingleLiveEvent<String> cityFromRepo = repo.city;
}
