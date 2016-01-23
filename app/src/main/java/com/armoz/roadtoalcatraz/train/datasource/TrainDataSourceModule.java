package com.armoz.roadtoalcatraz.train.datasource;

import com.armoz.roadtoalcatraz.train.datasource.impl.TrainDataSourceFromBBDD;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruben.arana on 23/11/15.
 */
@Module(complete = false, library = true)
public class TrainDataSourceModule {

    @Provides
    public TrainDataSource providesTrainDataSource(TrainDataSourceFromBBDD trainDataSourceFromBBDD) {
        return trainDataSourceFromBBDD;
    }
}
