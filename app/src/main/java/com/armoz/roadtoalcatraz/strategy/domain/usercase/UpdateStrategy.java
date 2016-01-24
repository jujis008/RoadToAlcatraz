package com.armoz.roadtoalcatraz.strategy.domain.usercase;


import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.strategy.domain.callback.UpdateStrategyCallback;

public interface UpdateStrategy {

    void updateStrategy(StrategyModel strategyModel, UpdateStrategyCallback callback);
}
