package com.armoz.roadtoalcatraz.welcome.domain;

import com.armoz.roadtoalcatraz.welcome.domain.usercase.Welcome;
import com.armoz.roadtoalcatraz.welcome.domain.usercase.impl.WelcomeJob;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class WelcomeDomainModule {

    @Provides
    public Welcome provideWelcome(WelcomeJob welcomeJob) {
        return welcomeJob;
    }

}
