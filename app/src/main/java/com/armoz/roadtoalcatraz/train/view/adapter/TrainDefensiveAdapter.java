package com.armoz.roadtoalcatraz.train.view.adapter;

import android.content.Context;

import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.train.view.controller.TrainController;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class TrainDefensiveAdapter extends TrainAdapter {

    public TrainDefensiveAdapter(Context context, PlayerModel playerModel, TrainController controller) {
        super(context,playerModel, controller);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        disableActionsWhileTraining(holder);

        switch (position){
            case 0:
                fillHolder(holder, PlayerModel.SKILL_STEAL, getModel().getSteal());
                break;
            case 1:
                fillHolder(holder, PlayerModel.SKILL_BLOCK, getModel().getBlock());
                break;
            case 2:
                fillHolder(holder, PlayerModel.SKILL_INT_DEFENSE, getModel().getIntDefense());
                break;
            case 3:
                fillHolder(holder, PlayerModel.SKILL_EXT_DEFENSE, getModel().getExtDefense());
                break;
            case 4:
                fillHolder(holder, PlayerModel.SKILL_DEFENSIVE_REBOUND, getModel().getDefensiveRebounding());
                break;
        }

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 5;
    }


}
