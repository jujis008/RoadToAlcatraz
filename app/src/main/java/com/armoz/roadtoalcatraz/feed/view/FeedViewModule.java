package com.armoz.roadtoalcatraz.feed.view;

/**
 * Created by ruben.arana on 23/11/15.
 */

import com.armoz.roadtoalcatraz.feed.domain.usercase.Feed;
import com.armoz.roadtoalcatraz.feed.view.activity.FeedActivity;
import com.armoz.roadtoalcatraz.feed.view.controller.FeedController;
import com.armoz.roadtoalcatraz.feed.view.fragment.FeedFragment;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module(injects = {FeedActivity.class, FeedFragment.class}, complete = false,
        library = true)
public class FeedViewModule {

    @Provides
    public FeedController provideHelloWorldController(Feed helloWorldJob) {
        return new FeedController(helloWorldJob);
    }
}
