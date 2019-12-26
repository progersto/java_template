package com.myapplication.base;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.myapplication.network.CompletableListener;
import com.myapplication.network.SingleListener;
import com.uber.autodispose.ScopeProvider;

import java.util.Objects;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;

import static com.uber.autodispose.AutoDispose.autoDisposable;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity baseActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.baseActivity = (BaseActivity) context;
    }

    public void showLoading() {
        baseActivity.showLoading();
    }

    public void hideLoading() {
        baseActivity.hideLoading();
    }

    public void popBackStack() {
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
    }

    public void clearStack() {
        FragmentActivity activ = getActivity();
        if (activ != null) {
            FragmentManager fm = activ.getSupportFragmentManager();
            for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
                fm.popBackStack();
            }
        }
    }

    public void showFragment(BaseFragment fragment, int idContainer, String tag) {
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .replace(idContainer, fragment)
                .addToBackStack(tag)
                .commit();
    }

    public void addFragment(BaseFragment fragment, int idContainer, String tag) {
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .add(idContainer, fragment)
                .addToBackStack(tag)
                .commit();
    }

    public void replaceFragmentInIdContent(BaseFragment fragment) {
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void replaceFragmentWithSharedElement(
            BaseFragment fragment,
            int dressingRoomContainer,
            String tag,
            View view
    ) {
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(dressingRoomContainer, fragment)
                .addToBackStack(tag)
                .addSharedElement(view, Objects.requireNonNull(ViewCompat.getTransitionName(view)))
                .commit();
    }

    protected <T> void subscribe(Single<T> single, SingleListener<T> listener) {
        showLoading();
        single.observeOn(AndroidSchedulers.mainThread())
                .doFinally(this::hideLoading)
                .as(autoDisposable(ScopeProvider.UNBOUND))
                .subscribe(listener::data, listener::error);
    }

    protected void subscribe(Completable completable, CompletableListener listener) {
        showLoading();
        completable.observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(ScopeProvider.UNBOUND))
                .subscribe(() -> {
                }, listener::error);
    }
}
