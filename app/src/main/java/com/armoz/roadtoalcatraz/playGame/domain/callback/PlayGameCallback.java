package com.armoz.roadtoalcatraz.playGame.domain.callback;


import com.armoz.roadtoalcatraz.base.domain.model.GameModel;

/**
 *
 */
public interface PlayGameCallback {

    void onGameFinished(GameModel game);

    void onError();
}
