package com.armoz.roadtoalcatraz.calendar.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.base.view.decoration.TournamentDayDecoration;
import com.armoz.roadtoalcatraz.base.view.fragment.BaseFragment;
import com.armoz.roadtoalcatraz.calendar.view.controller.CalendarController;
import com.armoz.roadtoalcatraz.tournamentDetail.view.activity.TournamentDetailActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * A placeholder fragment containing a simple view.
 */
public class CalendarFragment extends BaseFragment implements CalendarController.View {

    private static final String TAG = "CalendarFragment";
    @Inject
    CalendarController controller;

    @Inject
    Bus bus;

    private View rootView;

    @Bind(R.id.calendarView)
    MaterialCalendarView calendarView;

    public CalendarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);
        controller.obtainTournaments();
    }


    @Override
    public boolean showError(ErrorEvent event) {
        createSnackbarError(rootView);
        return false;
    }


    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    public void onPause() {
        bus.unregister(this);
        super.onPause();
    }

    @Override
    public void onError() {
        createSnackbarError(rootView);
    }

    @Override
    public void onTournamentsLoaded() {

        List<TournamentModel> tournamentList = controller.getModel().getTournaments();
        List<CalendarDay> calendarDayList = new ArrayList<>();

        for (TournamentModel t : tournamentList) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(t.getDate());
            CalendarDay calendarDay = CalendarDay.from(cal);
            calendarDayList.add(calendarDay);
        }

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {

                List<TournamentModel> tournamentList = controller.getModel().getTournaments();

                Calendar cal = Calendar.getInstance();
                cal.setTime(date.getDate());

                for (TournamentModel t : tournamentList) {

                    Calendar tournamentCalendar = Calendar.getInstance();
                    tournamentCalendar.setTime(t.getDate());

                    if (cal.get(Calendar.YEAR) == tournamentCalendar.get(Calendar.YEAR) &&
                            cal.get(Calendar.DAY_OF_YEAR) == tournamentCalendar.get(Calendar.DAY_OF_YEAR)) {

                        Intent intent = TournamentDetailActivity.buildIntent(getContext(), t.getId());
                        startActivity(intent);
                    }

                }

            }
        });


        calendarView.addDecorator(new TournamentDayDecoration(ContextCompat.getColor(getContext(), R.color.colorAccent), calendarDayList));
    }
}
