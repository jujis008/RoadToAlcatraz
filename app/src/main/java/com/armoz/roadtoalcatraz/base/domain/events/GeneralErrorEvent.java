package com.armoz.roadtoalcatraz.base.domain.events;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class GeneralErrorEvent extends ErrorEvent {


    public GeneralErrorEvent(String error) {
        super(error);
    }

    public GeneralErrorEvent(Exception exception) {
        super(exception);
    }
}
