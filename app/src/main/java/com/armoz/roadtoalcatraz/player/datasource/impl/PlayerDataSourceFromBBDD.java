package com.armoz.roadtoalcatraz.player.datasource.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.player.datasource.PlayerDataSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class PlayerDataSourceFromBBDD implements PlayerDataSource {


    private static final String LOGTAG = "PlayerDSFromBBDD";
    private final Dao<PlayerModel, String> daoPlayers;

    @Inject
    public PlayerDataSourceFromBBDD(Dao<PlayerModel, String> daoPlayers) {
        this.daoPlayers = daoPlayers;
    }

    @Override
    public PlayerModel obtainUserPlayer() {
        PlayerModel model = new PlayerModel();

        try {
            QueryBuilder<PlayerModel, String> builder = daoPlayers.queryBuilder();
            builder.where().eq("USER_PLAYER", true);
            model = daoPlayers.queryForFirst(builder.prepare());
        } catch (SQLException e) {
            Log.e(LOGTAG, "Error while obtaining player from BBDD", e);
        }

        return model;
    }

}
