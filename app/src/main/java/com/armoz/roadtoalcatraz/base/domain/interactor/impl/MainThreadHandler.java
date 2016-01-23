package com.armoz.roadtoalcatraz.base.domain.interactor.impl;

import android.os.Handler;
import android.os.Looper;

import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class MainThreadHandler implements MainThread {
    private Handler handler;

    @Inject
    MainThreadHandler() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
