package com.armoz.roadtoalcatraz.train.datasource;

import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;

/**
 * Created by ruben.arana on 23/11/15.
 */
public interface TrainDataSource {

    PlayerModel executeTraining(PlayerModel playerModel);

    PlayerModel createTraining(PlayerModel playerModel, String skillName, long time);
}
