package com.armoz.roadtoalcatraz.feed.domain.usercase;


import com.armoz.roadtoalcatraz.feed.domain.callback.FeedCallback;

public interface Feed {

    void getFeed(FeedCallback feedCallback);

}
