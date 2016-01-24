package com.armoz.roadtoalcatraz.strategy.view;

/**
 * Created by ruben.arana on 23/11/15.
 */

import com.armoz.roadtoalcatraz.strategy.domain.usercase.Strategy;
import com.armoz.roadtoalcatraz.strategy.domain.usercase.UpdateStrategy;
import com.armoz.roadtoalcatraz.strategy.view.activity.StrategyActivity;
import com.armoz.roadtoalcatraz.strategy.view.controller.StrategyController;
import com.armoz.roadtoalcatraz.strategy.view.fragment.StrategyDefensiveFragment;
import com.armoz.roadtoalcatraz.strategy.view.fragment.StrategyGeneralFragment;
import com.armoz.roadtoalcatraz.strategy.view.fragment.StrategyOffensiveFragment;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module(injects = {StrategyActivity.class, StrategyGeneralFragment.class, StrategyOffensiveFragment.class, StrategyDefensiveFragment.class}, complete = false,
        library = true)
public class StrategyViewModule {

    @Provides
    public StrategyController provideStrategyController(Strategy strategyJob, UpdateStrategy updateStrategyJob) {
        return new StrategyController(strategyJob, updateStrategyJob);
    }

}
