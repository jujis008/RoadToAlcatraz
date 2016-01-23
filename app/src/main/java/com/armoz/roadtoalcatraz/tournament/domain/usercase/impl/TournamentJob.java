package com.armoz.roadtoalcatraz.tournament.domain.usercase.impl;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.tournament.datasource.TournamentDataSource;
import com.armoz.roadtoalcatraz.tournament.domain.callback.TournamentCallback;
import com.armoz.roadtoalcatraz.tournament.domain.usercase.Tournament;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

import javax.inject.Inject;

;

public class TournamentJob extends UserCaseJob implements Tournament {

    private TournamentCallback callback;
    private TournamentDataSource tournamentDataSource;
    private TournamentModel model;
    private long tournamentID;


    @Inject
    TournamentJob(JobManager jobManager, MainThread mainThread,
                  DomainErrorHandler domainErrorHandler, TournamentDataSource tournamentDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.tournamentDataSource = tournamentDataSource;
    }

    @Override
    public void doRun() throws Throwable {
        try {
            model = tournamentDataSource.obtainTournamentInfo(tournamentID);
            onTournamentLoaded();
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

    private void onTournamentLoaded() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onTournamentLoaded(model);
            }
        });
    }


    @Override
    public void obtainTournamentInfo(TournamentCallback tournamentCallback, long tournamentID) {
        this.callback = tournamentCallback;
        this.tournamentID = tournamentID;
        jobManager.addJob(this);
    }
}
