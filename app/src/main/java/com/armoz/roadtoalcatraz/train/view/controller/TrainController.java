package com.armoz.roadtoalcatraz.train.view.controller;

import com.armoz.roadtoalcatraz.base.domain.events.ReloadEvent;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.train.domain.callback.CreateTrainingCallback;
import com.armoz.roadtoalcatraz.train.domain.callback.TrainCallback;
import com.armoz.roadtoalcatraz.train.domain.callback.TrainReloadCallback;
import com.armoz.roadtoalcatraz.train.domain.usercase.CreateTraining;
import com.armoz.roadtoalcatraz.train.domain.usercase.Train;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class TrainController {

    private static final String TAG = "StrategyController";
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    private Train trainJob;
    private CreateTraining createTrainingJob;


    private PlayerModel model = new PlayerModel();


    @Inject
    public TrainController(Train trainJob, CreateTraining createTrainingJob) {
        this.trainJob = trainJob;
        this.createTrainingJob = createTrainingJob;

    }

    private TrainCallback callback = new TrainCallback() {
        @Override
        public void onPlayerLoaded(PlayerModel playerModel) {
            model = playerModel;
            view.onPlayerLoaded();
        }

        @Override
        public void onError() {
            view.onError();
        }

    };

    private TrainReloadCallback reloadCallback = new TrainReloadCallback() {
        @Override
        public void onPlayerLoaded(PlayerModel playerModel) {
            model = playerModel;
            view.onReloadTraining();
        }

        @Override
        public void onError() {
            view.onError();
        }

    };

    private CreateTrainingCallback createTrainingCallback = new CreateTrainingCallback() {

        @Override
        public void onTrainingCreated(PlayerModel playerModel) {
            model = playerModel;
            view.onReloadTraining();
        }

        @Override
        public void onError() {
            view.onError();
        }

    };


    public PlayerModel getModel() {
        return model;
    }

    public void obtainPlayer() {
        trainJob.obtainPlayer(callback);
    }

    public void executeTraining() {
        trainJob.executeTraining();
    }

    public void createTraining(String skillName, long time) {
        createTrainingJob.createTraining(createTrainingCallback, skillName, time);
    }

    public void reloadTraining() {
        trainJob.obtainPlayer(reloadCallback);
    }

    public interface View {

        public void onError();

        public void onPlayerLoaded();

        public void onReloadTraining();

        public void onReloadEvent(ReloadEvent reloadEvent);

    }

}
