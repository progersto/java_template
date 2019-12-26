package com.myapplication.ui.example2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.myapplication.R;
import com.myapplication.base.BaseActivity;

import java.util.List;

public class ExampleActivity2 extends BaseActivity {

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ExampleActivity2.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_for_activity);

//        //viewModel
        final ExampleViewModel2 viewModel = ViewModelProviders.of(this).get(ExampleViewModel2.class);
        Log.d("viewModel", viewModel.toString());
//        viewModel.getCity().observe(this, users -> {
//            TextView text = findViewById(R.id.text);
//            text.setText(users.get(0).getName() + " " + users.get(0).getAdress());
//        });

        addFragment(ExampleFragment2.newInstance(), R.id.container_for_fragments, ExampleFragment2.TAG);
    }

    @Override
    public void onBackPressed() {
        List<Fragment> listFragments = getSupportFragmentManager().getFragments();
        Fragment fragment = listFragments.get(listFragments.size() - 1);
        if(fragment instanceof ExampleFragment2){
            finish();
        }else {
            super.onBackPressed();
        }
    }
}
