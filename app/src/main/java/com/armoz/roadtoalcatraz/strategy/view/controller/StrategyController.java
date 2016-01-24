package com.armoz.roadtoalcatraz.strategy.view.controller;

import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.strategy.domain.callback.StrategyCallback;
import com.armoz.roadtoalcatraz.strategy.domain.callback.UpdateStrategyCallback;
import com.armoz.roadtoalcatraz.strategy.domain.usercase.Strategy;
import com.armoz.roadtoalcatraz.strategy.domain.usercase.UpdateStrategy;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class StrategyController {

    private static final String TAG = "StrategyController";

    private View view;

    public void setView(View view) {
        this.view = view;
    }

    private Strategy strategyJob;
    private UpdateStrategy updateStrategyJob;


    private StrategyModel strategyModel = new StrategyModel();


    @Inject
    public StrategyController(Strategy strategyJob, UpdateStrategy updateStrategyJob) {
        this.strategyJob = strategyJob;
        this.updateStrategyJob = updateStrategyJob;
    }

    private StrategyCallback callback = new StrategyCallback() {
        @Override
        public void onStrategyLoaded(StrategyModel strategy) {
            strategyModel = strategy;
            view.onStrategyLoaded();
        }

        @Override
        public void onError() {
            view.onError();
        }

    };

    private UpdateStrategyCallback updateStrategyCallback = new UpdateStrategyCallback() {
        @Override
        public void onStrategyUpdated() {
            view.onStrategyLoaded();
        }

        @Override
        public void onError() {
            view.onError();
        }

    };


    public void obtainStrategy() {
        strategyJob.obtainStrategy(callback);
    }

    public StrategyModel getStrategyModel() {
        return strategyModel;
    }

    public void updateStrategy(StrategyModel strategyModel) {
        updateStrategyJob.updateStrategy(strategyModel, updateStrategyCallback);
    }

    public interface View {

        public void onError();

        public void onStrategyLoaded();

    }

}
