package com.armoz.roadtoalcatraz.playGame.domain.usercase.impl;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.PlayModel;
import com.armoz.roadtoalcatraz.base.domain.model.PossessionModel;
import com.armoz.roadtoalcatraz.base.domain.model.StatsModel;
import com.armoz.roadtoalcatraz.playGame.domain.callback.PlayGameCallback;
import com.armoz.roadtoalcatraz.playGame.domain.usercase.PlayGame;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class PlayGameJob extends UserCaseJob implements PlayGame {


    private static final int ACTION_CHOOSE_INT = 1;
    private static final int ACTION_CHOOSE_EXT = 2;
    private static final int ACTION_STEAL_ATTEMPT = 3;
    private static final int ACTION_FORCE_SHOOT = 4;
    private static final int ACTION_DRIBBLING = 5;
    private static final int ACTION_POST = 6;
    private static final int ACTION_DRIVE = 7;
    private static final int ACTION_STEAL_SUCCESS = 8;
    private static final int ACTION_STEAL_MISSED_NO_ADVANTATGE = 9;
    private static final int ACTION_DEFENSIVE_FAULT = 10;
    private static final int ACTION_STEAL_MISSED_ADVANTATGE = 11;
    private static final int ACTION_EXT_SHOOT = 12;
    private static final int ACTION_EXT_SHOOT_NO_ADVANTATGE = 13;
    private static final int ACTION_EXT_SHOOT_ADVANTATGE = 14;
    private static final int ACTION_OFFENSIVE_FAULT = 15;
    private static final int ACTION_BLOCK_ATTEMPT = 16;
    private static final int ACTION_INT_SHOOT = 17;
    private static final int ACTION_INT_SHOOT_NO_ADVANTATGE = 18;
    private static final int ACTION_INT_SHOOT_ADVANTATGE = 19;
    private static final int ACTION_SHOOT_SUCCESS = 20;
    private static final int ACTION_SHOOT_MISS = 21;
    private static final int ACTION_BLOCK_SUCCESS = 22;
    private static final int ACTION_OFFENSIVE_REBOUND = 23;
    private static final int ACTION_DEFENSIVE_REBOUND = 24;
    private static final int ACTION_CHOOSE_INT_OR_EXT = 25;
    private static final int ACTION_BLOCK_MISSED = 26;
    private static final String TAG = "PlayGameJob";


    private GameModel game;

    //Possession variables
    List<PlayModel> plays = new ArrayList<>();
    private boolean possessionEnded = false;

    private PlayGameCallback callback;


    @Inject
    PlayGameJob(JobManager jobManager, MainThread mainThread,
                DomainErrorHandler domainErrorHandler) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
    }

    @Override
    public void playGame(PlayGameCallback callback, GameModel game) {
        jobManager.addJob(this);
        this.callback = callback;
        this.game = game;

    }

    @Override
    public void doRun() throws Throwable {
        try {
            playGame();
            onGamePlayed();
        } catch (Exception e) {
            notifyError();
        }
    }

    private GameModel playGame() {

        System.out.println("Game started");
        List<PossessionModel> possessions = new ArrayList<>();
        chooseFirstPossession();

        while (!game.isGameEnded()) {

            System.out.println("New possession (" + game.getPlayer1Stats().getPoints() + " - " + game.getPlayer2Stats().getPoints() + ")");

            possessions = game.getPossessions();
            PossessionModel possession = new PossessionModel();

            PlayModel play = new PlayModel();
            play.setPlayerId(game.getAttackingPlayer().getId());
            play.setAction(ACTION_CHOOSE_INT_OR_EXT);
            plays.add(play);

            while (!possessionEnded) {

                System.out.println("New play (" + plays.get(plays.size() - 1).getAction() + ")");

                switch (plays.get(plays.size() - 1).getAction()) {
                    case ACTION_CHOOSE_INT_OR_EXT:
                        onActionChooseIntOrExt();
                        break;
                    case ACTION_CHOOSE_EXT:
                        onActionChooseExt();
                        break;
                    case ACTION_CHOOSE_INT:
                        onActionChooseInt();
                        break;
                    case ACTION_FORCE_SHOOT:
                        onActionForceShoot();
                        break;
                    case ACTION_DRIBBLING:
                        onActionDribbling();
                        break;
                    case ACTION_POST:
                        onActionPost();
                        break;
                    case ACTION_DRIVE:
                        onActionDrive();
                        break;

                    case ACTION_DEFENSIVE_FAULT:
                        onActionDefensiveFault();
                        break;
                    case ACTION_OFFENSIVE_FAULT:
                        onActionOffensiveFault();
                        break;

                    case ACTION_EXT_SHOOT:
                        onActionExtShoot();
                        break;
                    case ACTION_EXT_SHOOT_NO_ADVANTATGE:
                        onActionExtShootNoAdvantatge();
                        break;
                    case ACTION_EXT_SHOOT_ADVANTATGE:
                        onActionExtShootAdvantatge();
                        break;

                    case ACTION_INT_SHOOT:
                        onActionIntShoot();
                        break;
                    case ACTION_INT_SHOOT_NO_ADVANTATGE:
                        onActionIntShootNoAdvantatge();
                        break;
                    case ACTION_INT_SHOOT_ADVANTATGE:
                        onActionIntShootAdvantatge();
                        break;

                    case ACTION_BLOCK_ATTEMPT:
                        onActionBlockAttempt();
                        break;
                    case ACTION_BLOCK_SUCCESS:
                        onActionBlockSuccess();
                        break;
                    case ACTION_BLOCK_MISSED:
                        onActionBlockMissed();
                        break;

                    case ACTION_SHOOT_SUCCESS:
                        onActionShootSuccess();
                        break;
                    case ACTION_SHOOT_MISS:
                        onActionShootMiss();
                        break;

                    case ACTION_STEAL_ATTEMPT:
                        onActionStealAttempt();
                        break;
                    case ACTION_STEAL_SUCCESS:
                        onActionStealSuccess();
                        break;
                    case ACTION_STEAL_MISSED_ADVANTATGE:
                        onActionStealMissedAdvantatge();
                        break;
                    case ACTION_STEAL_MISSED_NO_ADVANTATGE:
                        onActionStealMissedNoAdvantatge();
                        break;

                    case ACTION_OFFENSIVE_REBOUND:
                        onActionOffensiveRebound();
                        break;
                    case ACTION_DEFENSIVE_REBOUND:
                        onActionDefensiveRebound();
                        break;
                    default:
                        break;

                }

            }
            possession.setPlayList(plays);
            possessions.add(possession);
            possessionEnded = false;

        }

        game.setPossessions(possessions);

        return game;
    }

    private void onActionBlockMissed() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());
        double random = Math.random();
        if (random < 0.50) {
            play.setAction(ACTION_SHOOT_SUCCESS);
        } else {
            play.setAction((ACTION_SHOOT_MISS));
        }

        plays.add(play);
    }


    private void onActionOffensiveFault() {

        StatsModel stats = game.getAttackingPlayerStats();
        int faults = stats.getFaults() + 1;
        stats.setFaults(faults);
        game.setAttackingPlayerStats(stats);

        possessionEnded = true;
        game.changeAtackingPlayer();
    }

    private void onActionDefensiveFault() {

        StatsModel stats = game.getDefensivePlayerStats();
        int faults = stats.getFaults() + 1;
        stats.setFaults(faults);
        game.setDefensivePlayerStats(stats);

        possessionEnded = true;

    }

    private void onActionOffensiveRebound() {

        StatsModel stats = game.getAttackingPlayerStats();
        int offRebounds = stats.getOffensiveRebounds() + 1;
        stats.setOffensiveRebounds(offRebounds);
        game.setAttackingPlayerStats(stats);

        possessionEnded = true;
    }

    private void onActionDefensiveRebound() {

        StatsModel stats = game.getDefensivePlayerStats();
        int defRebounds = stats.getDefensiveRebounds() +1;
        stats.setDefensiveRebounds(defRebounds);
        game.setDefensivePlayerStats(stats);

        possessionEnded = true;
        game.changeAtackingPlayer();
    }

    private void onActionShootSuccess() {

        StatsModel stats = game.getAttackingPlayerStats();
        int twoShoots = stats.getTwoShoots() + 1;
        int twoShootsCompleted = stats.getTwoShootsCompleted() + 1;
        int points = stats.getPoints() + 2;

        stats.setTwoShoots(twoShoots);
        stats.setTwoShootsCompleted(twoShootsCompleted);
        stats.setPoints(points);

        game.setAttackingPlayerStats(stats);

        possessionEnded = true;
        game.changeAtackingPlayer();
    }

    private void onActionShootMiss() {

        StatsModel stats = game.getAttackingPlayerStats();
        int twoShoots = stats.getTwoShoots() + 1;
        stats.setTwoShoots(twoShoots);
        game.setAttackingPlayerStats(stats);


        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());
        double random = Math.random();
        if (random < 0.40) {
            play.setAction(ACTION_OFFENSIVE_REBOUND);
        } else if (random < 0.80) {
            play.setAction((ACTION_DEFENSIVE_REBOUND));
        } else if (random < 0.90) {
            play.setAction((ACTION_OFFENSIVE_FAULT));
        } else {
            play.setAction(ACTION_DEFENSIVE_FAULT);
        }
        plays.add(play);

    }

    private void onActionBlockSuccess() {

        StatsModel stats = game.getDefensivePlayerStats();
        int blocks = stats.getBlocks() + 1;
        stats.setBlocks(blocks);
        game.setDefensivePlayerStats(stats);

        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());
        double random = Math.random();
        if (random < 0.50) {
            play.setAction(ACTION_OFFENSIVE_REBOUND);
        } else {
            play.setAction((ACTION_DEFENSIVE_REBOUND));
        }
        plays.add(play);

    }

    private void onActionBlockAttempt() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());
        double random = Math.random();
        if (random < 0.50) {
            play.setAction(ACTION_BLOCK_MISSED);
        } else {
            play.setAction(ACTION_BLOCK_SUCCESS);
        }
        plays.add(play);

    }

    private void onActionIntShootNoAdvantatge() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());
        double random = Math.random();
        if (random < 0.30) {
            play.setAction(ACTION_SHOOT_SUCCESS);
        } else if (random < 0.85) {
            play.setAction((ACTION_SHOOT_MISS));
        } else {
            play.setAction(ACTION_BLOCK_ATTEMPT);
        }

        plays.add(play);
    }

    private void onActionIntShootAdvantatge() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());
        double random = Math.random();
        if (random < 0.70) {
            play.setAction(ACTION_SHOOT_SUCCESS);
        } else if (random < 0.95) {
            play.setAction((ACTION_SHOOT_MISS));
        } else {
            play.setAction(ACTION_BLOCK_ATTEMPT);
        }

        plays.add(play);
    }

    private void onActionIntShoot() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());
        double random = Math.random();
        if (random < 0.45) {
            play.setAction(ACTION_SHOOT_SUCCESS);
        } else if (random < 0.90) {
            play.setAction((ACTION_SHOOT_MISS));
        } else {
            play.setAction(ACTION_BLOCK_ATTEMPT);
        }

        plays.add(play);
    }

    private void onActionExtShootNoAdvantatge() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());
        double random = Math.random();
        if (random < 0.30) {
            play.setAction(ACTION_SHOOT_SUCCESS);
        } else if (random < 0.85) {
            play.setAction((ACTION_SHOOT_MISS));
        } else {
            play.setAction(ACTION_BLOCK_ATTEMPT);
        }

        plays.add(play);
    }

    private void onActionExtShootAdvantatge() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());
        double random = Math.random();
        if (random < 0.70) {
            play.setAction(ACTION_SHOOT_SUCCESS);
        } else if (random < 0.95) {
            play.setAction((ACTION_SHOOT_MISS));
        } else {
            play.setAction(ACTION_BLOCK_ATTEMPT);
        }

        plays.add(play);
    }

    private void onActionExtShoot() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());
        double random = Math.random();
        if (random < 0.45) {
            play.setAction(ACTION_SHOOT_SUCCESS);
        } else if (random < 0.90) {
            play.setAction((ACTION_SHOOT_MISS));
        } else {
            play.setAction(ACTION_BLOCK_ATTEMPT);
        }

        plays.add(play);
    }

    private void onActionStealMissedNoAdvantatge() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());

        int previousAction = plays.get(plays.size() - 2).getAction();
        if (previousAction == ACTION_CHOOSE_INT || previousAction == ACTION_POST || previousAction == ACTION_DRIVE) {
            play.setAction(ACTION_INT_SHOOT);
        } else {
            play.setAction(ACTION_EXT_SHOOT);
        }

        plays.add(play);
    }

    private void onActionStealMissedAdvantatge() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());

        int previousAction = plays.get(plays.size() - 2).getAction();
        if (previousAction == ACTION_CHOOSE_INT || previousAction == ACTION_POST || previousAction == ACTION_DRIVE) {
            play.setAction(ACTION_INT_SHOOT_ADVANTATGE);
        } else {
            play.setAction(ACTION_EXT_SHOOT_ADVANTATGE);
        }

        plays.add(play);
    }

    private void onActionDrive() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());

        double random = Math.random();

        if (random < 0.45) {
            play.setAction(ACTION_INT_SHOOT);
        } else if (random < 0.60) {
            play.setAction((ACTION_INT_SHOOT_NO_ADVANTATGE));
        } else if (random < 0.75) {
            play.setAction((ACTION_INT_SHOOT_ADVANTATGE));
        } else if (random < 0.90) {
            play.setAction((ACTION_DEFENSIVE_FAULT));
        } else if (random < 0.95) {
            play.setAction((ACTION_OFFENSIVE_FAULT));
        } else {
            play.setAction(ACTION_STEAL_ATTEMPT);
        }
        plays.add(play);

    }

    private void onActionPost() {

        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());

        double random = Math.random();

        if (random < 0.65) {
            play.setAction(ACTION_INT_SHOOT);
        } else if (random < 0.75) {
            play.setAction((ACTION_INT_SHOOT_NO_ADVANTATGE));
        } else if (random < 0.85) {
            play.setAction((ACTION_INT_SHOOT_ADVANTATGE));
        } else if (random < 0.93) {
            play.setAction((ACTION_DEFENSIVE_FAULT));
        } else if (random < 0.95) {
            play.setAction((ACTION_OFFENSIVE_FAULT));
        } else {
            play.setAction(ACTION_STEAL_ATTEMPT);
        }

        plays.add(play);

    }

    private void onActionDribbling() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());

        double random = Math.random();

        if (random < 0.55) {
            play.setAction(ACTION_EXT_SHOOT);
        } else if (random < 0.65) {
            play.setAction((ACTION_EXT_SHOOT_NO_ADVANTATGE));
        } else if (random < 0.75) {
            play.setAction((ACTION_EXT_SHOOT_ADVANTATGE));
        } else if (random < 0.85) {
            play.setAction((ACTION_DEFENSIVE_FAULT));
        } else if (random < 0.90) {
            play.setAction((ACTION_OFFENSIVE_FAULT));
        } else {
            play.setAction(ACTION_STEAL_ATTEMPT);
        }

        plays.add(play);
    }


    private void onActionForceShoot() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());

        double random = Math.random();

        if (random < 0.60) {
            play.setAction(ACTION_EXT_SHOOT);
        } else if (random < 0.75) {
            play.setAction((ACTION_EXT_SHOOT_NO_ADVANTATGE));
        } else if (random < 0.85) {
            play.setAction((ACTION_EXT_SHOOT_ADVANTATGE));
        } else if (random < 0.93) {
            play.setAction((ACTION_DEFENSIVE_FAULT));
        } else if (random < 0.95) {
            play.setAction((ACTION_OFFENSIVE_FAULT));
        } else {
            play.setAction(ACTION_STEAL_ATTEMPT);
        }

        plays.add(play);
    }

    private void onActionStealSuccess() {

        StatsModel stats = game.getDefensivePlayerStats();
        int steals = stats.getSteals() + 1;
        stats.setSteals(steals);
        game.setDefensivePlayerStats(stats);

        StatsModel statsAtt = game.getAttackingPlayerStats();
        int turnovers = statsAtt.getTurnovers()+1;
        statsAtt.setTurnovers(turnovers);
        game.setAttackingPlayerStats(statsAtt);

        possessionEnded = true;
        game.changeAtackingPlayer();
    }

    private void onActionStealAttempt() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());

        double random = Math.random();

        if (random < 0.25) {
            play.setAction(ACTION_STEAL_SUCCESS);
        } else if (random < 0.50) {
            play.setAction((ACTION_STEAL_MISSED_NO_ADVANTATGE));
        } else if (random < 0.75) {
            play.setAction((ACTION_DEFENSIVE_FAULT));
        } else {
            play.setAction(ACTION_STEAL_MISSED_ADVANTATGE);
        }

        plays.add(play);
    }

    private void onActionChooseInt() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());

        double random = Math.random();

        if (random < 0.48) {
            play.setAction(ACTION_POST);
        } else if (random < 0.96) {
            play.setAction(ACTION_DRIVE);
        } else {
            play.setAction(ACTION_STEAL_ATTEMPT);
        }

        plays.add(play);
    }

    private void onActionChooseExt() {
        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());

        double random = Math.random();

        if (random < 0.48) {
            play.setAction(ACTION_FORCE_SHOOT);
        } else if (random < 0.96) {
            play.setAction(ACTION_DRIBBLING);
        } else {
            play.setAction(ACTION_STEAL_ATTEMPT);
        }

        plays.add(play);
    }

    private void chooseFirstPossession() {
        if (Math.random() < 0.5) {
            game.getPlayer1().setAtacking(true);
        }
    }

    private void onActionChooseIntOrExt() {

        PlayModel play = new PlayModel();
        play.setPlayerId(game.getAttackingPlayer().getId());

        double random = Math.random();

        if (random < 0.50) {
            play.setAction(ACTION_CHOOSE_INT);
        } else {
            play.setAction(ACTION_CHOOSE_EXT);
        }

        plays.add(play);

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

    private void onGamePlayed() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onGameFinished(game);
            }
        });
    }


}
