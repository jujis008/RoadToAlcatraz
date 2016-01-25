package com.armoz.roadtoalcatraz.message.datasource.impl;

import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;
import com.armoz.roadtoalcatraz.message.datasource.MessageDataSource;
import com.j256.ormlite.dao.Dao;

import java.util.Date;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class MessageDataSourceFromBBDD implements MessageDataSource {


    private static final String TAG = "MessageDSFromBBDD";

    private final Dao<MessageModel, String> daoMessages;

    @Inject
    public MessageDataSourceFromBBDD(Dao<MessageModel, String> daoMessages) {
        this.daoMessages = daoMessages;
    }

    @Override
    public void createMessage(String title, String body, String type) throws Exception {
        MessageModel m = new MessageModel();
        m.setBody(body);
        m.setTitle(title);
        m.setRead(false);
        m.setDate(new Date(System.currentTimeMillis()));
        m.setType(type);
        daoMessages.create(m);
    }
}


