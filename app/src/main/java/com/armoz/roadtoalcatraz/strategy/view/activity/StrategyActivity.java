package com.armoz.roadtoalcatraz.strategy.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.activity.BaseTabbedActivity;
import com.armoz.roadtoalcatraz.strategy.view.fragment.StrategyDefensiveFragment;
import com.armoz.roadtoalcatraz.strategy.view.fragment.StrategyGeneralFragment;
import com.armoz.roadtoalcatraz.strategy.view.fragment.StrategyOffensiveFragment;


public class StrategyActivity extends BaseTabbedActivity {

    private StrategyGeneralFragment strategyGeneralFragment;
    private StrategyOffensiveFragment strategyOffensiveFragment;
    private StrategyDefensiveFragment strategyDefensiveFragment;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, StrategyActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.strategy_title));

        strategyGeneralFragment = new StrategyGeneralFragment();
        strategyOffensiveFragment = new StrategyOffensiveFragment();
        strategyDefensiveFragment = new StrategyDefensiveFragment();
    }

    @Override
    protected int getTabsNumber() {
        return 3;
    }

    @Override
    protected Fragment getTab(int position) {

        switch (position){
            case 0:
                return strategyGeneralFragment;
            case 1:
                return strategyOffensiveFragment;
            case 2:
                return strategyDefensiveFragment;

        }
        return null;
    }

    @Override
    protected CharSequence getTabTitle(int position) {

        String tabTitle = "";
        switch (position){
            case 0:
                tabTitle = getString(R.string.strategy_general_title);
                break;
            case 1:
                tabTitle = getString(R.string.strategy_defensive_title);
                break;
            case 2:
                tabTitle = getString(R.string.strategy_defensive_title);
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
