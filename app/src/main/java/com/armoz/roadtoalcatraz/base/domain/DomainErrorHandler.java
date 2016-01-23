package com.armoz.roadtoalcatraz.base.domain;

import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.squareup.otto.Bus;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class DomainErrorHandler {

    private Bus bus;

    public DomainErrorHandler(Bus bus) {
        this.bus = bus;
    }

    public void notifyError(ErrorEvent errorObject) throws Throwable {
        bus.post(errorObject);
    }

}
