package com.armoz.roadtoalcatraz.calendar.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.activity.BaseDrawerActivity;
import com.armoz.roadtoalcatraz.calendar.view.fragment.CalendarFragment;


public class CalendarActivity extends BaseDrawerActivity {

    private CalendarFragment calendarFragment;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, CalendarActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.calendar_title));

        if (savedInstanceState == null) {
            calendarFragment = new CalendarFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, calendarFragment).commit();
        }
    }

    @Override
    protected boolean showError(ErrorEvent event) {
        if (calendarFragment != null) {
            return calendarFragment.showError(event);
        }
        return false;
    }


}
