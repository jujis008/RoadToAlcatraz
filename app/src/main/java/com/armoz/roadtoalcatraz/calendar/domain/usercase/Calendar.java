package com.armoz.roadtoalcatraz.calendar.domain.usercase;


import com.armoz.roadtoalcatraz.calendar.domain.callback.CalendarCallback;

public interface Calendar {

    void obtainTournaments(CalendarCallback calendarCallback);
}
