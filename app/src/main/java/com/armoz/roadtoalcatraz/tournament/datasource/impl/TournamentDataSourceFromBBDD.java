package com.armoz.roadtoalcatraz.tournament.datasource.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.tournament.datasource.TournamentDataSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class TournamentDataSourceFromBBDD implements TournamentDataSource {


    private static final String LOGTAG = "TournamentDSFromBBDD";
    private final Dao<TournamentModel, String> daoTournaments;
    private final Dao<GameModel, String> daoGames;


    @Inject
    public TournamentDataSourceFromBBDD(Dao<TournamentModel, String> daoTournaments, Dao<GameModel, String> daoGames) {
        this.daoTournaments = daoTournaments;
        this.daoGames = daoGames;
    }

    @Override
    public TournamentModel obtainTournament(int tournamentID) {

        TournamentModel tournament = new TournamentModel();
        List<GameModel> games = new ArrayList<>();

        try {
            tournament = daoTournaments.queryForId(String.valueOf(tournamentID));
        } catch (SQLException e) {
            Log.e(LOGTAG, "Error while obtaining tournament from BBDD", e);
        }

        try {
            QueryBuilder<GameModel, String> builder = daoGames.queryBuilder();
            builder.where().eq("TOURNAMENT_ID", tournamentID);
            builder.orderBy("DATE", true);
            games = daoGames.query(builder.prepare());
        } catch (SQLException e) {
            Log.e(LOGTAG, "Error while obtaining tournament from BBDD", e);
        }

        tournament.setGames(games);
        return tournament;
    }
}
