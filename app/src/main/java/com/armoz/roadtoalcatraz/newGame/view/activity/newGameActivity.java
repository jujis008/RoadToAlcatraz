package com.armoz.roadtoalcatraz.newGame.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.activity.BaseActivity;
import com.armoz.roadtoalcatraz.newGame.view.fragment.NewGameFragment;


public class NewGameActivity extends BaseActivity {

    private NewGameFragment newGameFragment;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, NewGameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            newGameFragment = new NewGameFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, newGameFragment).commit();
        }
    }

    @Override
    protected boolean showError(ErrorEvent event) {
        if (newGameFragment != null) {
            return newGameFragment.showError(event);
        }
        return false;
    }
}
