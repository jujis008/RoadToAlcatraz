package com.armoz.roadtoalcatraz.tournamentDetail.view.controller;

import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.tournamentDetail.domain.callback.TournamentDetailCallback;
import com.armoz.roadtoalcatraz.tournamentDetail.domain.usercase.TournamentDetail;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class TournamentDetailController {

    private View view;

    public void setView(View view) {
        this.view = view;
    }

    private TournamentDetail tournamentDetailJob;

    private TournamentModel model = new TournamentModel();

    @Inject
    public TournamentDetailController(TournamentDetail tournamentDetailJob) {
        this.tournamentDetailJob = tournamentDetailJob;
    }

    private TournamentDetailCallback tournamentCallback = new TournamentDetailCallback() {
        @Override
        public void onTournamentDetailLoaded(TournamentModel tournamentModel) {
            model = tournamentModel;
            view.onTournamentLoaded();
        }

        @Override
        public void onError() {
            view.onError();
        }

    };

    public void obtainTournament(int tournamentID) {
        tournamentDetailJob.obtainTournament(tournamentCallback, tournamentID);
    }

    public TournamentModel getModel(){
        return model;
    }

    public interface View {

        public void onTournamentLoaded();

        public void onError();

    }

}
