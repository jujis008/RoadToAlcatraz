package com.armoz.roadtoalcatraz.train.domain;

import com.armoz.roadtoalcatraz.train.domain.usercase.CreateTraining;
import com.armoz.roadtoalcatraz.train.domain.usercase.Train;
import com.armoz.roadtoalcatraz.train.domain.usercase.impl.CreateTrainingJob;
import com.armoz.roadtoalcatraz.train.domain.usercase.impl.TrainJob;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class TrainDomainModule {

    @Provides
    public Train provideTrain(TrainJob trainJob) {
        return trainJob;
    }

    @Provides
    public CreateTraining provideCreateTraining(CreateTrainingJob createTrainingJob) {
        return createTrainingJob;
    }

}
