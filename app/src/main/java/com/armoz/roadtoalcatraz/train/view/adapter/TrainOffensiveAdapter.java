package com.armoz.roadtoalcatraz.train.view.adapter;

import android.content.Context;

import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.train.view.controller.TrainController;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class TrainOffensiveAdapter extends TrainAdapter {

    private static final String LOG = "TrainOffAdapter";

    public TrainOffensiveAdapter(Context context, PlayerModel playerModel, TrainController controller) {
        super(context, playerModel, controller);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        disableActionsWhileTraining(holder);

        switch (position){
            case 0:
                fillHolder(holder, PlayerModel.SKILL_DRIBBLE, getModel().getDribble());
                break;
            case 1:
                fillHolder(holder, PlayerModel.SKILL_POST_PLAY, getModel().getPostPlay());
                break;
            case 2:
                fillHolder(holder, PlayerModel.SKILL_INT_SHOOT, getModel().getIntShoot());
                break;
            case 3:
                fillHolder(holder, PlayerModel.SKILL_EXT_SHOOT, getModel().getExtShoot());
                break;
            case 4:
                fillHolder(holder, PlayerModel.SKILL_OFFENSIVE_REBOUND, getModel().getOffensiveRebounding());
                break;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 5;
    }
}
