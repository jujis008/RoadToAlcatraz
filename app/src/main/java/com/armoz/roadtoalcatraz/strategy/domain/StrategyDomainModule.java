package com.armoz.roadtoalcatraz.strategy.domain;

import com.armoz.roadtoalcatraz.strategy.domain.usercase.Strategy;
import com.armoz.roadtoalcatraz.strategy.domain.usercase.UpdateStrategy;
import com.armoz.roadtoalcatraz.strategy.domain.usercase.impl.StrategyJob;
import com.armoz.roadtoalcatraz.strategy.domain.usercase.impl.UpdateStrategyJob;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class StrategyDomainModule {

    @Provides
    public Strategy provideStrategy(StrategyJob strategyJob) {
        return strategyJob;
    }

    @Provides
    public UpdateStrategy provideUpdateStrategy(UpdateStrategyJob updateStrategyJob) {
        return updateStrategyJob;
    }
}
