package com.armoz.roadtoalcatraz.feed.domain.model;

import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;

import java.util.List;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class FeedModel {

    private List<MessageModel> messages;

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }
}
