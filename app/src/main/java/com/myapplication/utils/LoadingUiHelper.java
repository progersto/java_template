package com.myapplication.utils;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class LoadingUiHelper {

    public ProgressDialogFragment showProgress() {
        return new ProgressDialogFragment();
    }

    public static class ProgressDialogFragment extends DialogFragment {

        public static String TAG = "ProgressDialogFragment";

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setCancelable(false);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            FrameLayout frameLayout = new FrameLayout(getContext());
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            frameLayout.setLayoutParams(lp);

            ProgressBar progressBar = new ProgressBar(getContext());
            lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            int margin = DimenUtils.dpToPx(16f);
            lp.setMargins(margin, margin, margin, margin);
            progressBar.setLayoutParams(lp);

            frameLayout.addView(progressBar);

            return frameLayout;
        }

        @Override
        public void onStart() {
            super.onStart();
            Window window = getDialog().getWindow();
            window.setBackgroundDrawable(null);
        }
    }
}
