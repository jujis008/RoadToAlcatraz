package com.armoz.roadtoalcatraz.base.domain.events;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class ErrorEvent {

    public static final int ACTION_NO_ACTION = 0;
    public static Exception exception;

    private String message;
    private int action = ACTION_NO_ACTION;

    public ErrorEvent() {
    }

    public ErrorEvent(Exception exception) {
        this.exception = exception;
        this.message = exception.getMessage();
    }

    public ErrorEvent(Exception exception, int action) {
        this.exception = exception;
        this.message = exception.getMessage();
        this.action = action;
    }

    public ErrorEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public static Exception getException() {
        return exception;
    }

    public static void setException(Exception exception) {
        ErrorEvent.exception = exception;
    }
}

