package com.armoz.roadtoalcatraz.newGame.domain.usercase.impl;

import android.content.Context;
import android.util.Log;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;
import com.armoz.roadtoalcatraz.message.datasource.MessageDataSource;
import com.armoz.roadtoalcatraz.newGame.domain.callback.NewGameCallback;
import com.armoz.roadtoalcatraz.newGame.domain.usercase.NewGame;
import com.armoz.roadtoalcatraz.player.datasource.PlayerDataSource;
import com.armoz.roadtoalcatraz.tournament.datasource.TournamentDataSource;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

import javax.inject.Inject;


public class NewGameJob extends UserCaseJob implements NewGame {

    private static final String TAG = "NewGameJob";
    private NewGameCallback callback;
    private MessageDataSource messageDataSource;
    private PlayerDataSource playerDataSource;
    private TournamentDataSource tournamentDataSource;

    private Context context;


    @Inject
    NewGameJob(JobManager jobManager, MainThread mainThread, DomainErrorHandler domainErrorHandler,
               MessageDataSource messageDataSource,
               PlayerDataSource playerDataSource, TournamentDataSource tournamentDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.messageDataSource = messageDataSource;
        this.playerDataSource = playerDataSource;
        this.tournamentDataSource = tournamentDataSource;

    }

    @Override
    public void doRun() throws Throwable {
        try {
            //Creating messages
            Log.d(TAG, "--------------------------  MESSAGES -----------------------------");
            messageDataSource.createMessage(context.getString(R.string.new_game_first_message_title),
                    context.getString(R.string.new_game_first_message_body), MessageModel.TYPE_INFO);
            messageDataSource.createMessage(context.getString(R.string.new_game_quest_message_title),
                    context.getString(R.string.new_game_quest_message_body), MessageModel.TYPE_INFO);

            //Creating quests


            //Creating user player
            Log.d(TAG, "--------------------------  USER PLAYER -----------------------------");
            playerDataSource.createMyPlayer();

            //Creating players
            Log.d(TAG, "--------------------------  PLAYERS -----------------------------");
            playerDataSource.createAllPlayers();

            //Creating tournaments
            Log.d(TAG, "--------------------------  TOURNAMENTS -----------------------------");
            tournamentDataSource.createSeasonTournaments();

            onNewGameCreated(context);

        }
        catch (Exception e){
            notifyError();
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

    private void onNewGameCreated(final Context context) {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onNewGameCreated(context);
            }
        });
    }


    @Override
    public void createNewGame(Context context, NewGameCallback newGameCallback) {
        this.callback = newGameCallback;
        this.context = context;
        jobManager.addJob(this);
    }
}
