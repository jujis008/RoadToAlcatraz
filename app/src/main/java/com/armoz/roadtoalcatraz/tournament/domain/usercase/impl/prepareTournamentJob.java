package com.armoz.roadtoalcatraz.tournament.domain.usercase.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.game.datasource.GameDataSource;
import com.armoz.roadtoalcatraz.player.datasource.PlayerDataSource;
import com.armoz.roadtoalcatraz.tournament.datasource.TournamentDataSource;
import com.armoz.roadtoalcatraz.tournament.domain.callback.PrepareTournamentCallback;
import com.armoz.roadtoalcatraz.tournament.domain.usercase.PrepareTournament;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

;import hugo.weaving.DebugLog;

public class PrepareTournamentJob extends UserCaseJob implements PrepareTournament {

    private static final String TAG = "PrepareTournamentJob";
    private GameDataSource gameDataSource;
    private TournamentDataSource tournamentDataSource;
    private PlayerDataSource playerDataSource;
    private PrepareTournamentCallback callback;

    @Inject
    Bus bus;

    @Inject
    PrepareTournamentJob(JobManager jobManager, MainThread mainThread,
                         DomainErrorHandler domainErrorHandler, GameDataSource gameDataSource,
                         TournamentDataSource tournamentDataSource, PlayerDataSource playerDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.gameDataSource = gameDataSource;
        this.tournamentDataSource = tournamentDataSource;
        this.playerDataSource = playerDataSource;
    }


    @Override
    public void prepareNextTournament(PrepareTournamentCallback callback) {
        this.callback = callback;
        jobManager.addJob(this);
    }

    @Override
    @DebugLog
    public void doRun() throws Throwable {
        try {
            TournamentModel tournamentModel = tournamentDataSource.obtainNextTournament();
            List<PlayerModel> players = playerDataSource.obtainPlayers((long) Math.pow(2, tournamentModel.getRounds()));
            List<GameModel> games = gameDataSource.createTournamentGames(tournamentModel);

            Log.d(TAG, "TOURNAMENT ID: " +  tournamentModel.getId());
            Log.d(TAG, "PLAYERS: " +  players.size());
            Log.d(TAG, "GAMES: " +  games.size());

            prepareNextTournamentGames(tournamentModel, players, games);

            onTournamentPrepared();
        } catch (Exception e) {
            notifyError();
        }
    }

    private void prepareNextTournamentGames(TournamentModel tournamentModel, List<PlayerModel> players, List<GameModel> games) {

        int playersIndex = 0;

        for (GameModel game : games) {

            if (game.getRound() == tournamentModel.getRounds()) {

                PlayerModel player1 = players.get(playersIndex);
                PlayerModel player2 = players.get(playersIndex + 1);

                game.setPlayer1Id(player1.getId());
                game.setPlayer2Id(player2.getId());

                playersIndex = playersIndex + 2;

                if (player1.isUserPlayer() || player2.isUserPlayer()) {
                    //Schedule game
                }

            }

            gameDataSource.updateGame(game);
        }
    }

    private void notifyError() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError();
                }
            }
        });
    }

    private void onTournamentPrepared() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onTournamentPrepared();
            }
        });
    }


}
