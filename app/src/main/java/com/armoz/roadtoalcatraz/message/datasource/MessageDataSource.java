package com.armoz.roadtoalcatraz.message.datasource;

/**
 * Created by ruben.arana on 23/11/15.
 */
public interface MessageDataSource {

    void createMessage(String title, String body, String type) throws Exception;
}
