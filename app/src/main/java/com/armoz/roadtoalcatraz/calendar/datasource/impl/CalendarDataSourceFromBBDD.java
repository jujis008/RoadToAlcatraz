package com.armoz.roadtoalcatraz.calendar.datasource.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.calendar.datasource.CalendarDataSource;
import com.armoz.roadtoalcatraz.calendar.domain.model.CalendarModel;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class CalendarDataSourceFromBBDD implements CalendarDataSource {


    private static final String LOGTAG = "CalendarDSFromBBDD";
    private final Dao<TournamentModel, String> daoTournaments;

    @Inject
    public CalendarDataSourceFromBBDD(Dao<TournamentModel, String> daoTournaments) {
        this.daoTournaments = daoTournaments;
    }

    @Override
    public CalendarModel obtainTournaments() {
        CalendarModel model = new CalendarModel();
        List<TournamentModel> tournaments = new ArrayList<>();

        try {
            QueryBuilder<TournamentModel, String> builder = daoTournaments.queryBuilder();
            builder.orderBy("DATE", false);  // true for ascending, false for descending
            builder.where().ge("DATE", new Date(System.currentTimeMillis()));
            tournaments = daoTournaments.query(builder.prepare());
        } catch (SQLException e) {
            Log.e(LOGTAG, "Error while obtaining messages from BBDD", e);
        }


        model.setTournaments(tournaments);
        return model;
    }
}
