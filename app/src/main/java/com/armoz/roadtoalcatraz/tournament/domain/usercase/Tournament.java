package com.armoz.roadtoalcatraz.tournament.domain.usercase;


import com.armoz.roadtoalcatraz.tournament.domain.callback.TournamentCallback;

public interface Tournament {

    void obtainTournamentInfo(TournamentCallback tournamentCallback, long tournamentID);

}
