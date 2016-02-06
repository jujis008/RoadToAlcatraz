package com.armoz.roadtoalcatraz.tournamentDetail.view.fragment;

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
import com.armoz.roadtoalcatraz.tournamentDetail.view.activity.TournamentDetailActivity;
import com.armoz.roadtoalcatraz.tournamentDetail.view.adapter.TournamentDetailAdapter;
import com.armoz.roadtoalcatraz.tournamentDetail.view.controller.TournamentDetailController;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * A placeholder fragment containing a simple view.
 */
public class TournamentDetailFragment extends BaseFragment implements TournamentDetailController.View {

    private static final String TAG = "TournamentDetailFragm";
    @Inject
    TournamentDetailController controller;

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

    private int tournamentId;


    public TournamentDetailFragment() {
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
        tournamentId = getActivity().getIntent().getIntExtra(TournamentDetailActivity.EXTRA_TOURNAMENT_ID, -1);
        controller.obtainTournament(tournamentId);
        Log.d(TAG, "TournamentID: " + tournamentId);
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
        mAdapter = new TournamentDetailAdapter(model.getGames());

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.item_divider));

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onError() {
        createSnackbarError(rootView);
    }


}
