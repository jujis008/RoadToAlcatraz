package com.armoz.roadtoalcatraz.strategy.view.adapter;

import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.strategy.view.controller.StrategyController;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class StrategyGeneralAdapter extends StrategyAdapter {

    private static final String LOG = "StrategyGeneralAdapter";

    public StrategyGeneralAdapter(StrategyModel strategyModel, StrategyController controller) {
        super(strategyModel, controller);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        switch (position) {
            case 0:
                fillHolder(holder, StrategyModel.LESS_TO_MORE_PHYSICAL, getModel().getLessToMorePhysical());
                break;
            case 1:
                fillHolder(holder, StrategyModel.LESS_TO_MORE_TRASHTALKING, getModel().getLessToMoreTrashtalking());
                break;

        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 2;
    }
}
