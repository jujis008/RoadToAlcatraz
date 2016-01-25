package com.armoz.roadtoalcatraz.message.datasource;

import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;
import com.armoz.roadtoalcatraz.message.datasource.impl.MessageDataSourceFromBBDD;
import com.j256.ormlite.dao.Dao;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruben.arana on 23/11/15.
 */
@Module(complete = false, library = true)
public class MessageDataSourceModule {

    @Provides
    public MessageDataSource providesMessageDataSource(Dao<MessageModel, String> daoMessages) {

        return new MessageDataSourceFromBBDD(daoMessages);
    }
}
