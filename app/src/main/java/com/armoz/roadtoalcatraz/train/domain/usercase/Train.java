package com.armoz.roadtoalcatraz.train.domain.usercase;


import com.armoz.roadtoalcatraz.train.domain.callback.TrainCallback;

public interface Train {

    void obtainPlayer(TrainCallback trainCallback);

    void executeTraining();
}
