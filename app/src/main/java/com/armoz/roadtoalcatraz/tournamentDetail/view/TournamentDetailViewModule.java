package com.armoz.roadtoalcatraz.tournamentDetail.view;

/**
 * Created by ruben.arana on 23/11/15.
 */

import com.armoz.roadtoalcatraz.tournamentDetail.domain.usercase.TournamentDetail;
import com.armoz.roadtoalcatraz.tournamentDetail.view.activity.TournamentDetailActivity;
import com.armoz.roadtoalcatraz.tournamentDetail.view.controller.TournamentDetailController;
import com.armoz.roadtoalcatraz.tournamentDetail.view.fragment.TournamentDetailFragment;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module(injects = {TournamentDetailActivity.class, TournamentDetailFragment.class}, complete = false,
        library = true)
public class TournamentDetailViewModule {

    @Provides
    public TournamentDetailController provideTournamentDetailController(TournamentDetail tournamentDetailJob) {
        return new TournamentDetailController(tournamentDetailJob);
    }
}
