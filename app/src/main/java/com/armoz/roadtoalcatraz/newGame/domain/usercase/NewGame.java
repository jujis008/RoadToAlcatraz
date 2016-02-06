package com.armoz.roadtoalcatraz.newGame.domain.usercase;


import android.content.Context;

import com.armoz.roadtoalcatraz.newGame.domain.callback.NewGameCallback;

public interface NewGame {

    void createNewGame(Context context, NewGameCallback newGameCallback);

}
