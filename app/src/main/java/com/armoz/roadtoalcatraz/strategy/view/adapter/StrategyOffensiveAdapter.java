package com.armoz.roadtoalcatraz.strategy.view.adapter;

import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.strategy.view.controller.StrategyController;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class StrategyOffensiveAdapter extends StrategyAdapter {

    private static final String LOG = "StrategyOffAdapter";

    public StrategyOffensiveAdapter(StrategyModel strategyModel, StrategyController controller) {
        super(strategyModel, controller);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        switch (position) {
            case 0:
                fillHolder(holder, StrategyModel.LESS_TO_MORE_EXT_ACTION, getModel().getLessToMoreExtActions());
                break;
            case 1:
                fillHolder(holder, StrategyModel.PENETRATION_VS_POSTMOVE, getModel().getPenetrationVsPostmove());
                break;
            case 2:
                fillHolder(holder, StrategyModel.QUICK_VS_ELABORATED_SHOT, getModel().getQuickVsElaboratedShoot());
                break;
            case 3:
                fillHolder(holder, StrategyModel.FIGHT_OFFENSIVE_REBOUNDING, getModel().getFightOffensiveRebounding());
                break;

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 4;
    }
}
