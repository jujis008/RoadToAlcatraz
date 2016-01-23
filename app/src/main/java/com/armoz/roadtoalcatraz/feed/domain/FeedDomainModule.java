package com.armoz.roadtoalcatraz.feed.domain;

import com.armoz.roadtoalcatraz.feed.domain.usercase.Feed;
import com.armoz.roadtoalcatraz.feed.domain.usercase.impl.FeedJob;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class FeedDomainModule {

    @Provides
    public Feed provideFeed(FeedJob feedJob) {
        return feedJob;
    }

}
