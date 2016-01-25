package com.armoz.roadtoalcatraz.strategy.domain.usercase.impl;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.player.datasource.PlayerDataSource;
import com.armoz.roadtoalcatraz.strategy.datasource.StrategyDataSource;
import com.armoz.roadtoalcatraz.strategy.domain.callback.StrategyCallback;
import com.armoz.roadtoalcatraz.strategy.domain.usercase.Strategy;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.squareup.otto.Bus;

import javax.inject.Inject;

;

public class StrategyJob extends UserCaseJob implements Strategy {

    private static final String TAG = "StrategyJob";
    private StrategyCallback callback;
    private StrategyDataSource strategyDataSource;
    private PlayerDataSource playerDataSource;
    private StrategyModel strategyModel;

    @Inject
    Bus bus;

    @Inject
    StrategyJob(JobManager jobManager, MainThread mainThread,
                DomainErrorHandler domainErrorHandler, StrategyDataSource strategyDataSource, PlayerDataSource playerDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.strategyDataSource = strategyDataSource;
        this.playerDataSource = playerDataSource;
    }


    @Override
    public void obtainStrategy(StrategyCallback strategyCallback) {
        this.callback = strategyCallback;
        jobManager.addJob(this);
    }

    @Override
    public void doRun() throws Throwable {
        try {
            PlayerModel playerModel = playerDataSource.obtainUserPlayer();
            strategyModel = strategyDataSource.obtainStrategy(playerModel.getStrategy());
            onStrategyLoaded();
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

    private void onStrategyLoaded() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onStrategyLoaded(strategyModel);
            }
        });
    }



}
