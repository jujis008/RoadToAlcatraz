package com.armoz.roadtoalcatraz.strategy.domain.usercase;


import com.armoz.roadtoalcatraz.strategy.domain.callback.StrategyCallback;

public interface Strategy {

    void obtainStrategy(StrategyCallback strategyCallback);
}
