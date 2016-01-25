package com.armoz.roadtoalcatraz.calendar.domain.usercase.impl;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.calendar.domain.callback.CalendarCallback;
import com.armoz.roadtoalcatraz.calendar.domain.model.CalendarModel;
import com.armoz.roadtoalcatraz.calendar.domain.usercase.Calendar;
import com.armoz.roadtoalcatraz.tournament.datasource.TournamentDataSource;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

import javax.inject.Inject;

;

public class CalendarJob extends UserCaseJob implements Calendar {

    private CalendarCallback callback;
    private TournamentDataSource tournamentDataSource;
    private CalendarModel model;


    @Inject
    CalendarJob(JobManager jobManager, MainThread mainThread,
                DomainErrorHandler domainErrorHandler, TournamentDataSource tournamentDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.tournamentDataSource = tournamentDataSource;
    }

    @Override
    public void obtainTournaments(CalendarCallback callback) {
        this.callback = callback;
        jobManager.addJob(this);

    }

    @Override
    public void doRun() throws Throwable {
        try {
            model = new CalendarModel();
            model.setTournaments(tournamentDataSource.obtainAllTournaments());
            onTournamentsLoaded();
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

    private void onTournamentsLoaded() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onTournamentsLoaded(model);
            }
        });
    }


}
