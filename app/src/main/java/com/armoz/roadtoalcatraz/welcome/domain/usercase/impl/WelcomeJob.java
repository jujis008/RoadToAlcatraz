package com.armoz.roadtoalcatraz.welcome.domain.usercase.impl;

import android.content.Context;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.game.datasource.GameDataSource;
import com.armoz.roadtoalcatraz.message.datasource.MessageDataSource;
import com.armoz.roadtoalcatraz.player.datasource.PlayerDataSource;
import com.armoz.roadtoalcatraz.tournament.datasource.TournamentDataSource;
import com.armoz.roadtoalcatraz.welcome.domain.callback.WelcomeCallback;
import com.armoz.roadtoalcatraz.welcome.domain.usercase.Welcome;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

import java.util.List;

import javax.inject.Inject;

;

public class WelcomeJob extends UserCaseJob implements Welcome {

    private static final String TAG = "WelcomeJob";
    private WelcomeCallback callback;
    private MessageDataSource messageDataSource;
    private PlayerDataSource playerDataSource;
    private TournamentDataSource tournamentDataSource;
    private GameDataSource gameDataSource;

    private Context context;


    @Inject
    WelcomeJob(JobManager jobManager, MainThread mainThread, DomainErrorHandler domainErrorHandler,
               MessageDataSource messageDataSource,
               PlayerDataSource playerDataSource, TournamentDataSource tournamentDataSource,
               GameDataSource gameDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.messageDataSource = messageDataSource;
        this.playerDataSource = playerDataSource;
        this.tournamentDataSource = tournamentDataSource;
        this.gameDataSource = gameDataSource;

    }


    @Override
    public void doRun() throws Throwable {
        try {
            //Creating messages
            messageDataSource.createMessage(context.getString(R.string.welcome_first_message_title),
                    context.getString(R.string.welcome_first_message_body), MessageModel.TYPE_INFO);
            messageDataSource.createMessage(context.getString(R.string.welcome_quest_message_title),
                    context.getString(R.string.welcome_quest_message_body), MessageModel.TYPE_INFO);

            //Creating quests

            //Creating user player
            playerDataSource.createMyPlayer();

            //Creating players
            playerDataSource.createAllPlayers();

            //Creating tournaments
            List<TournamentModel> tournamentModelList = tournamentDataSource.createSeasonTournaments();

            for (TournamentModel t : tournamentModelList) {
                gameDataSource.createTournamentGames(t);
            }

            onNewGameCreated();
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

    private void onNewGameCreated() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onNewGameCreated();
            }
        });
    }


    @Override
    public void createNewGame(Context context, WelcomeCallback welcomeCallback) {
        this.callback = welcomeCallback;
        this.context = context;
        jobManager.addJob(this);
    }
}
