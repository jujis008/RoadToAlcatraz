package com.armoz.roadtoalcatraz.player.datasource;

import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;

import java.util.List;

/**
 * Created by ruben.arana on 23/11/15.
 */
public interface PlayerDataSource {

    PlayerModel obtainUserPlayer();

    PlayerModel createMyPlayer();

    List<PlayerModel> createAllPlayers();
}
