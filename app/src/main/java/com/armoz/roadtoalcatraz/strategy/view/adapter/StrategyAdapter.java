package com.armoz.roadtoalcatraz.strategy.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.strategy.view.controller.StrategyController;

/**
 * Created by ruben.arana on 24/12/15.
 */
public abstract class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.ViewHolder> {

    private static final String TAG = "StrategyAdapter";

    private StrategyModel model;

    StrategyController controller;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public TextView tv_strategy_name;
        public TextView tv_strategy_level;
        public SeekBar sb_strategy_level;

        public ViewHolder(View v, TextView tv_strategy_name, TextView tv_strategy_level, SeekBar sb_strategy_level) {
            super(v);
            this.view = v;
            this.tv_strategy_name = tv_strategy_name;
            this.tv_strategy_level = tv_strategy_level;
            this.sb_strategy_level = sb_strategy_level;
        }
    }

    public StrategyAdapter(StrategyModel strategyModel, StrategyController controller) {
        this.model = strategyModel;
        this.controller = controller;
    }

    @Override
    public StrategyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_strategy, parent, false);

        TextView tv_strategy_name = (TextView) v.findViewById(R.id.tv_strategy_name);
        TextView tv_strategy_level = (TextView) v.findViewById(R.id.tv_strategy_level);
        SeekBar sb_strategy_level = (SeekBar) v.findViewById(R.id.sb_strategy_level);

        ViewHolder vh = new ViewHolder(v, tv_strategy_name, tv_strategy_level, sb_strategy_level);

        return vh;
    }

    protected StrategyModel getModel() {
        return model;
    }

    protected void fillHolder(final ViewHolder holder, final String strategy, int strategyValue) {

        holder.tv_strategy_name.setText(strategy);
        holder.sb_strategy_level.setProgress(strategyValue);
        holder.tv_strategy_level.setText(String.valueOf(strategyValue));

        holder.sb_strategy_level.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "ProgressChanged to " + progress);
                holder.tv_strategy_level.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "UPDATING STRATEGY");
                updateStrategy(strategy, seekBar.getProgress());
            }
        });
    }

    protected void updateStrategy(String strategy, int progress){

        switch (strategy){
            case StrategyModel.LESS_TO_MORE_PHYSICAL:
                model.setLessToMorePhysical(progress);
                break;
            case StrategyModel.LESS_TO_MORE_TRASHTALKING:
                model.setLessToMoreTrashtalking(progress);
                break;
            case StrategyModel.LESS_TO_MORE_EXT_ACTION:
                model.setLessToMoreExtActions(progress);
                break;
            case StrategyModel.PENETRATION_VS_POSTMOVE:
                model.setPenetrationVsPostmove(progress);
                break;
            case StrategyModel.QUICK_VS_ELABORATED_SHOT:
                model.setQuickVsElaboratedShoot(progress);
                break;
            case StrategyModel.FIGHT_OFFENSIVE_REBOUNDING:
                model.setFightOffensiveRebounding(progress);
                break;
            case StrategyModel.LESS_TO_MORE_STEAL:
                model.setLessToMoreSteal(progress);
                break;
            case StrategyModel.LESS_TO_MORE_SPACING:
                model.setLessToMoreSpacing(progress);
                break;
            case StrategyModel.LESS_TO_MORE_BLOCKING:
                model.setLessToMoreBlocking(progress);
                break;
            case StrategyModel.FIGHT_DEFENSIVE_REBOUNDING:
                model.setFightDefensiveRebounding(progress);
                break;
        }

        controller.updateStrategy(model);
    }


}
