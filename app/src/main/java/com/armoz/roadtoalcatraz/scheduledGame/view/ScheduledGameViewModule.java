package com.armoz.roadtoalcatraz.scheduledGame.view;

/**
 * Created by ruben.arana on 23/11/15.
 */

import com.armoz.roadtoalcatraz.scheduledGame.domain.usercase.ScheduledGame;
import com.armoz.roadtoalcatraz.scheduledGame.view.controller.ScheduledGameController;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module(injects = {ScheduledGameJobService.class}, complete = false,
        library = true)
public class ScheduledGameViewModule {

    @Provides
    public ScheduledGameController provideScheduledGameController(ScheduledGame scheduledGameJob) {
        return new ScheduledGameController(scheduledGameJob);
    }

}
