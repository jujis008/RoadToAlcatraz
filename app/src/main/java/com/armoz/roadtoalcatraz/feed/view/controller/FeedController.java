package com.armoz.roadtoalcatraz.feed.view.controller;

import com.armoz.roadtoalcatraz.feed.domain.callback.FeedCallback;
import com.armoz.roadtoalcatraz.feed.domain.model.FeedModel;
import com.armoz.roadtoalcatraz.feed.domain.usercase.Feed;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class FeedController {

    private View view;

    public void setView(View view) {
        this.view = view;
    }

    private Feed feedJob;

    private FeedModel model = new FeedModel();

    @Inject
    public FeedController(Feed feedJob) {
        this.feedJob = feedJob;
    }

    private FeedCallback feedCallback = new FeedCallback() {
        @Override
        public void onFeedLoaded(FeedModel feedModel) {
            model = feedModel;
            view.onFeedLoaded();
        }

        @Override
        public void onError() {
            view.onError();
        }

    };

    public void getFeed() {
        feedJob.getFeed(feedCallback);
    }

    public FeedModel getModel(){
        return model;
    }

    public interface View {

        public void onFeedLoaded();

        public void onError();

    }

}
