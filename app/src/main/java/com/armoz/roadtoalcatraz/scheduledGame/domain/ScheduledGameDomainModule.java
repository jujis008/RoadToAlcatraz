package com.armoz.roadtoalcatraz.scheduledGame.domain;

import com.armoz.roadtoalcatraz.scheduledGame.domain.usercase.ScheduledGame;
import com.armoz.roadtoalcatraz.scheduledGame.domain.usercase.impl.ScheduledGameJob;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class ScheduledGameDomainModule {

    @Provides
    public ScheduledGame provideScheduledGame(ScheduledGameJob ScheduledGameJob) {
        return ScheduledGameJob;
    }
}
