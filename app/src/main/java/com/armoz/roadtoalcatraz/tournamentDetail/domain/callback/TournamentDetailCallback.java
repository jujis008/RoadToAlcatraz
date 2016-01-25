package com.armoz.roadtoalcatraz.tournamentDetail.domain.callback;


import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;

/**
 *
 */
public interface TournamentDetailCallback {

    void onTournamentDetailLoaded(TournamentModel tournament);

    void onError();
}
