package com.myapplication.base;

import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.myapplication.R;
import com.myapplication.utils.LoadingUiHelper;
import java.util.Objects;

public abstract class BaseActivity extends AppCompatActivity {
    private BaseFragment currentFragment;
    private LoadingUiHelper.ProgressDialogFragment progressDialog;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (currentFragment != null){
           currentFragment.onActivityResult(requestCode, resultCode, data);
       }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (currentFragment != null){
            currentFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void addFragment(BaseFragment fragment, int idContainer, String tag){
        currentFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .add(idContainer, fragment)
                .addToBackStack(tag)
                .commit();
    }

    protected void replaceFragment(BaseFragment fragment) {
        currentFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    protected void replaceFragmentWithoutBackstack(BaseFragment fragment) {
        currentFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    protected void replaceFragmentInIdContent(BaseFragment fragment) {
        currentFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

    protected void showFragment(BaseFragment fragment, int idContainer, String tag){
        currentFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(idContainer, fragment)
                .addToBackStack(tag)
                .commit();
    }

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null){
            imm.hideSoftInputFromWindow(Objects.requireNonNull(getWindow().getCurrentFocus()).getWindowToken(), 0);
        }
    }

    protected void showLoading(){
        if (progressDialog == null){
            progressDialog = new LoadingUiHelper().showProgress();
            progressDialog.show(getSupportFragmentManager(), LoadingUiHelper.ProgressDialogFragment.TAG);
        }
    }

    protected void  hideLoading(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
