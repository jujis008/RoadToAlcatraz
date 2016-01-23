package com.armoz.roadtoalcatraz.base.domain;

import android.content.Context;
import android.util.Log;

import com.armoz.roadtoalcatraz.base.daggerutils.ForApplication;
import com.armoz.roadtoalcatraz.base.datasource.dao.DatabaseHelper;
import com.armoz.roadtoalcatraz.base.domain.interactor.MainThread;
import com.armoz.roadtoalcatraz.base.domain.interactor.impl.MainThreadHandler;
import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.j256.ormlite.dao.Dao;
import com.path.android.jobqueue.JobManager;
import com.squareup.otto.Bus;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This module provide global dependencies for all domain objects.
 */
@Module(complete = false,
        library = true)
public class GlobalDomainModule {

    private static final String LOGTAG = "GlobalDataSourceModule";

    @Provides
    @Singleton
    JobManager provideJobManager(@ForApplication Context context) {
        return new JobManager(context);
    }

    @Provides
    @Singleton
    MainThread provideMainThread(MainThreadHandler mainThreadHandler) {
        return mainThreadHandler;
    }

    @Provides
    @Named("zulu_with_millis")
    SimpleDateFormat provideSimpleDateFormatZuluWithMillis() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        // This is to solve problem with list offer in Zulu hour
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT0"));
        return simpleDateFormat;
    }

    @Provides
    @Named("zulu")
    SimpleDateFormat provideSimpleDateFormatZulu() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    @Provides
    @Named("general")
    SimpleDateFormat provideSimpleDateFormatGeneral() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());
    }

    @Provides
    DomainErrorHandler provideDomainErrorHandler(Bus bus) {
        return new DomainErrorHandler(bus);
    }


    @Provides
    @Singleton
    public DatabaseHelper provideDataBaseHelper(@ForApplication Context context) {
        return new DatabaseHelper(context);
    }


    @Provides
    public Dao<MessageModel, String> provideDaoMessages(DatabaseHelper dbHelper) {
        try {
            return dbHelper.getMessagesDao();
        } catch (SQLException e) {
            Log.e(LOGTAG, "error creating dao", e);
        }
        return null;
    }

    @Provides
    public Dao<TournamentModel, String> provideDaoTournaments(DatabaseHelper dbHelper) {
        try {
            return dbHelper.getTournamentsDao();
        } catch (SQLException e) {
            Log.e(LOGTAG, "error creating dao", e);
        }
        return null;
    }

    @Provides
    public Dao<GameModel, String> provideDaoGames(DatabaseHelper dbHelper) {
        try {
            return dbHelper.getGamesDao();
        } catch (SQLException e) {
            Log.e(LOGTAG, "error creating dao", e);
        }
        return null;
    }

    @Provides
    public Dao<PlayerModel, String> provideDaoPlayers(DatabaseHelper dbHelper) {
        try {
            return dbHelper.getPlayersDao();
        } catch (SQLException e) {
            Log.e(LOGTAG, "error creating dao", e);
        }
        return null;
    }
}
