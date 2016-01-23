package com.armoz.roadtoalcatraz.base.view.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.armoz.roadtoalcatraz.base.domain.events.ErrorEvent;
import com.armoz.roadtoalcatraz.base.view.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by ruben.arana on 23/11/15.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((BaseActivity) this.getActivity()).inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    public abstract boolean showError(ErrorEvent event);

    @Override
    public void onResume() {
        super.onResume();
    }

    protected void createSnackbarError(View rootView) {
        Snackbar snackbar = Snackbar.make(
                rootView,
                "Error message",
                Snackbar.LENGTH_LONG);

        //No action created
        snackbar.setAction("Action", null);
        snackbar.getView().setBackgroundColor(Color.RED);

        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        snackbar.show();
    }

}
