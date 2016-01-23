package com.armoz.roadtoalcatraz.tournament.view.controller;

import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.tournament.domain.callback.TournamentCallback;
import com.armoz.roadtoalcatraz.tournament.domain.usercase.Tournament;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class TournamentController {

    private View view;

    public void setView(View view) {
        this.view = view;
    }

    private Tournament tournamentJob;

    private TournamentModel model = new TournamentModel();

    @Inject
    public TournamentController(Tournament tournamentJob) {
        this.tournamentJob = tournamentJob;
    }

    private TournamentCallback tournamentCallback = new TournamentCallback() {
        @Override
        public void onTournamentLoaded(TournamentModel tournamentModel) {
            model = tournamentModel;
            view.onTournamentLoaded();
        }

        @Override
        public void onError() {
            view.onError();
        }

    };

    public void obtainTournamentInfo(long tournamentID) {
        tournamentJob.obtainTournamentInfo(tournamentCallback, tournamentID);
    }

    public TournamentModel getModel(){
        return model;
    }

    public interface View {

        public void onTournamentLoaded();

        public void onError();

    }

}
