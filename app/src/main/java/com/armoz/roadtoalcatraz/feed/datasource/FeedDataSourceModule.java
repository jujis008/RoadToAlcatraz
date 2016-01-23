package com.armoz.roadtoalcatraz.feed.datasource;

import com.armoz.roadtoalcatraz.feed.datasource.impl.FeedDataSourceFromBBDD;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruben.arana on 23/11/15.
 */
@Module(complete = false, library = true)
public class FeedDataSourceModule {

    @Provides
    public FeedDataSource providesFeedDataSource(FeedDataSourceFromBBDD feedDataSourceFromBBDD) {
        return feedDataSourceFromBBDD;
    }
}
