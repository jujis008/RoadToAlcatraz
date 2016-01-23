package com.armoz.roadtoalcatraz.playGame.domain.usercase;


import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.playGame.domain.callback.PlayGameCallback;

public interface PlayGame {

    int MIN_VICTORY_POINTS = 21;
    int MIN_VICTORY_DIFF = 2;

    void playGame(PlayGameCallback playGameCallback, GameModel game);

}
