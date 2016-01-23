package com.armoz.roadtoalcatraz.tournament.domain.callback;


import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;

/**
 *
 */
public interface TournamentCallback {

    void onTournamentLoaded(TournamentModel tournament);

    void onError();
}
