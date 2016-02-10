package com.armoz.roadtoalcatraz.newGame.domain.callback;


import android.content.Context;

/**
 *
 */
public interface NewGameCallback {

    void onNewGameCreated(Context context);

    void onError();
}
