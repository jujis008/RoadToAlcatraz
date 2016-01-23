package com.armoz.roadtoalcatraz.welcome.view;

/**
 * Created by ruben.arana on 23/11/15.
 */

import com.armoz.roadtoalcatraz.welcome.domain.usercase.Welcome;
import com.armoz.roadtoalcatraz.welcome.view.activity.WelcomeActivity;
import com.armoz.roadtoalcatraz.welcome.view.controller.WelcomeController;
import com.armoz.roadtoalcatraz.welcome.view.fragment.WelcomeFragment;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module(injects = {WelcomeActivity.class, WelcomeFragment.class}, complete = false,
        library = true)
public class WelcomeViewModule {

    @Provides
    public WelcomeController provideHelloWorldController(Welcome helloWorldJob) {
        return new WelcomeController(helloWorldJob);
    }
}
