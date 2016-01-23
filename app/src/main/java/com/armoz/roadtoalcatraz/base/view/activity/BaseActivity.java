package com.armoz.roadtoalcatraz.base.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.application.BaseApplication;
import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.errors.ViewErrorEvent;
import com.armoz.roadtoalcatraz.base.view.errors.ViewErrorHandler;
import com.squareup.otto.Bus;

import javax.inject.Inject;

abstract public class BaseActivity extends AppCompatActivity implements ViewErrorEvent {

    @Inject
    Bus bus;

    protected ViewErrorHandler viewErrorHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);
        setContentView(getActivityLayout());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void inject(Object object) {
        // Perform injection so that when this call returns all dependencies will be available for use.
        ((BaseApplication) getApplication()).inject(object);
    }


    public void onError(ErrorEvent event) {
        Log.e("ERROR", event.getClass().getSimpleName() + " - " + event.getMessage());
    }

    protected abstract boolean showError(ErrorEvent event);

    protected int getActivityLayout() {
        return R.layout.activity_base;
    }

}
