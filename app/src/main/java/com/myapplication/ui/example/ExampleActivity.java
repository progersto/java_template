package com.myapplication.ui.example;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.myapplication.R;
import com.myapplication.base.BaseActivity;

import java.util.List;

public class ExampleActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_for_activity);

//        //viewModel
        final ExampleViewModel viewModel = ViewModelProviders.of(this).get(ExampleViewModel.class);
        Log.d("viewModel", viewModel.toString());
//        viewModel.getCity().observe(this, users -> {
//            TextView text = findViewById(R.id.text);
//            text.setText(users.get(0).getName() + " " + users.get(0).getAdress());
//        });

        addFragment(ExampleFragment.newInstance(), R.id.container_for_fragments, ExampleFragment.TAG);
    }

    @Override
    public void onBackPressed() {
        List<Fragment> listFragments = getSupportFragmentManager().getFragments();
        Fragment fragment = listFragments.get(listFragments.size() - 1);
        if(fragment instanceof ExampleFragment){
            finish();
        }else {
            super.onBackPressed();
        }
    }
}
