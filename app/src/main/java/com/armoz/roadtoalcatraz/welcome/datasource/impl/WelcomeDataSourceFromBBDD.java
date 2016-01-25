package com.armoz.roadtoalcatraz.welcome.datasource.impl;

import android.content.Context;
import android.util.Log;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
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
    private static final int PLAYER_NUMBER = 64;
    private static final int MAX_AGE = 33;
    private static final int MIN_AGE = 18;
    private static final int MIN_SKILL_VALUE = 0;
    private static final int MAX_SKILL_VALUE = 100;
    private static final int MIN_STRATEGY_VALUE = 0;
    private static final int MAX_STRATEGY_VALUE = 100;

    private final Dao<TournamentModel, String> daoTournament;
    private final Dao<MessageModel, String> daoMessages;
    private final Dao<GameModel, String> daoGames;
    private final Dao<PlayerModel, String> daoPlayers;
    private final Dao<StrategyModel, String> daoStrategy;


    private Context context;

    @Inject
    public WelcomeDataSourceFromBBDD(Context context, Dao<MessageModel, String> daoMessages,
                                     Dao<TournamentModel, String> daoTournament, Dao<GameModel, String> daoGames,
                                     Dao<PlayerModel, String> daoPlayers, Dao<StrategyModel, String> daoStrategy) {
        this.daoMessages = daoMessages;
        this.daoTournament = daoTournament;
        this.daoGames = daoGames;
        this.daoPlayers = daoPlayers;
        this.daoStrategy = daoStrategy;
        this.context = context;
    }

    @Override
    public void createNewGame() throws Exception {

        Log.d(TAG, "creating game datasource");

        //maybe is better to split into core things (user should wait) and other stuff not required for the first activity.

        //Creating messages
        createFirstMessage();
        createQuestMessage();

        //Creating quests

        //Creating user player
        createMyPlayer();

        //Creating players
        createAllPlayers(PLAYER_NUMBER);

        //Creating tournaments
        createSeasonTournaments();

        //Creating employees
        Log.d(TAG, "Game created datasource");


    }

    private void createAllPlayers(int playersNumber) throws Exception {

        for (int i = 0; i < playersNumber; i++) {

            PlayerModel player = new PlayerModel();
            player.setUserPlayer(false);
            player.setAge(getRandomValue(MIN_AGE, MAX_AGE) + MIN_AGE);
            player.setHeight(190);
            player.setWeight(90);
            player.setName("Name" + i);
            player.setSurname("Surname" + i);
            player.setCountry("Spain");
            player.setYearsPlayed(MAX_AGE - MIN_AGE);

            //Physical
            player.setStamina(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setJump(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setSpeed(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setStrength(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));

            //Technical Ofensive Skills
            player.setDribble(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setPostPlay(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setIntShoot(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setExtShoot(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setOffensiveRebounding(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));

            //Technical Defensive Skills
            player.setSteal(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setBlock(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setIntDefense(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setExtDefense(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setDefensiveRebounding(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));

            //Mental Skills
            player.setMentalToughness(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setWorkethic(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));
            player.setFriendly(getRandomValue(MIN_SKILL_VALUE, MAX_SKILL_VALUE));

            daoPlayers.create(player);

            createStrategy(player);
        }
    }

    private void createStrategy(PlayerModel playerModel) throws SQLException{

        StrategyModel strategy = new StrategyModel();
        strategy.setLessToMorePhysical(getRandomValue(MIN_STRATEGY_VALUE, MAX_STRATEGY_VALUE));
        strategy.setLessToMoreTrashtalking(getRandomValue(MIN_STRATEGY_VALUE, MAX_STRATEGY_VALUE));

        strategy.setLessToMoreExtActions(getRandomValue(MIN_STRATEGY_VALUE, MAX_STRATEGY_VALUE));
        strategy.setPenetrationVsPostmove(getRandomValue(MIN_STRATEGY_VALUE, MAX_STRATEGY_VALUE));
        strategy.setQuickVsElaboratedShoot(getRandomValue(MIN_STRATEGY_VALUE, MAX_STRATEGY_VALUE));
        strategy.setFightOffensiveRebounding(getRandomValue(MIN_STRATEGY_VALUE, MAX_STRATEGY_VALUE));

        strategy.setLessToMoreSteal(getRandomValue(MIN_STRATEGY_VALUE, MAX_STRATEGY_VALUE));
        strategy.setLessToMoreSpacing(getRandomValue(MIN_STRATEGY_VALUE, MAX_STRATEGY_VALUE));
        strategy.setLessToMoreBlocking(getRandomValue(MIN_STRATEGY_VALUE, MAX_STRATEGY_VALUE));
        strategy.setFightDefensiveRebounding(getRandomValue(MIN_STRATEGY_VALUE, MAX_STRATEGY_VALUE));

        daoStrategy.create(strategy);

        playerModel.setStrategy(strategy.getId());
        daoPlayers.update(playerModel);
    }

    private void createMyStrategy(PlayerModel playerModel) throws SQLException {

        StrategyModel strategy = new StrategyModel();
        strategy.setLessToMorePhysical(50);
        strategy.setLessToMoreTrashtalking(50);

        strategy.setLessToMoreExtActions(50);
        strategy.setPenetrationVsPostmove(50);
        strategy.setQuickVsElaboratedShoot(50);
        strategy.setFightOffensiveRebounding(50);

        strategy.setLessToMoreSteal(50);
        strategy.setLessToMoreSpacing(50);
        strategy.setLessToMoreBlocking(50);
        strategy.setFightDefensiveRebounding(50);

        daoStrategy.create(strategy);

        playerModel.setStrategy(strategy.getId());
        daoPlayers.update(playerModel);

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

        createMyStrategy(player);
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

    private int getRandomValue(int minValue, int maxValue){
        return (int) ((Math.random() * (maxValue - minValue)) + minValue);
    }
}


