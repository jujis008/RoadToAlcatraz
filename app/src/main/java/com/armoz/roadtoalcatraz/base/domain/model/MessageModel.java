package com.armoz.roadtoalcatraz.base.domain.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class MessageModel {

    @DatabaseField(generatedId = true, columnName = "ID")
    private long id;

    @DatabaseField(columnName = "TITLE")
    private String title;

    @DatabaseField(columnName = "BODY")
    private String body;

    @DatabaseField(columnName = "TYPE")
    private String type;

    @DatabaseField(columnName = "IS_READED")
    private boolean read;

    @DatabaseField(columnName = "DATE")
    private Date date;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
