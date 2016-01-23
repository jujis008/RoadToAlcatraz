package com.armoz.roadtoalcatraz.train.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.activity.BaseTabbedActivity;
import com.armoz.roadtoalcatraz.train.view.fragment.TrainDefensiveFragment;
import com.armoz.roadtoalcatraz.train.view.fragment.TrainMentalFragment;
import com.armoz.roadtoalcatraz.train.view.fragment.TrainOffensiveFragment;
import com.armoz.roadtoalcatraz.train.view.fragment.TrainPhysicalFragment;


public class TrainActivity extends BaseTabbedActivity {

    private TrainOffensiveFragment trainOffensiveFragment;
    private TrainDefensiveFragment trainDefensiveFragment;
    private TrainPhysicalFragment trainPhysicalFragment;
    private TrainMentalFragment trainMentalFragment;


    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, TrainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.training_title));

        trainOffensiveFragment = new TrainOffensiveFragment();
        trainDefensiveFragment = new TrainDefensiveFragment();
        trainPhysicalFragment = new TrainPhysicalFragment();
        trainMentalFragment = new TrainMentalFragment();

    }

    @Override
    protected int getTabsNumber() {
        return 4;
    }

    @Override
    protected Fragment getTab(int position) {

        switch (position){
            case 0:
                return trainOffensiveFragment;
            case 1:
                return trainDefensiveFragment;
            case 2:
                return trainPhysicalFragment;
            case 3:
                return trainMentalFragment;
        }
        return null;
    }

    @Override
    protected CharSequence getTabTitle(int position) {

        String tabTitle = "";
        switch (position){
            case 0:
                tabTitle = getString(R.string.training_offensive_title);
                break;
            case 1:
                tabTitle = getString(R.string.training_defensive_title);
                break;
            case 2:
                tabTitle = getString(R.string.training_physical_title);
                break;
            case 3:
                tabTitle = getString(R.string.training_mental_title);
                break;
        }

        return tabTitle;
    }

    @Override
    protected boolean showError(ErrorEvent event) {
       /* if (trainFragment != null) {
            return trainFragment.showError(event);
        }*/
        return false;
    }

}
