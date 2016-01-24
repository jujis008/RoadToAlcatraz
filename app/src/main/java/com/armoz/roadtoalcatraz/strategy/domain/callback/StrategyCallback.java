package com.armoz.roadtoalcatraz.strategy.domain.callback;


import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;

/**
 *
 */
public interface StrategyCallback {

    void onStrategyLoaded( StrategyModel strategyModel);

    void onError();
}
