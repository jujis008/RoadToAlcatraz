package com.armoz.roadtoalcatraz.game.datasource.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.game.datasource.GameDataSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class GameDataSourceFromBBDD implements GameDataSource {


    private static final String TAG = "GameDSFromBBDD";

    private final Dao<GameModel, String> daoGames;

    @Inject
    public GameDataSourceFromBBDD(Dao<GameModel, String> daoGames) {
        this.daoGames = daoGames;
    }

    @Override
    public List<GameModel> createTournamentGames(TournamentModel tournamentModel) {

        List<GameModel> gameList = new ArrayList<>();

        int round = 1;
        Calendar gameCalendar = Calendar.getInstance();
        gameCalendar.setTime(tournamentModel.getDate());

        for (int j = 0; j < Math.pow(2, tournamentModel.getRounds()) - 1; j++) {

            if (j >= Math.pow(2, round) - 1) {
                round++;
                gameCalendar.setTimeInMillis(gameCalendar.getTimeInMillis() - 2 * 60 * 60 * 1000);
            }

            GameModel g = new GameModel();
            g.setDate(gameCalendar.getTime());
            g.setRound(round);
            g.setTournamentID(tournamentModel.getId());

            try {
                daoGames.create(g);
            } catch (SQLException e) {
                Log.e(TAG, "Error while creating games", e);
            }

            gameList.add(g);
        }

        return gameList;
    }

    @Override
    public void updateGame(GameModel game) {

        try {
            daoGames.update(game);
        } catch (SQLException e) {
            Log.e(TAG, "Error while updating game", e);
        }

    }

    @Override
    public List<GameModel> obtainTournamentGames(int tournamentId) {

        List<GameModel> games = new ArrayList<>();

        try {
            QueryBuilder<GameModel, String> builder = daoGames.queryBuilder();
            builder.where().eq(GameModel.TOURNAMENT_ID, tournamentId);
            games = daoGames.query(builder.prepare());
        } catch (SQLException e) {
            Log.e(TAG, "Error while obtaining tournament games from BBDD", e);
        }

        return games;
    }

}


