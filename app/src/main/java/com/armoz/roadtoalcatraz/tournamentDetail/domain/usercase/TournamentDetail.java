package com.armoz.roadtoalcatraz.tournamentDetail.domain.usercase;


import com.armoz.roadtoalcatraz.tournamentDetail.domain.callback.TournamentDetailCallback;

public interface TournamentDetail {

    void obtainTournament(TournamentDetailCallback tournamentDetailCallback, int tournamentID);

}
