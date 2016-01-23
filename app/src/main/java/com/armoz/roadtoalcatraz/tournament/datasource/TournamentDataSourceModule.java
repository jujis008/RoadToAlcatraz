package com.armoz.roadtoalcatraz.tournament.datasource;

import com.armoz.roadtoalcatraz.tournament.datasource.impl.TournamentDataSourceFromBBDD;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruben.arana on 23/11/15.
 */
@Module(complete = false, library = true)
public class TournamentDataSourceModule {

    @Provides
    public TournamentDataSource providesTournamentDataSource(TournamentDataSourceFromBBDD tournamentDataSourceFromBBDD) {
        return tournamentDataSourceFromBBDD;
    }
}
