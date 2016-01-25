package com.armoz.roadtoalcatraz.player.datasource.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.player.datasource.PlayerDataSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class PlayerDataSourceFromBBDD implements PlayerDataSource {

    private static final int PLAYER_NUMBER = 64;
    private static final int MAX_AGE = 33;
    private static final int MIN_AGE = 18;
    private static final int MIN_SKILL_VALUE = 0;
    private static final int MAX_SKILL_VALUE = 100;
    private static final int MIN_STRATEGY_VALUE = 0;
    private static final int MAX_STRATEGY_VALUE = 100;

    private static final String TAG = "PlayerDSFromBBDD";
    private final Dao<PlayerModel, String> daoPlayers;
    private final Dao<StrategyModel, String> daoStrategy;


    @Inject
    public PlayerDataSourceFromBBDD(Dao<PlayerModel, String> daoPlayers, Dao<StrategyModel, String> daoStrategy) {
        this.daoPlayers = daoPlayers;
        this.daoStrategy = daoStrategy;
    }

    @Override
    public PlayerModel obtainUserPlayer() {
        PlayerModel model = new PlayerModel();

        try {
            QueryBuilder<PlayerModel, String> builder = daoPlayers.queryBuilder();
            builder.where().eq("USER_PLAYER", true);
            model = daoPlayers.queryForFirst(builder.prepare());
        } catch (SQLException e) {
            Log.e(TAG, "Error while obtaining player from BBDD", e);
        }

        return model;
    }

    @Override
    public PlayerModel createMyPlayer() {

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

        try {
            daoPlayers.create(player);
        } catch (SQLException e) {
            Log.d(TAG, "Error while creating a player");
        }

        createMyStrategy(player);

        return player;
    }

    @Override
    public List<PlayerModel> createAllPlayers() {

        List<PlayerModel> playerList = new ArrayList<>();

        for (int i = 0; i < PLAYER_NUMBER; i++) {

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

            try {
                daoPlayers.create(player);
            } catch (SQLException e) {
                Log.d(TAG, "Error while creating a player");
            }

            playerList.add(player);

            createStrategy(player);
        }

        return playerList;
    }

    private void createMyStrategy(PlayerModel playerModel) {

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

        try {
            daoStrategy.create(strategy);
        } catch (SQLException e) {
            Log.d(TAG, "Error while creating a strategy");
        }

        playerModel.setStrategy(strategy.getId());
        try {
            daoPlayers.update(playerModel);
        } catch (SQLException e) {
            Log.d(TAG, "Error while updating a player");
        }

    }


    private void createStrategy(PlayerModel playerModel) {

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

        try {
            daoStrategy.create(strategy);
        } catch (SQLException e) {
            Log.d(TAG, "Error while creating a strategy");
        }

        playerModel.setStrategy(strategy.getId());
        try {
            daoPlayers.update(playerModel);
        } catch (SQLException e) {
            Log.d(TAG, "Error while updating a player");

        }
    }

    private int getRandomValue(int minValue, int maxValue){
        return (int) ((Math.random() * (maxValue - minValue)) + minValue);
    }

}
