package com.armoz.roadtoalcatraz.calendar.domain;

import com.armoz.roadtoalcatraz.calendar.domain.usercase.Calendar;
import com.armoz.roadtoalcatraz.calendar.domain.usercase.impl.CalendarJob;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class CalendarDomainModule {

    @Provides
    public Calendar provideCalendar(CalendarJob calendarJob) {
        return calendarJob;
    }

}
