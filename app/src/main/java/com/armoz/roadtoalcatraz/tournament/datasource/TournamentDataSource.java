package com.armoz.roadtoalcatraz.tournament.datasource;

import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;

import java.util.List;

/**
 * Created by ruben.arana on 23/11/15.
 */
public interface TournamentDataSource {

    TournamentModel obtainTournament(int tournamentID);

    List<TournamentModel> obtainAllTournaments();

    List<TournamentModel> createSeasonTournaments();

    TournamentModel obtainNextTournament();
}
