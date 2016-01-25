package com.armoz.roadtoalcatraz.welcome.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.fragment.BaseFragment;
import com.armoz.roadtoalcatraz.feed.view.activity.FeedActivity;
import com.armoz.roadtoalcatraz.welcome.view.controller.WelcomeController;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class WelcomeFragment extends BaseFragment implements WelcomeController.View {

    @Inject
    WelcomeController controller;

    @Inject
    Bus bus;

    private View rootView;

    public WelcomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);


        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        boolean newGameCreated = sharedPref.getBoolean(getString(R.string.shared_preference_new_game_created), false);

        if (newGameCreated){
            Intent intent = FeedActivity.buildIntent(getContext());
            startActivity(intent);
        }
        else{
            controller.createNewGame(getContext());
        }

        //start loading...
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
    public void onNewGameCreated() {
        //end loading

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.shared_preference_new_game_created), true);
        editor.commit();

        Intent intent = FeedActivity.buildIntent(getContext());
        startActivity(intent);
    }

    @Override
    public void onError() {
        createSnackbarError(rootView);
    }


}
