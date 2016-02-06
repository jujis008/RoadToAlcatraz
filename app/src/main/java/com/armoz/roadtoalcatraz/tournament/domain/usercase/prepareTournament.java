package com.armoz.roadtoalcatraz.tournament.domain.usercase;


import com.armoz.roadtoalcatraz.tournament.domain.callback.PrepareTournamentCallback;

public interface PrepareTournament {

    void prepareNextTournament(PrepareTournamentCallback callback);
}
