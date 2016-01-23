package com.armoz.roadtoalcatraz.train.view.adapter;

import android.content.Context;

import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.train.view.controller.TrainController;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class TrainPhysicalAdapter extends TrainAdapter {

    public TrainPhysicalAdapter(Context context, PlayerModel playerModel, TrainController controller) {
        super(context, playerModel, controller);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        disableActionsWhileTraining(holder);

        switch (position){
            case 0:
                fillHolder(holder, PlayerModel.SKILL_STAMINA, getModel().getStamina());
                break;
            case 1:
                fillHolder(holder, PlayerModel.SKILL_JUMP, getModel().getJump());
                break;
            case 2:
                fillHolder(holder, PlayerModel.SKILL_SPEED, getModel().getSpeed());
                break;
            case 3:
                fillHolder(holder, PlayerModel.SKILL_STRENGTH, getModel().getStrength());
                break;
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 4;
    }


}
