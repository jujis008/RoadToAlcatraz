package com.armoz.roadtoalcatraz.tournamentDetail.domain;

import com.armoz.roadtoalcatraz.tournamentDetail.domain.usercase.TournamentDetail;
import com.armoz.roadtoalcatraz.tournamentDetail.domain.usercase.impl.TournamentDetailJob;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class TournamentDetailDomainModule {

    @Provides
    public TournamentDetail provideTournament(TournamentDetailJob tournamentJob) {
        return tournamentJob;
    }

}
