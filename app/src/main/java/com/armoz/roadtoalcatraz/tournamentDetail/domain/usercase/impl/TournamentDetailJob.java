package com.armoz.roadtoalcatraz.tournamentDetail.domain.usercase.impl;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.tournament.datasource.TournamentDataSource;
import com.armoz.roadtoalcatraz.tournamentDetail.domain.callback.TournamentDetailCallback;
import com.armoz.roadtoalcatraz.tournamentDetail.domain.usercase.TournamentDetail;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

import javax.inject.Inject;

;

public class TournamentDetailJob extends UserCaseJob implements TournamentDetail {

    private TournamentDetailCallback callback;
    private TournamentDataSource tournamentDataSource;
    private TournamentModel model;
    private int tournamentID;


    @Inject
    TournamentDetailJob(JobManager jobManager, MainThread mainThread,
                        DomainErrorHandler domainErrorHandler, TournamentDataSource tournamentDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.tournamentDataSource = tournamentDataSource;
    }

    @Override
    public void doRun() throws Throwable {
        try {
            model = tournamentDataSource.obtainTournament(tournamentID);
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
                callback.onTournamentDetailLoaded(model);
            }
        });
    }


    @Override
    public void obtainTournament(TournamentDetailCallback tournamentDetailCallback, int tournamentID) {
        this.callback = tournamentDetailCallback;
        this.tournamentID = tournamentID;
        jobManager.addJob(this);
    }
}
