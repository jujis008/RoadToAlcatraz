package com.armoz.roadtoalcatraz.scheduledGame.view.controller;

import com.armoz.roadtoalcatraz.scheduledGame.domain.callback.ScheduledGameCallback;
import com.armoz.roadtoalcatraz.scheduledGame.domain.usercase.ScheduledGame;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class ScheduledGameController {

    private static final String TAG = "ScheduledGameController";

    private ScheduledGame scheduledGameJob;


    @Inject
    public ScheduledGameController(ScheduledGame scheduledGameJob) {
        this.scheduledGameJob = scheduledGameJob;
    }

    private ScheduledGameCallback callback = new ScheduledGameCallback() {
        @Override
        public void onScheduledGamesPlayed() {

            // Notification!!!
        }

        @Override
        public void onError() {

        }
    };

    public void executeScheduledGames() {
        scheduledGameJob.playScheduledGames(callback);
    }

}
