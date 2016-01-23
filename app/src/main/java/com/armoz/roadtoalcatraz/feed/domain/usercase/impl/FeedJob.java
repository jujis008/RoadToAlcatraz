package com.armoz.roadtoalcatraz.feed.domain.usercase.impl;

import com.armoz.roadtoalcatraz.base.domain.DomainErrorHandler;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.UserCaseJob;
import com.armoz.roadtoalcatraz.feed.datasource.FeedDataSource;
import com.armoz.roadtoalcatraz.feed.domain.callback.FeedCallback;
import com.armoz.roadtoalcatraz.feed.domain.model.FeedModel;
import com.armoz.roadtoalcatraz.feed.domain.usercase.Feed;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

import javax.inject.Inject;

;

public class FeedJob extends UserCaseJob implements Feed {

    private FeedCallback callback;
    private FeedDataSource feedDataSource;
    private FeedModel model;


    @Inject
    FeedJob(JobManager jobManager, MainThread mainThread,
            DomainErrorHandler domainErrorHandler, FeedDataSource feedDataSource
    ) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY), domainErrorHandler);
        this.feedDataSource = feedDataSource;
    }

    @Override
    public void getFeed(FeedCallback feedCallback) {
        this.callback = feedCallback;
        jobManager.addJob(this);

    }

    @Override
    public void doRun() throws Throwable {
        try {
            model = feedDataSource.getFeed();
            onFeedLoaded();
        }
        catch (Exception e){
            notifyError();
        }
    }

    private void notifyError() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError();
                }
            }
        });
    }

    private void onFeedLoaded() {
        sendCallback(new Runnable() {
            @Override
            public void run() {
                callback.onFeedLoaded(model);
            }
        });
    }


}
