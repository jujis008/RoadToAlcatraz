package com.armoz.roadtoalcatraz.tournamentDetail.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.activity.BaseDrawerActivity;
import com.armoz.roadtoalcatraz.tournamentDetail.view.fragment.TournamentDetailFragment;


public class TournamentDetailActivity extends BaseDrawerActivity {

    public static final String EXTRA_TOURNAMENT_ID = "tournamentId";

    private TournamentDetailFragment tournamentDetailFragment;

    public static Intent buildIntent(Context context, int tournamentId) {
        Intent intent = new Intent(context, TournamentDetailActivity.class);
        intent.putExtra(EXTRA_TOURNAMENT_ID, tournamentId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.tournament_detail_title));

        if (savedInstanceState == null) {
            tournamentDetailFragment = new TournamentDetailFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, tournamentDetailFragment).commit();
        }
    }

    @Override
    protected boolean showError(ErrorEvent event) {
        if (tournamentDetailFragment != null) {
            return tournamentDetailFragment.showError(event);
        }
        return false;
    }
}
