package com.armoz.roadtoalcatraz.calendar.view.controller;

import com.armoz.roadtoalcatraz.calendar.domain.callback.CalendarCallback;
import com.armoz.roadtoalcatraz.calendar.domain.model.CalendarModel;
import com.armoz.roadtoalcatraz.calendar.domain.usercase.Calendar;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class CalendarController {

    private View view;

    public void setView(View view) {
        this.view = view;
    }

    private Calendar calendarJob;

    private CalendarModel model = new CalendarModel();


    @Inject
    public CalendarController(Calendar calendarJob) {
        this.calendarJob = calendarJob;

    }

    private CalendarCallback calendarCallback = new CalendarCallback() {
        @Override
        public void onTournamentsLoaded(CalendarModel calendarModel) {
            model = calendarModel;
            view.onTournamentsLoaded();
        }

        @Override
        public void onError() {
            view.onError();
        }

    };


    public CalendarModel getModel() {
        return model;
    }

    public void obtainTournaments() {
        calendarJob.obtainTournaments(calendarCallback);
    }

    public interface View {

        public void onError();

        public void onTournamentsLoaded();

    }

}
