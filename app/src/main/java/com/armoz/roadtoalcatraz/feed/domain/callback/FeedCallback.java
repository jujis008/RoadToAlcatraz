package com.armoz.roadtoalcatraz.feed.domain.callback;


import com.armoz.roadtoalcatraz.feed.domain.model.FeedModel;

/**
 *
 */
public interface FeedCallback {

    void onFeedLoaded(FeedModel feedModel);

    void onError();
}
