package com.armoz.roadtoalcatraz.game.datasource;

import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.game.datasource.impl.GameDataSourceFromBBDD;
import com.j256.ormlite.dao.Dao;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruben.arana on 23/11/15.
 */
@Module(complete = false, library = true)
public class GameDataSourceModule {

    @Provides
    public GameDataSource providesGameDataSource(Dao<GameModel, String> daoGames) {

        return new GameDataSourceFromBBDD(daoGames);
    }
}
