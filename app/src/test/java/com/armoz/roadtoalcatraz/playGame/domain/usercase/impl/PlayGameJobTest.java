package com.armoz.roadtoalcatraz.playGame.domain.usercase.impl;

import com.armoz.roadtoalcatraz.AbstractJob;
import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.playGame.domain.callback.PlayGameCallback;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayGameJobTest extends AbstractJob {

    @InjectMocks
    PlayGameJob job;

    @Captor
    ArgumentCaptor<GameModel> gameCaptor;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void shouldPlayGame() throws Throwable {

        //Given
        //when(XXXXX).thenReturn(anyXXXX());

        PlayGameCallback callback = mock(PlayGameCallback.class);

        //When
        job.playGame(callback, anyGame());
        job.doRun();

        //Then
        verify(callback).onGameFinished(gameCaptor.capture());
        GameModel game = gameCaptor.getValue();

        Assert.assertEquals(game.getPlayer1Stats().getTwoShootsCompleted() * 2, game.getPlayer1Stats().getPoints());
        Assert.assertEquals(game.getPlayer1Stats().getSteals(), game.getPlayer2Stats().getTurnovers());

    }

    private GameModel anyGame() {

        GameModel game = new GameModel();
        game.setPlayer1(anyPlayer());
        game.setPlayer2(anyPlayer());
        game.setStrategy1(anyStrategy());
        game.setStrategy2(anyStrategy());

        return game;
    }

    private StrategyModel anyStrategy() {
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

        return strategy;
    }

    private PlayerModel anyPlayer() {
        PlayerModel player = new PlayerModel();
        player.setName("Ruben");
        player.setSurname("Arana");
        player.setAge(19);
        player.setBlock(50);
        player.setCountry("Spain");
        player.setDefensiveRebounding(50);
        player.setDribble(50);
        player.setExtDefense(50);
        player.setExtShoot(50);
        player.setFriendly(50);
        player.setHeight(181);
        player.setWeight(80);
        player.setId(1);
        player.setIntDefense(50);
        player.setIntShoot(50);
        player.setJump(50);
        player.setMentalToughness(50);
        player.setOffensiveRebounding(50);
        player.setPopular(50);
        player.setPostPlay(50);
        player.setStamina(50);
        player.setSpeed(50);
        player.setSteal(50);
        player.setStrength(50);
        player.setWorkethic(50);
        player.setYearsPlayed(0);


        return player;
    }

}