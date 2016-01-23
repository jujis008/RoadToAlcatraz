package com.armoz.roadtoalcatraz.feed.datasource.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;
import com.armoz.roadtoalcatraz.feed.datasource.FeedDataSource;
import com.armoz.roadtoalcatraz.feed.domain.model.FeedModel;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class FeedDataSourceFromBBDD implements FeedDataSource {


    private static final String LOGTAG = "FeedDataSourceFromBBDD";
    private final Dao<MessageModel, String> daoMessages;
    private static Long NUM_MESSAGES = 30L;

    @Inject
    public FeedDataSourceFromBBDD(Dao<MessageModel, String> daoMessages) {
        this.daoMessages = daoMessages;
    }

    @Override
    public FeedModel getFeed() {

        FeedModel model = new FeedModel();
        List<MessageModel> messages = new ArrayList<>();

        try {
            QueryBuilder<MessageModel, String> builder = daoMessages.queryBuilder();
            builder.limit(NUM_MESSAGES);
            builder.orderBy("DATE", false);  // true for ascending, false for descending
            messages = daoMessages.query(builder.prepare());
        } catch (SQLException e) {
            Log.e(LOGTAG, "Error while obtaining messages from BBDD", e);
        }

        model.setMessages(messages);
        return model;
    }
}
