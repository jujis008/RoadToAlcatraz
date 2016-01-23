package com.armoz.roadtoalcatraz.base.view.errors;

import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class ViewErrorHandler {

    private Bus bus;
    ViewErrorEvent errorEvent;

    public ViewErrorHandler(Bus bus, ViewErrorEvent errorEvent) {
        this.bus = bus;
        this.errorEvent = errorEvent;
    }

    public void register() {
        bus.register(this);
    }

    public void unregister() {
        bus.unregister(this);
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent event) {
        errorEvent.onError(event);
    }

}

