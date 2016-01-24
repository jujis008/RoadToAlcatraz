package com.armoz.roadtoalcatraz.strategy.view.adapter;

import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.strategy.view.controller.StrategyController;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class StrategyDefensiveAdapter extends StrategyAdapter {

    public StrategyDefensiveAdapter( StrategyModel strategyModel, StrategyController controller) {
        super(strategyModel, controller);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        switch (position) {
            case 0:
                fillHolder(holder, StrategyModel.LESS_TO_MORE_STEAL, getModel().getLessToMoreSteal());
                break;
            case 1:
                fillHolder(holder, StrategyModel.LESS_TO_MORE_SPACING, getModel().getLessToMoreSpacing());
                break;
            case 2:
                fillHolder(holder, StrategyModel.LESS_TO_MORE_BLOCKING, getModel().getLessToMoreBlocking());
                break;
            case 3:
                fillHolder(holder, StrategyModel.FIGHT_DEFENSIVE_REBOUNDING, getModel().getFightDefensiveRebounding());
                break;

        }

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 4;
    }


}
