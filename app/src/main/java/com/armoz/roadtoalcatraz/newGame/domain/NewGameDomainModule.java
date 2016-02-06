package com.armoz.roadtoalcatraz.newGame.domain;

import com.armoz.roadtoalcatraz.newGame.domain.usercase.NewGame;
import com.armoz.roadtoalcatraz.newGame.domain.usercase.impl.NewGameJob;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class NewGameDomainModule {

    @Provides
    public NewGame provideWelcome(NewGameJob welcomeJob) {
        return welcomeJob;
    }

}
