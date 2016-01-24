package com.armoz.roadtoalcatraz.strategy.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.fragment.BaseFragment;
import com.armoz.roadtoalcatraz.strategy.view.adapter.StrategyOffensiveAdapter;
import com.armoz.roadtoalcatraz.strategy.view.controller.StrategyController;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * A placeholder fragment containing a simple view.
 */
public class StrategyOffensiveFragment extends BaseFragment implements StrategyController.View {

    private static final String TAG = "StrategyOffensiveFragment";

    @Inject
    StrategyController controller;

    @Inject
    Bus bus;

    private View rootView;

    @Bind(R.id.strategy_offensive_recycler_view)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    public StrategyOffensiveFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_strategy_offensive, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);
        controller.obtainStrategy();
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
    public void onStrategyLoaded() {

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new StrategyOffensiveAdapter(controller.getStrategyModel(), controller);

        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.item_divider));

        recyclerView.setAdapter(mAdapter);

    }

}
