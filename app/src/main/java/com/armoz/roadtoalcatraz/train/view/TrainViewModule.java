package com.armoz.roadtoalcatraz.train.view;

/**
 * Created by ruben.arana on 23/11/15.
 */

import com.armoz.roadtoalcatraz.train.domain.usercase.CreateTraining;
import com.armoz.roadtoalcatraz.train.domain.usercase.Train;
import com.armoz.roadtoalcatraz.train.view.activity.TrainActivity;
import com.armoz.roadtoalcatraz.train.view.controller.TrainController;
import com.armoz.roadtoalcatraz.train.view.fragment.TrainDefensiveFragment;
import com.armoz.roadtoalcatraz.train.view.fragment.TrainMentalFragment;
import com.armoz.roadtoalcatraz.train.view.fragment.TrainOffensiveFragment;
import com.armoz.roadtoalcatraz.train.view.fragment.TrainPhysicalFragment;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module(injects = {TrainJobService.class, TrainActivity.class, TrainOffensiveFragment.class, TrainDefensiveFragment.class, TrainMentalFragment.class, TrainPhysicalFragment.class}, complete = false,
        library = true)
public class TrainViewModule {

    @Provides
    public TrainController provideTrainController(Train trainJob, CreateTraining createTrainingJob) {
        return new TrainController(trainJob, createTrainingJob);
    }

}
