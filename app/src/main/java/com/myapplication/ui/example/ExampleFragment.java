package com.myapplication.ui.example;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.myapplication.R;
import com.myapplication.base.BaseFragment;
import com.myapplication.network.CompletableListener;
import com.myapplication.network.SingleListener;
import com.myapplication.network.response.WetherResponse;
import com.myapplication.ui.example2.ExampleActivity2;
import com.uber.autodispose.ScopeProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static com.myapplication.utils.Extension.setMessage;
import static com.uber.autodispose.AutoDispose.autoDisposable;

public class ExampleFragment extends BaseFragment {

    public static String TAG = "ExampleFragment";
    private static int REQUEST_CODE = 12345;

    public static ExampleFragment newInstance() {
        return new ExampleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //определяем viewModel
        ExampleViewModel viewModel = ViewModelProviders.of(baseActivity).get(ExampleViewModel.class);
        Log.d("viewModel", viewModel.toString());
        viewModel.city.observe(this, city -> {
            TextView text = view.findViewById(R.id.text);
            text.setText(city);
        });

        //SingleListener
        subscribe(viewModel.getWeather("kiev"), new SingleListener<WetherResponse>() {
            @Override
            public void data(WetherResponse data) {
                Log.d("", "");
            }

            @Override
            public void error(Throwable error) {
                Log.d("", "");
                Toast.makeText(getContext(), setMessage(error, getContext()), Toast.LENGTH_SHORT).show();
            }
        });

        //CompletableListener
        subscribe(viewModel.getWeather2("kiev"), error -> {
            Log.d("", "");
            Toast.makeText(getContext(), setMessage(error, getContext()), Toast.LENGTH_SHORT).show();
        });

        //нажатие кнопки
        ConstraintLayout layout = view.findViewById(R.id.background);
        //передаеи View, на которое нажимаем
        RxView.clicks(layout).throttleFirst(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(ScopeProvider.UNBOUND)).subscribe((Consumer<Object>) o -> {
            Toast.makeText(getContext(), "Button clicked", Toast.LENGTH_SHORT).show();
            ExampleActivity2.start(getActivity());
        });

        //вместо TextWatcher для EditText используем так
        EditText et = view.findViewById(R.id.edit);
        RxTextView.textChanges(et).as(autoDisposable(ScopeProvider.UNBOUND)).subscribe(charSequence -> {
            //логика с использованием charSequence
            Log.d("textChanges", charSequence.toString());
        });

        //запрос разрешения для камеры или еще чего-то
        String[] permissions = new String[]{Manifest.permission.CAMERA};
        if (hasPermissions(getContext(), permissions[0])) {
            Log.d("", "");
        } else {
            ActivityCompat.requestPermissions(baseActivity, (permissions), REQUEST_CODE);
        }
    }//onViewCreated

    //---------------------------  разрешения  ----------------------------------------------------------
    private Boolean hasPermissions(Context context, String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // разрешение дано
                Log.d("", "");
            } else {
                //разрешения НЕ даны
                Log.d("", "");
            }
        }
    }
    //---------------------------  разрешения  ----------------------------------------------------------
}
