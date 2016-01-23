package com.armoz.roadtoalcatraz.train.domain.callback;


import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;

/**
 *
 */
public interface TrainReloadCallback extends TrainCallback {

    void onPlayerLoaded(PlayerModel playerModel);

    void onError();
}
