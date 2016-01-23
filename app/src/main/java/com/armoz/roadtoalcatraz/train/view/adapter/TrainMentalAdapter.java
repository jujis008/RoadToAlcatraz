package com.armoz.roadtoalcatraz.train.view.adapter;

import android.content.Context;

import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.train.view.controller.TrainController;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class TrainMentalAdapter extends TrainAdapter {

    public TrainMentalAdapter(Context context, PlayerModel playerModel, TrainController controller) {
        super(context, playerModel, controller);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        disableActionsWhileTraining(holder);

        switch (position){
            case 0:
                fillHolder(holder, PlayerModel.SKILL_MENTAL_TOUGHNESS, getModel().getMentalToughness());
                break;
            case 1:
                fillHolder(holder, PlayerModel.SKILL_WORKETHIC, getModel().getWorkethic());
                break;
            case 2:
                fillHolder(holder, PlayerModel.SKILL_FRIENDLY, getModel().getFriendly());
                break;
            case 3:
                fillHolder(holder, PlayerModel.SKILL_POPULAR, getModel().getPopular());
                break;
        }

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 4;
    }


}
