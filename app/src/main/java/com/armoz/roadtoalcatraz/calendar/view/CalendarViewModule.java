package com.armoz.roadtoalcatraz.calendar.view;

/**
 * Created by ruben.arana on 23/11/15.
 */

import com.armoz.roadtoalcatraz.calendar.domain.usercase.Calendar;
import com.armoz.roadtoalcatraz.calendar.view.activity.CalendarActivity;
import com.armoz.roadtoalcatraz.calendar.view.controller.CalendarController;
import com.armoz.roadtoalcatraz.calendar.view.fragment.CalendarFragment;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module(injects = {CalendarActivity.class, CalendarFragment.class}, complete = false,
        library = true)
public class CalendarViewModule {

    @Provides
    public CalendarController provideCalendarController(Calendar calendarJob) {
        return new CalendarController(calendarJob);
    }
}
