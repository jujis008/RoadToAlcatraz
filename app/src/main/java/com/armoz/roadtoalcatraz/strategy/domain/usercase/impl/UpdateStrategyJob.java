package com.armoz.roadtoalcatraz.strategy.domain.usercase.impl;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.strategy.datasource.StrategyDataSource;
import com.armoz.roadtoalcatraz.strategy.domain.callback.StrategyCallback;
import com.armoz.roadtoalcatraz.strategy.domain.callback.UpdateStrategyCallback;
import com.armoz.roadtoalcatraz.strategy.domain.usercase.UpdateStrategy;
import com.armoz.roadtoalcatraz.train.datasource.TrainDataSource;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.squareup.otto.Bus;

import javax.inject.Inject;

;

public class UpdateStrategyJob extends UserCaseJob implements UpdateStrategy {

    private static final String TAG = "UpdateStrategyJob";
    private StrategyDataSource strategyDataSource;
    private StrategyModel strategyModel;
    private UpdateStrategyCallback callback;

    @Inject
    Bus bus;

    @Inject
    UpdateStrategyJob(JobManager jobManager, MainThread mainThread,
                      DomainErrorHandler domainErrorHandler, StrategyDataSource strategyDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.strategyDataSource = strategyDataSource;
    }


    @Override
    public void updateStrategy(StrategyModel strategyModel, UpdateStrategyCallback callback) {
        this.strategyModel = strategyModel;
        this.callback = callback;
        jobManager.addJob(this);
    }

    @Override
    public void doRun() throws Throwable {
        try {
            strategyDataSource.updateStrategy(strategyModel);
            onStrategyUpdated();
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

    private void onStrategyUpdated() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onStrategyUpdated();
            }
        });
    }

}
