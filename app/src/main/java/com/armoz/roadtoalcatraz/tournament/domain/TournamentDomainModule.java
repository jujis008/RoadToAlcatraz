package com.armoz.roadtoalcatraz.tournament.domain;

import com.armoz.roadtoalcatraz.tournament.domain.usercase.PrepareTournament;
import com.armoz.roadtoalcatraz.tournament.domain.usercase.impl.PrepareTournamentJob;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
    public class TournamentDomainModule {

    @Provides
    public PrepareTournament providePrepareTournament( PrepareTournamentJob prepareTournamentJob) {
        return prepareTournamentJob;
    }
}
