package com.armoz.roadtoalcatraz.tournament.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.activity.BaseDrawerActivity;
import com.armoz.roadtoalcatraz.tournament.view.fragment.TournamentFragment;


public class TournamentActivity extends BaseDrawerActivity {

    public static final String EXTRA_TOURNAMENT_ID = "tournamentId";

    private TournamentFragment tournamentFragment;

    public static Intent buildIntent(Context context, long tournamentId) {
        Intent intent = new Intent(context, TournamentActivity.class);
        intent.putExtra(EXTRA_TOURNAMENT_ID, tournamentId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.tournament_title));

        if (savedInstanceState == null) {
            tournamentFragment = new TournamentFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, tournamentFragment).commit();
        }
    }

    @Override
    protected boolean showError(ErrorEvent event) {
        if (tournamentFragment != null) {
            return tournamentFragment.showError(event);
        }
        return false;
    }
}
