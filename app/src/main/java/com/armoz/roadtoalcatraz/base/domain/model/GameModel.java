package com.armoz.roadtoalcatraz.base.domain.model;

import com.armoz.roadtoalcatraz.playGame.domain.usercase.PlayGame;
import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class GameModel {

    @DatabaseField(generatedId = true, columnName = "ID")
    private long id;

    @DatabaseField(columnName = "TOURNAMENT_ID")
    private long tournamentID;

    @DatabaseField(columnName = "PLAYER_1_ID")
    private long player1Id;

    @DatabaseField(columnName = "PLAYER_2_ID")
    private long player2Id;

    @DatabaseField(columnName = "WINNER_ID")
    private long winnerId;

    @DatabaseField(columnName = "DATE")
    private Date date;

    @DatabaseField(columnName = "ROUND")
    private int round;

    private PlayerModel player1;

    private PlayerModel player2;

    private StrategyModel strategy1;

    private StrategyModel strategy2;

    private List<PossessionModel> possessions = new ArrayList<>();

    private StatsModel player1Stats = new StatsModel();

    private StatsModel player2Stats = new StatsModel();


    public boolean isGameEnded(){

        if (player1Stats.getPoints() >= PlayGame.MIN_VICTORY_POINTS
                && player1Stats.getPoints() - player2Stats.getPoints() >= PlayGame.MIN_VICTORY_DIFF
                || player2Stats.getPoints() >= PlayGame.MIN_VICTORY_POINTS && player2Stats.getPoints() - player1Stats.getPoints() >= PlayGame.MIN_VICTORY_DIFF) {
            return true;
        }
        else{
            return false;
        }

    }

    public PlayerModel getAttackingPlayer(){
        if (player1.isAtacking()){
            return player1;
        }
        else {
            return player2;
        }
    }

    public PlayerModel getDefensivePlayer(){
        if (player1.isAtacking()){
            return player2;
        }
        else {
            return player1;
        }
    }

    public StatsModel getAttackingPlayerStats(){
        if (player1.isAtacking()){
            return player1Stats;
        }
        else {
            return player2Stats;
        }
    }

    public StatsModel getDefensivePlayerStats(){
        if (player1.isAtacking()){
            return player2Stats;
        }
        else {
            return player1Stats;
        }
    }

    public void setAttackingPlayerStats(StatsModel stats) {
        if (player1.isAtacking()){
            player1Stats = stats;
        }
        else{
            player2Stats = stats;
        }
    }

    public void setDefensivePlayerStats(StatsModel stats) {
        if (player1.isAtacking()){
            player2Stats = stats;
        }
        else{
            player1Stats = stats;
        }
    }


    public void changeAtackingPlayer() {
        if (player1.isAtacking()){
            player1.setAtacking(false);
            player2.setAtacking(true);
        }
        else{
            player2.setAtacking(false);
            player1.setAtacking(true);
        }
    }

    public PlayerModel getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerModel player1) {
        this.player1 = player1;
    }

    public PlayerModel getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerModel player2) {
        this.player2 = player2;
    }

    public StrategyModel getStrategy1() {
        return strategy1;
    }

    public void setStrategy1(StrategyModel strategy1) {
        this.strategy1 = strategy1;
    }

    public StrategyModel getStrategy2() {
        return strategy2;
    }

    public void setStrategy2(StrategyModel strategy2) {
        this.strategy2 = strategy2;
    }

    public List<PossessionModel> getPossessions() {
        return possessions;
    }

    public void setPossessions(List<PossessionModel> possessions) {
        this.possessions = possessions;
    }

    public StatsModel getPlayer1Stats() {
        return player1Stats;
    }

    public void setPlayer1Stats(StatsModel player1Stats) {
        this.player1Stats = player1Stats;
    }

    public StatsModel getPlayer2Stats() {
        return player2Stats;
    }

    public void setPlayer2Stats(StatsModel player2Stats) {
        this.player2Stats = player2Stats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(long tournamentID) {
        this.tournamentID = tournamentID;
    }

    public long getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(long player1Id) {
        this.player1Id = player1Id;
    }

    public long getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(long player2Id) {
        this.player2Id = player2Id;
    }

    public long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(long winnerId) {
        this.winnerId = winnerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
