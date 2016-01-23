package com.armoz.roadtoalcatraz.feed.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.decoration.DividerItemDecoration;
import com.armoz.roadtoalcatraz.base.view.fragment.BaseFragment;
import com.armoz.roadtoalcatraz.feed.domain.model.FeedModel;
import com.armoz.roadtoalcatraz.feed.view.adapter.FeedAdapter;
import com.armoz.roadtoalcatraz.feed.view.controller.FeedController;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedFragment extends BaseFragment implements FeedController.View {

    @Inject
    FeedController controller;

    @Inject
    Bus bus;

    @Bind(R.id.feed_recycler_view)
    RecyclerView recyclerView;

    private View rootView;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;


    public FeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);
        controller.getFeed();
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
    public void onFeedLoaded() {
        FeedModel model = controller.getModel();

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new FeedAdapter(model.getMessages());

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.item_divider));

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onError() {
        createSnackbarError(rootView);
    }


}
