package com.armoz.roadtoalcatraz.calendar.domain.callback;


import com.armoz.roadtoalcatraz.calendar.domain.model.CalendarModel;

/**
 *
 */
public interface CalendarCallback {

    void onTournamentsLoaded(CalendarModel calendarModel);

    void onError();
}
