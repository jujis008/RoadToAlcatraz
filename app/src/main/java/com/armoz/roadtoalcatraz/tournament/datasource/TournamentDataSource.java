package com.armoz.roadtoalcatraz.tournament.datasource;

import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;

/**
 * Created by ruben.arana on 23/11/15.
 */
public interface TournamentDataSource {

    TournamentModel obtainTournamentInfo(long tournamentID);
}
