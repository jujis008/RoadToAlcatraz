package com.armoz.roadtoalcatraz.train.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.domain.events.ReloadEvent;
import com.armoz.roadtoalcatraz.base.view.fragment.BaseFragment;
import com.armoz.roadtoalcatraz.train.view.adapter.TrainDefensiveAdapter;
import com.armoz.roadtoalcatraz.train.view.controller.TrainController;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * A placeholder fragment containing a simple view.
 */
public class TrainDefensiveFragment extends BaseFragment implements TrainController.View {

    private static final String TAG = "TrainDefensiveFragment";
    @Inject
    TrainController controller;

    @Inject
    Bus bus;

    private View rootView;


    @Bind(R.id.train_defensive_recycler_view)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    public TrainDefensiveFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_train_defensive, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);
        controller.obtainPlayer();
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
    public void onPlayerLoaded() {

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TrainDefensiveAdapter(getContext(), controller.getModel(), controller);

        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.item_divider));

        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onReloadTraining() {
        Log.d(TAG, "Defensive reload");
        mAdapter = new TrainDefensiveAdapter(getContext(), controller.getModel(), controller);
        recyclerView.setAdapter(mAdapter);
    }

    @Subscribe
    public void onReloadEvent(ReloadEvent reloadEvent) {
        controller.reloadTraining();
    }
}
