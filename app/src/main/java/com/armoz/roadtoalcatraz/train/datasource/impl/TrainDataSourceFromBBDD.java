package com.armoz.roadtoalcatraz.train.datasource.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.train.datasource.TrainDataSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class TrainDataSourceFromBBDD implements TrainDataSource {


    private static final String LOGTAG = "TrainDSFromBBDD";
    private final Dao<PlayerModel, String> daoPlayers;

    @Inject
    public TrainDataSourceFromBBDD(Dao<PlayerModel, String> daoPlayers) {
        this.daoPlayers = daoPlayers;
    }

    @Override
    public PlayerModel obtainPlayer() {
        PlayerModel model = new PlayerModel();

        try {
            QueryBuilder<PlayerModel, String> builder = daoPlayers.queryBuilder();
            builder.where().eq("USER_PLAYER", true);
            model = daoPlayers.queryForFirst(builder.prepare());
        } catch (SQLException e) {
            Log.e(LOGTAG, "Error while obtaining player from BBDD", e);
        }

        return model;
    }

    @Override
    public PlayerModel executeTraining() {

        PlayerModel player = new PlayerModel();
        try {
            player = obtainPlayer();

            if (player.getTrainingFinishingDate() != null && player.getTrainingFinishingDate().before(new Date(System.currentTimeMillis()))) {
                player = updateSkillsByTrainingType(player);
                daoPlayers.update(player);
            }
        }
        catch (SQLException e){
            Log.e(LOGTAG, "Error while updating player", e);
        }
        return player;
    }

    @Override
    public PlayerModel createTraining(String skillName, long time) {
        PlayerModel player = new PlayerModel();
        try {
            player = obtainPlayer();
            player.setTrainingType(skillName);
            player.setTrainingFinishingDate(new Date(System.currentTimeMillis() + (time * 1000)));
            daoPlayers.update(player);
        }
        catch (SQLException e){
            Log.e(LOGTAG, "Error while updating player", e);
        }

        return player;
    }

    private PlayerModel updateSkillsByTrainingType(PlayerModel player) {

        switch (player.getTrainingType()){
            case PlayerModel.SKILL_STAMINA:
                player.setStamina(player.getStamina() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_JUMP:
                player.setJump(player.getJump() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_SPEED:
                player.setSpeed(player.getSpeed() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_STRENGTH:
                player.setStrength(player.getStrength() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_DRIBBLE:
                player.setDribble(player.getDribble() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_POST_PLAY:
                player.setPostPlay(player.getPostPlay() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_INT_SHOOT:
                player.setIntShoot(player.getIntShoot() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_EXT_SHOOT:
                player.setExtShoot(player.getExtShoot() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_OFFENSIVE_REBOUND:
                player.setOffensiveRebounding(player.getOffensiveRebounding() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_STEAL:
                player.setSteal(player.getSteal() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_BLOCK:
                player.setBlock(player.getBlock() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_INT_DEFENSE:
                player.setIntDefense(player.getIntDefense() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_EXT_DEFENSE:
                player.setExtDefense(player.getExtDefense() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_DEFENSIVE_REBOUND:
                player.setDefensiveRebounding(player.getDefensiveRebounding() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_MENTAL_TOUGHNESS:
                player.setMentalToughness(player.getMentalToughness() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_WORKETHIC:
                player.setWorkethic(player.getWorkethic() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_FRIENDLY:
                player.setFriendly(player.getFriendly() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            case PlayerModel.SKILL_POPULAR:
                player.setPopular(player.getPopular() + 1);
                player.setTrainingType("");
                player.setTrainingFinishingDate(null);
                break;
            default:
                break;

        }

        return player;
    }
}
