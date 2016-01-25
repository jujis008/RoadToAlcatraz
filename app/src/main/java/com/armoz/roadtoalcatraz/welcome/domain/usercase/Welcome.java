package com.armoz.roadtoalcatraz.welcome.domain.usercase;


import android.content.Context;

import com.armoz.roadtoalcatraz.welcome.domain.callback.WelcomeCallback;

public interface Welcome {

    void createNewGame(Context context, WelcomeCallback welcomeCallback);

}
