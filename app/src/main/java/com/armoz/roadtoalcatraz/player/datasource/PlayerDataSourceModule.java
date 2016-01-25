package com.armoz.roadtoalcatraz.player.datasource;

import com.armoz.roadtoalcatraz.player.datasource.impl.PlayerDataSourceFromBBDD;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruben.arana on 23/11/15.
 */
@Module(complete = false, library = true)
public class PlayerDataSourceModule {

    @Provides
    public PlayerDataSource providesPlayerDataSource(PlayerDataSourceFromBBDD playerDataSourceFromBBDD) {
        return playerDataSourceFromBBDD;
    }
}
