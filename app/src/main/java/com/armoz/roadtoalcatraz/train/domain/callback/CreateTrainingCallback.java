package com.armoz.roadtoalcatraz.train.domain.callback;


import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;

/**
 *
 */
public interface CreateTrainingCallback {

    void onTrainingCreated(PlayerModel playerModel);

    void onError();
}
