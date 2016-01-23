package com.armoz.roadtoalcatraz.welcome.domain.usercase.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.welcome.datasource.WelcomeDataSource;
import com.armoz.roadtoalcatraz.welcome.domain.callback.WelcomeCallback;
import com.armoz.roadtoalcatraz.welcome.domain.usercase.Welcome;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

import javax.inject.Inject;

;

public class WelcomeJob extends UserCaseJob implements Welcome {

    private static final String TAG = "WelcomeJob";
    private WelcomeCallback callback;
    private WelcomeDataSource welcomeDataSource;


    @Inject
    WelcomeJob(JobManager jobManager, MainThread mainThread,
               DomainErrorHandler domainErrorHandler, WelcomeDataSource welcomeDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.welcomeDataSource = welcomeDataSource;
    }


    @Override
    public void doRun() throws Throwable {
        try {
            Log.d(TAG, "Job creating game" );
            welcomeDataSource.createNewGame();
            Log.d(TAG, "Game created");
            onNewGameCreated();
        }
        catch (Exception e){
            notifyError();
        }
    }

    private void notifyError() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError();
                }
            }
        });
    }

    private void onNewGameCreated() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onNewGameCreated();
            }
        });
    }


    @Override
    public void createNewGame(WelcomeCallback welcomeCallback) {
        this.callback = welcomeCallback;
        jobManager.addJob(this);
    }
}
