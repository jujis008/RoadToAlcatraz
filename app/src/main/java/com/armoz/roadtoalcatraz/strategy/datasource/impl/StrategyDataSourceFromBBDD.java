package com.armoz.roadtoalcatraz.strategy.datasource.impl;

import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.model.StrategyModel;
import com.armoz.roadtoalcatraz.strategy.datasource.StrategyDataSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;

import javax.inject.Inject;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class StrategyDataSourceFromBBDD implements StrategyDataSource {


    private static final String LOGTAG = "StrategyDSFromBBDD";
    private final Dao<StrategyModel, String> daoStrategy;

    @Inject
    public StrategyDataSourceFromBBDD(Dao<StrategyModel, String> daoStrategy) {
        this.daoStrategy = daoStrategy;
    }

    @Override
    public StrategyModel obtainStrategy(long strategyId) {
        StrategyModel model = new StrategyModel();

        try {
            QueryBuilder<StrategyModel, String> builder = daoStrategy.queryBuilder();
            builder.where().eq("ID", strategyId);
            model = daoStrategy.queryForFirst(builder.prepare());
        } catch (SQLException e) {
            Log.e(LOGTAG, "Error while obtaining strategy from BBDD", e);
        }

        return model;
    }

    @Override
    public void updateStrategy(StrategyModel strategyModel) {
        try {
            daoStrategy.update(strategyModel);
        } catch (SQLException e) {
            Log.e(LOGTAG, "Error while obtaining strategy from BBDD", e);
        }
    }
}
