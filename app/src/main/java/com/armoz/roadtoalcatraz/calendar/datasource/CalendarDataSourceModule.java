package com.armoz.roadtoalcatraz.calendar.datasource;

import com.armoz.roadtoalcatraz.calendar.datasource.impl.CalendarDataSourceFromBBDD;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruben.arana on 23/11/15.
 */
@Module(complete = false, library = true)
public class CalendarDataSourceModule {

    @Provides
    public CalendarDataSource providesCalendarDataSource(CalendarDataSourceFromBBDD calendarDataSourceFromBBDD) {
        return calendarDataSourceFromBBDD;
    }
}
