package com.armoz.roadtoalcatraz.base.application;

import android.app.Application;

import com.armoz.roadtoalcatraz.base.domain.GlobalDomainModule;
import com.armoz.roadtoalcatraz.base.utils.module.AndroidModule;
import com.armoz.roadtoalcatraz.calendar.domain.CalendarDomainModule;
import com.armoz.roadtoalcatraz.calendar.view.CalendarViewModule;
import com.armoz.roadtoalcatraz.feed.datasource.FeedDataSourceModule;
import com.armoz.roadtoalcatraz.feed.domain.FeedDomainModule;
import com.armoz.roadtoalcatraz.feed.view.FeedViewModule;
import com.armoz.roadtoalcatraz.game.datasource.GameDataSourceModule;
import com.armoz.roadtoalcatraz.message.datasource.MessageDataSourceModule;
import com.armoz.roadtoalcatraz.player.datasource.PlayerDataSourceModule;
import com.armoz.roadtoalcatraz.strategy.datasource.StrategyDataSourceModule;
import com.armoz.roadtoalcatraz.strategy.domain.StrategyDomainModule;
import com.armoz.roadtoalcatraz.strategy.view.StrategyViewModule;
import com.armoz.roadtoalcatraz.tournament.datasource.TournamentDataSourceModule;
import com.armoz.roadtoalcatraz.tournamentDetail.domain.TournamentDetailDomainModule;
import com.armoz.roadtoalcatraz.tournamentDetail.view.TournamentDetailViewModule;
import com.armoz.roadtoalcatraz.train.datasource.TrainDataSourceModule;
import com.armoz.roadtoalcatraz.train.domain.TrainDomainModule;
import com.armoz.roadtoalcatraz.train.view.TrainViewModule;
import com.armoz.roadtoalcatraz.welcome.domain.WelcomeDomainModule;
import com.armoz.roadtoalcatraz.welcome.view.WelcomeViewModule;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.ndk.CrashlyticsNdk;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import io.fabric.sdk.android.Fabric;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class BaseApplication extends Application {
    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Answers(), new Crashlytics(), new CrashlyticsNdk());

        graph = ObjectGraph.create(getModules().toArray());
        graph.injectStatics();
    }

    public void inject(Object object) {
        graph.inject(object);
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(this),
                new GlobalDomainModule(),
                new PlayerDataSourceModule(),
                new FeedViewModule(),
                new FeedDomainModule(),
                new FeedDataSourceModule(),
                new CalendarViewModule(),
                new CalendarDomainModule(),
                new GameDataSourceModule(),
                new TournamentDetailViewModule(),
                new TournamentDetailDomainModule(),
                new TournamentDataSourceModule(),
                new WelcomeViewModule(),
                new WelcomeDomainModule(),
                new MessageDataSourceModule(),
                new TrainViewModule(),
                new TrainDomainModule(),
                new TrainDataSourceModule(),
                new StrategyViewModule(),
                new StrategyDomainModule(),
                new StrategyDataSourceModule()

        );

    }
}
