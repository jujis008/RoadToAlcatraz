package com.armoz.roadtoalcatraz.newGame.view;

/**
 * Created by ruben.arana on 23/11/15.
 */

import com.armoz.roadtoalcatraz.newGame.domain.usercase.NewGame;
import com.armoz.roadtoalcatraz.newGame.view.activity.NewGameActivity;
import com.armoz.roadtoalcatraz.newGame.view.controller.NewGameController;
import com.armoz.roadtoalcatraz.newGame.view.fragment.NewGameFragment;
import com.armoz.roadtoalcatraz.tournament.domain.usercase.PrepareTournament;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module(injects = {NewGameActivity.class, NewGameFragment.class}, complete = false,
        library = true)
public class NewGameViewModule {

    @Provides
    public NewGameController provideNewGameController(NewGame newGame, PrepareTournament prepareTournament) {
        return new NewGameController(newGame, prepareTournament);
    }
}
