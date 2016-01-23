package com.armoz.roadtoalcatraz.welcome.datasource.impl;

import android.content.Context;
import android.util.Log;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.welcome.datasource.WelcomeDataSource;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class WelcomeDataSourceFromBBDD implements WelcomeDataSource {


    private static final String TAG = "FeedDataSourceFromBBDD";
    private static final int SEASON_TOURNAMENTS = 10;
    private static final int TOURNAMENT_HOUR = 20;
    private static final int TOURNAMENT_MINUTE = 0;
    private static final int TOURNAMENT_SECOND = 0;
    private static final int TOURNAMENT_ROUNDS = 3;
    private final Dao<TournamentModel, String> daoTournament;
    private final Dao<MessageModel, String> daoMessages;
    private final Dao<GameModel, String> daoGames;
    private final Dao<PlayerModel, String> daoPlayers;

    private Context context;

    @Inject
    public WelcomeDataSourceFromBBDD(Context context, Dao<MessageModel, String> daoMessages,
                                     Dao<TournamentModel, String> daoTournament, Dao<GameModel, String> daoGames,
                                     Dao<PlayerModel, String> daoPlayers) {
        this.daoMessages = daoMessages;
        this.daoTournament = daoTournament;
        this.daoGames = daoGames;
        this.daoPlayers = daoPlayers;
        this.context = context;
    }

    @Override
    public void createNewGame() throws Exception {

        Log.d(TAG, "creating game datasource");

        //maybe is better to split into core things (user should wait) and other stuff not required for the first activity.

        //Creating messages
        createFirstMessage();
        createQuestMessage();

        //Crear rewards

        //Crear myPLayer + my strategy + my training
        createMyPlayer();

        //Crear tornejos temporada
        createSeasonTournaments();
        //Crear resta de jugadors

        //Crear resta de estrategies

        //Crear resta de personal
        Log.d(TAG, "Game created datasource");


    }

    private void createMyPlayer() throws SQLException{

        PlayerModel player = new PlayerModel();
        player.setUserPlayer(true);
        player.setAge(18);
        player.setHeight(190);
        player.setWeight(90);
        player.setName("Ruben");
        player.setSurname("Arana");
        player.setCountry("Spain");
        player.setYearsPlayed(0);

        //Physical
        player.setStamina(0);
        player.setJump(0);
        player.setSpeed(0);
        player.setStrength(0);

        //Technical Ofensive Skills
        player.setDribble(0);
        player.setPostPlay(0);
        player.setIntShoot(0);
        player.setExtShoot(0);
        player.setOffensiveRebounding(0);

        //Technical Defensive Skills
        player.setSteal(0);
        player.setBlock(0);
        player.setIntDefense(0);
        player.setExtDefense(0);
        player.setDefensiveRebounding(0);

        //Mental Skills
        player.setMentalToughness(0);
        player.setWorkethic(0);
        player.setFriendly(0);

        daoPlayers.create(player);
    }

    private void createSeasonTournaments() throws SQLException {

        Calendar cal = Calendar.getInstance();
        //Setting tournament time to default values
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), TOURNAMENT_HOUR, TOURNAMENT_MINUTE, TOURNAMENT_SECOND);
        cal.setTimeInMillis(cal.getTimeInMillis() + 24 * 60 * 60 * 1000);

        for (int i = 0; i < SEASON_TOURNAMENTS; i++) {

            //Creating tournament
            TournamentModel t = new TournamentModel();
            t.setName("Tournament" + (i + 1));
            t.setRounds(TOURNAMENT_ROUNDS);
            t.setDate(cal.getTime());
            t.setLevel(1);

            daoTournament.create(t);

            //Creating tournament games
            createTournamentGames(t);

            //Preparing next tournament
            cal.setTimeInMillis(cal.getTimeInMillis() + 2 * 24 * 60 * 60 * 1000);
        }

    }

    private void createTournamentGames(TournamentModel t) throws SQLException{

        int round = 1;
        Calendar gameCalendar = Calendar.getInstance();
        gameCalendar.setTime(t.getDate());

        for (int j = 0; j < Math.pow(2, TOURNAMENT_ROUNDS) - 1; j++) {

            if (j >= Math.pow(2, round) - 1) {
                round++;
                gameCalendar.setTimeInMillis(gameCalendar.getTimeInMillis() - 2 * 60 * 60 * 1000);
            }

            GameModel g = new GameModel();
            g.setDate(gameCalendar.getTime());
            g.setRound(round);
            g.setTournamentID(t.getId());
            daoGames.create(g);
        }
    }

    private void createFirstMessage() throws SQLException {
        MessageModel m = new MessageModel();
        m.setBody(context.getString(R.string.welcome_first_message_body));
        m.setTitle(context.getString(R.string.welcome_first_message_title));
        m.setRead(false);
        m.setDate(new Date(System.currentTimeMillis()));
        m.setType("INFO");
        daoMessages.create(m);
    }

    private void createQuestMessage() throws SQLException {
        MessageModel m = new MessageModel();
        m.setBody(context.getString(R.string.welcome_quest_message_body));
        m.setTitle(context.getString(R.string.welcome_quest_message_title));
        m.setRead(false);
        m.setDate(new Date(System.currentTimeMillis()));
        m.setType("INFO");
        daoMessages.create(m);
    }
}
