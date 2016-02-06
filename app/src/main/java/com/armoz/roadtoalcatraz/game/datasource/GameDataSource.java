package com.armoz.roadtoalcatraz.game.datasource;

import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;

import java.util.List;

/**
 * Created by ruben.arana on 23/11/15.
 */
public interface GameDataSource {

    List<GameModel> createTournamentGames(TournamentModel tournamentModel);

    void updateGame(GameModel game);

    List<GameModel> obtainTournamentGames(int tournamentId);
}
