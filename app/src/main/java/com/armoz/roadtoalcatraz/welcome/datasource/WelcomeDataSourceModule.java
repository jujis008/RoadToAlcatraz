package com.armoz.roadtoalcatraz.welcome.datasource;

import android.content.Context;

import com.armoz.roadtoalcatraz.base.daggerutils.ForApplication;
import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.welcome.datasource.impl.WelcomeDataSourceFromBBDD;
import com.j256.ormlite.dao.Dao;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruben.arana on 23/11/15.
 */
@Module(complete = false, library = true)
public class WelcomeDataSourceModule {

    @Provides
    public WelcomeDataSource providesWelcomeDataSource(
            @ForApplication Context context,
            Dao<MessageModel, String> daoMessages,
            Dao<TournamentModel, String> daoTournament,
            Dao<GameModel, String> daoGames,
            Dao<PlayerModel, String> daoPlayers,
            Dao<StrategyModel, String> daoStrategy) {

        return new WelcomeDataSourceFromBBDD(context,daoMessages,daoTournament, daoGames, daoPlayers, daoStrategy);
    }
}
