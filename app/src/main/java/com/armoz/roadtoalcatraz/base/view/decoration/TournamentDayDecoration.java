package com.armoz.roadtoalcatraz.base.view.decoration;

import android.graphics.drawable.ColorDrawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by ruben.arana on 8/1/16.
 */
public class TournamentDayDecoration implements DayViewDecorator {

    private final int color;
    private final HashSet<CalendarDay> dates;

    public TournamentDayDecoration(int color, Collection<CalendarDay> dates) {
        this.color = color;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {

        view.setBackgroundDrawable(new ColorDrawable(color));
    }
}