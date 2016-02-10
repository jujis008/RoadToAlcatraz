package com.armoz.roadtoalcatraz.scheduledGame.view;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.armoz.roadtoalcatraz.base.application.BaseApplication;
import com.armoz.roadtoalcatraz.scheduledGame.view.controller.ScheduledGameController;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 14/1/16.
 */
public class ScheduledGameJobService extends JobService {

    @Inject
    ScheduledGameController controller;

    private static final String TAG = "TrainJobService";
    JobParameters params;

    @Override
    public boolean onStartJob(JobParameters params) {
        ((BaseApplication) getApplication()).inject(this);
        this.params = params;
        controller.executeScheduledGames();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

}
