package com.armoz.roadtoalcatraz.train.view;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.armoz.roadtoalcatraz.base.application.BaseApplication;
import com.armoz.roadtoalcatraz.train.view.controller.TrainController;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 14/1/16.
 */
public class TrainJobService extends JobService {

    @Inject
    TrainController controller;

    private static final String TAG = "TrainJobService";
    JobParameters params;

    @Override
    public boolean onStartJob(JobParameters params) {
        ((BaseApplication) getApplication()).inject(this);
        this.params = params;
        controller.executeTraining();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

}
