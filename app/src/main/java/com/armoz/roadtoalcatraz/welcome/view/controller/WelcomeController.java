package com.armoz.roadtoalcatraz.welcome.view.controller;

import com.armoz.roadtoalcatraz.welcome.domain.callback.WelcomeCallback;
import com.armoz.roadtoalcatraz.welcome.domain.usercase.Welcome;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class WelcomeController {

    private View view;

    public void setView(View view) {
        this.view = view;
    }

    private Welcome welcomeJob;
    
    @Inject
    public WelcomeController(Welcome welcomeJob) {
        this.welcomeJob = welcomeJob;
    }

    private WelcomeCallback welcomeCallback = new WelcomeCallback() {
        @Override
        public void onNewGameCreated() {
            view.onNewGameCreated();
        }

        @Override
        public void onError() {
            view.onError();
        }

    };

    public void createNewGame() {
        welcomeJob.createNewGame(welcomeCallback);
    }
    
    public interface View {

        public void onNewGameCreated();

        public void onError();

    }

}
