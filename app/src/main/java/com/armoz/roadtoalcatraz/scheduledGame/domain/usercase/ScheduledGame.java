package com.armoz.roadtoalcatraz.scheduledGame.domain.usercase;


import com.armoz.roadtoalcatraz.scheduledGame.domain.callback.ScheduledGameCallback;

public interface ScheduledGame {

    void playScheduledGames(ScheduledGameCallback callback);
}
