package com.armoz.roadtoalcatraz.tournament.view;

/**
 * Created by ruben.arana on 23/11/15.
 */

import com.armoz.roadtoalcatraz.tournament.domain.usercase.Tournament;
import com.armoz.roadtoalcatraz.tournament.view.activity.TournamentActivity;
import com.armoz.roadtoalcatraz.tournament.view.controller.TournamentController;
import com.armoz.roadtoalcatraz.tournament.view.fragment.TournamentFragment;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module(injects = {TournamentActivity.class, TournamentFragment.class}, complete = false,
        library = true)
public class TournamentViewModule {

    @Provides
    public TournamentController provideTournamentController(Tournament tournamentJob) {
        return new TournamentController(tournamentJob);
    }
}
