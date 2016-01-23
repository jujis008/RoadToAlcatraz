package com.armoz.roadtoalcatraz.welcome.domain.usercase;


import com.armoz.roadtoalcatraz.welcome.domain.callback.WelcomeCallback;

public interface Welcome {

    void createNewGame(WelcomeCallback welcomeCallback);

}
