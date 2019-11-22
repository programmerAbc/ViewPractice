package com.practice.viewpractice;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

public class LayoutMonitor {
    Callback callback;
    boolean once;
    View view;
    ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;

    private LayoutMonitor(Builder builder) {
        callback = builder.callback;
        once = builder.once;
        view = builder.view;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public interface Callback {
        void onLayoutFinished();
    }

    public static final class Builder {
        private Callback callback;
        private boolean once;
        private View view;

        private Builder() {
            once = true;
        }

        public Builder callback(Callback val) {
            callback = val;
            return this;
        }

        public Builder once(boolean val) {
            once = val;
            return this;
        }

        public Builder view(View val) {
            view = val;
            return this;
        }

        public LayoutMonitor build() {
            return new LayoutMonitor(this);
        }

    }

    public LayoutMonitor install() {
        globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (callback != null) {
                    callback.onLayoutFinished();
                }
                if (once) {
                    uninstall();
                }
            }
        };
        view.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
        return this;
    }

    public void uninstall() {
        try {
            if (Build.VERSION.SDK_INT < 16) {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(globalLayoutListener);
            } else {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(globalLayoutListener);
            }
        } catch (Exception e) {

        }
    }
}
