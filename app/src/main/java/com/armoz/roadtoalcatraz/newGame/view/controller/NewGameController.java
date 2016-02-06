package com.armoz.roadtoalcatraz.newGame.view.controller;

import android.content.Context;
import android.util.Log;

import com.armoz.roadtoalcatraz.newGame.domain.callback.NewGameCallback;
import com.armoz.roadtoalcatraz.newGame.domain.usercase.NewGame;
import com.armoz.roadtoalcatraz.tournament.domain.callback.PrepareTournamentCallback;
import com.armoz.roadtoalcatraz.tournament.domain.usercase.PrepareTournament;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class NewGameController {

    private static final String TAG = "NewGameController";
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    private NewGame newGameJob;
    private PrepareTournament prepareTournamentJob;
    
    @Inject
    public NewGameController(NewGame newGameJob, PrepareTournament prepareTournamentJob) {
        this.newGameJob = newGameJob;
        this.prepareTournamentJob = prepareTournamentJob;
    }

    private NewGameCallback newGameCallback = new NewGameCallback() {

        @DebugLog
        @Override
        public void onNewGameCreated() {

            //Prepare next tournament
            Log.d(TAG, "--------------------------  PREPARE NEXT TOURNAMENT -----------------------------");
            prepareTournamentJob.prepareNextTournament(prepareTournamentCallback);

        }

        @Override
        public void onError() {
            view.onError();
        }

    };

    private PrepareTournamentCallback prepareTournamentCallback = new PrepareTournamentCallback() {

        @Override
        public void onTournamentPrepared() {
            view.onNewGameCreated();
        }

        @Override
        public void onError() {
            view.onError();
        }

    };

    public void createNewGame(Context context) {
        newGameJob.createNewGame(context, newGameCallback);
    }
    
    public interface View {

        void onNewGameCreated();

        void onError();

    }

}
