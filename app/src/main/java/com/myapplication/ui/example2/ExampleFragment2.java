package com.myapplication.ui.example2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import com.jakewharton.rxbinding3.view.RxView;
import com.myapplication.R;
import com.myapplication.base.BaseFragment;
import com.uber.autodispose.ScopeProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static com.uber.autodispose.AutoDispose.autoDisposable;

public class ExampleFragment2 extends BaseFragment {

    public static String TAG = "ExampleFragment2";

    public static ExampleFragment2 newInstance() {
        return new ExampleFragment2();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ExampleViewModel2 viewModel = ViewModelProviders.of(baseActivity).get(ExampleViewModel2.class);
        Log.d("viewModel", viewModel.toString());
        viewModel.cityFromRepo.observe(this, city -> {
            TextView text = view.findViewById(R.id.text);
            text.setText(city + " ffffff");
        });

        //нажатие кнопки
        ConstraintLayout layout = view.findViewById(R.id.background);
        RxView.clicks(layout).throttleFirst(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(ScopeProvider.UNBOUND)).subscribe((Consumer<Object>) o -> {
            Toast.makeText(getContext(), "Button clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
