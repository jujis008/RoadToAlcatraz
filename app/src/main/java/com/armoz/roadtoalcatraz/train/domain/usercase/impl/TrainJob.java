package com.armoz.roadtoalcatraz.train.domain.usercase.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.events.ReloadEvent;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.train.datasource.TrainDataSource;
import com.armoz.roadtoalcatraz.train.domain.callback.TrainCallback;
import com.armoz.roadtoalcatraz.train.domain.usercase.Train;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.squareup.otto.Bus;

import javax.inject.Inject;

;

public class TrainJob extends UserCaseJob implements Train {

    private static final String TAG = "StrategyJob";
    private TrainCallback callback;
    private TrainDataSource trainDataSource;
    private PlayerModel model;

    @Inject
    Bus bus;

    @Inject
    TrainJob(JobManager jobManager, MainThread mainThread,
             DomainErrorHandler domainErrorHandler, TrainDataSource trainDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.trainDataSource = trainDataSource;
    }

    @Override
    public void obtainPlayer(TrainCallback callback) {
        this.callback = callback;
        jobManager.addJob(this);
    }

    @Override
    public void executeTraining() {
        Log.d(TAG, "Executing job training");
        PlayerModel playerModel = trainDataSource.executeTraining();
        //post on bus
        bus.post(new ReloadEvent());
    }

    @Override
    public void doRun() throws Throwable {
        try {
            model = trainDataSource.obtainPlayer();
            onPlayerLoaded();
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

    private void onPlayerLoaded() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onPlayerLoaded(model);
            }
        });
    }


}
