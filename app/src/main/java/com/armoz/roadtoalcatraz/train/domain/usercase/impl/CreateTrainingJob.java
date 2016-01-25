package com.armoz.roadtoalcatraz.train.domain.usercase.impl;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.events.ReloadEvent;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.player.datasource.PlayerDataSource;
import com.armoz.roadtoalcatraz.train.datasource.TrainDataSource;
import com.armoz.roadtoalcatraz.train.domain.callback.CreateTrainingCallback;
import com.armoz.roadtoalcatraz.train.domain.usercase.CreateTraining;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.squareup.otto.Bus;

import javax.inject.Inject;


public class CreateTrainingJob extends UserCaseJob implements CreateTraining {

    private static final String TAG = "CreateTrainingJob";
    private CreateTrainingCallback callback;
    private TrainDataSource trainDataSource;
    private PlayerDataSource playerDataSource;
    private String skillName;
    private long time;

    @Inject
    Bus bus;

    @Inject
    CreateTrainingJob(JobManager jobManager, MainThread mainThread,
                      DomainErrorHandler domainErrorHandler, TrainDataSource trainDataSource, PlayerDataSource playerDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.trainDataSource = trainDataSource;
        this.playerDataSource = playerDataSource;
    }

    @Override
    public void createTraining(CreateTrainingCallback callback, String skillName, long time) {
        this.callback = callback;
        this.skillName = skillName;
        this.time = time;
        jobManager.addJob(this);
    }

    @Override
    public void doRun() throws Throwable {
        try {
            PlayerModel playerModel = playerDataSource.obtainUserPlayer();
            playerModel = trainDataSource.createTraining(playerModel,skillName, time);
            bus.post(new ReloadEvent());
            onTrainingCreated(playerModel);
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

    private void onTrainingCreated(final PlayerModel playerModel) {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onTrainingCreated(playerModel);
            }
        });
    }

}
