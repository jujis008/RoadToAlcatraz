package com.armoz.roadtoalcatraz.feed.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.activity.BaseDrawerActivity;
import com.armoz.roadtoalcatraz.feed.view.fragment.FeedFragment;


public class FeedActivity extends BaseDrawerActivity {

    private FeedFragment feedFragment;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, FeedActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.feed_title));

        if (savedInstanceState == null) {
            feedFragment = new FeedFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, feedFragment).commit();
        }
    }

    @Override
    protected boolean showError(ErrorEvent event) {
        if (feedFragment != null) {
            return feedFragment.showError(event);
        }
        return false;
    }
}
