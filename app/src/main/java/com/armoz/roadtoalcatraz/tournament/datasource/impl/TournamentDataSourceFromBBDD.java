package com.armoz.roadtoalcatraz.tournament.datasource.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.tournament.datasource.TournamentDataSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class TournamentDataSourceFromBBDD implements TournamentDataSource {

    private static final int SEASON_TOURNAMENTS = 10;
    private static final int TOURNAMENT_HOUR = 20;
    private static final int TOURNAMENT_MINUTE = 0;
    private static final int TOURNAMENT_SECOND = 0;
    private static final int TOURNAMENT_ROUNDS = 3;

    private static final String TAG = "TournamentDSFromBBDD";
    private final Dao<TournamentModel, String> daoTournament;
    private final Dao<GameModel, String> daoGames;


    @Inject
    public TournamentDataSourceFromBBDD(Dao<TournamentModel, String> daoTournament, Dao<GameModel, String> daoGames) {
        this.daoTournament = daoTournament;
        this.daoGames = daoGames;
    }

    @Override
    public TournamentModel obtainTournament(int tournamentID) {

        TournamentModel tournament = new TournamentModel();
        List<GameModel> games = new ArrayList<>();

        try {
            tournament = daoTournament.queryForId(String.valueOf(tournamentID));
        } catch (SQLException e) {
            Log.e(TAG, "Error while obtaining tournament from BBDD", e);
        }

        try {
            QueryBuilder<GameModel, String> builder = daoGames.queryBuilder();
            builder.where().eq("TOURNAMENT_ID", tournamentID);
            builder.orderBy("DATE", true);
            games = daoGames.query(builder.prepare());
        } catch (SQLException e) {
            Log.e(TAG, "Error while obtaining tournament from BBDD", e);
        }

        tournament.setGames(games);
        return tournament;
    }


    @Override
    public List<TournamentModel> obtainAllTournaments() {
        List<TournamentModel> tournaments = new ArrayList<>();

        try {
            QueryBuilder<TournamentModel, String> builder = daoTournament.queryBuilder();
            builder.orderBy("DATE", false);  // true for ascending, false for descending
            builder.where().ge("DATE", new Date(System.currentTimeMillis()));
            tournaments = daoTournament.query(builder.prepare());
        } catch (SQLException e) {
            Log.e(TAG, "Error while obtaining messages from BBDD", e);
        }

        return tournaments;
    }

    @Override
    public List<TournamentModel> createSeasonTournaments() {

        List<TournamentModel> tournamentList = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        //Setting tournament time to default values
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), TOURNAMENT_HOUR, TOURNAMENT_MINUTE, TOURNAMENT_SECOND);
        cal.setTimeInMillis(cal.getTimeInMillis() + 24 * 60 * 60 * 1000);

        for (int i = 0; i < SEASON_TOURNAMENTS; i++) {

            //Creating tournament
            TournamentModel t = new TournamentModel();
            t.setName("TournamentDetail" + (i + 1));
            t.setRounds(TOURNAMENT_ROUNDS);
            t.setDate(cal.getTime());
            t.setLevel(1);

            try {
                daoTournament.create(t);
            } catch (SQLException e) {
                Log.e(TAG, "Error while creating tournaments", e);
            }

            tournamentList.add(t);

            //Preparing next tournament
            cal.setTimeInMillis(cal.getTimeInMillis() + 2 * 24 * 60 * 60 * 1000);
        }

        return tournamentList;
    }
}
