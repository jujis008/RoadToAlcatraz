package com.armoz.roadtoalcatraz.train.domain.usercase;


import com.armoz.roadtoalcatraz.train.domain.callback.CreateTrainingCallback;

public interface CreateTraining {

    void createTraining(CreateTrainingCallback callback, String skillName, long time);
}
