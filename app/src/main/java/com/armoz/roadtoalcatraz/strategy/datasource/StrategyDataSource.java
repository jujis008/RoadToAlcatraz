package com.armoz.roadtoalcatraz.strategy.datasource;

import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;

/**
 * Created by ruben.arana on 23/11/15.
 */
public interface StrategyDataSource {

    StrategyModel obtainStrategy(long strategyId);

    void updateStrategy(StrategyModel strategyModel);
}
