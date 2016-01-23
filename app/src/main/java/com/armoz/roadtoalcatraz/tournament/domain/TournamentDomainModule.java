package com.armoz.roadtoalcatraz.tournament.domain;

import com.armoz.roadtoalcatraz.tournament.domain.usercase.Tournament;
import com.armoz.roadtoalcatraz.tournament.domain.usercase.impl.TournamentJob;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class TournamentDomainModule {

    @Provides
    public Tournament provideTournament(TournamentJob tournamentJob) {
        return tournamentJob;
    }

}
