package com.armoz.roadtoalcatraz.tournament.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.armoz.roadtoalcatraz.base.view.decoration.DividerItemDecoration;
import com.armoz.roadtoalcatraz.base.view.fragment.BaseFragment;
import com.armoz.roadtoalcatraz.tournament.view.activity.TournamentActivity;
import com.armoz.roadtoalcatraz.tournament.view.adapter.TournamentAdapter;
import com.armoz.roadtoalcatraz.tournament.view.controller.TournamentController;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * A placeholder fragment containing a simple view.
 */
public class TournamentFragment extends BaseFragment implements TournamentController.View {

    private static final String TAG = "TournamentFragment";
    @Inject
    TournamentController controller;

    @Inject
    Bus bus;

    @Bind(R.id.tv_tournament_name)
    TextView tv_tournament_name;

    @Bind(R.id.tv_tournament_type)
    TextView tv_tournament_type;

    @Bind(R.id.tournament_recycler_view)
    RecyclerView recyclerView;

    private View rootView;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    private long tournamentId;


    public TournamentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_tournament, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);
        tournamentId = getActivity().getIntent().getLongExtra(TournamentActivity.EXTRA_TOURNAMENT_ID, -1);
        controller.obtainTournamentInfo(tournamentId);
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
    public void onTournamentLoaded() {

        TournamentModel model = controller.getModel();

        tv_tournament_name.setText(model.getName());
        tv_tournament_type.setText("LEVEL: " + model.getLevel());

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        Log.d(TAG, "GAMES: " + model.getGames().size());
        // specify an adapter (see also next example)
        mAdapter = new TournamentAdapter(model.getGames());

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.item_divider));

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onError() {
        createSnackbarError(rootView);
    }


}
