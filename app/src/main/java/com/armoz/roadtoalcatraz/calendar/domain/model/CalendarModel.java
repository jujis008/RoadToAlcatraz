package com.armoz.roadtoalcatraz.calendar.domain.model;

import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;

import java.util.List;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class CalendarModel {

    private List<TournamentModel> tournaments;

    public List<TournamentModel> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<TournamentModel> tournaments) {
        this.tournaments = tournaments;
    }
}
