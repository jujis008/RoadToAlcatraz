package com.armoz.roadtoalcatraz.welcome.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.activity.BaseActivity;
import com.armoz.roadtoalcatraz.welcome.view.fragment.WelcomeFragment;


public class WelcomeActivity extends BaseActivity {

    private WelcomeFragment welcomeFragment;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            welcomeFragment = new WelcomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, welcomeFragment).commit();
        }
    }

    @Override
    protected boolean showError(ErrorEvent event) {
        if (welcomeFragment != null) {
            return welcomeFragment.showError(event);
        }
        return false;
    }
}
