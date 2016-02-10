package com.armoz.roadtoalcatraz.scheduledGame.domain.usercase.impl;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.scheduledGame.domain.callback.ScheduledGameCallback;
import com.armoz.roadtoalcatraz.scheduledGame.domain.usercase.ScheduledGame;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

import javax.inject.Inject;


public class ScheduledGameJob extends UserCaseJob implements ScheduledGame {

    private static final String TAG = "ScheduledGameJob";
    private ScheduledGameCallback callback;

    @Inject
    ScheduledGameJob(JobManager jobManager, MainThread mainThread,
                     DomainErrorHandler domainErrorHandler
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
    }

    @Override
    public void doRun() throws Throwable {
        try {

            //Should play games here until current date

            onScheduledGamesPlayed();
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

    private void onScheduledGamesPlayed() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onScheduledGamesPlayed();
            }
        });
    }

    @Override
    public void playScheduledGames(ScheduledGameCallback callback) {
        this.callback = callback;
        jobManager.addJob(this);
    }
}
