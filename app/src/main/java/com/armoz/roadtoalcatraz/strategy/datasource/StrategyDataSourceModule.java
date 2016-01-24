package com.armoz.roadtoalcatraz.strategy.datasource;

import com.armoz.roadtoalcatraz.strategy.datasource.impl.StrategyDataSourceFromBBDD;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruben.arana on 23/11/15.
 */
@Module(complete = false, library = true)
public class StrategyDataSourceModule {

    @Provides
    public StrategyDataSource providesStrategyDataSource(StrategyDataSourceFromBBDD strategyDataSourceFromBBDD) {
        return strategyDataSourceFromBBDD;
    }
}
