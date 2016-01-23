package com.armoz.roadtoalcatraz.base.datasource.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.armoz.roadtoalcatraz.base.domain.model.GameModel;
import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.base.domain.model.TournamentModel;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by ruben.arana on 23/11/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "roadToAlcatraz.db";
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    private Dao<MessageModel, String> messagesDao = null;
    private Dao<TournamentModel, String> tournamentsDao = null;
    private Dao<GameModel, String> gamesDao = null;
    private Dao<PlayerModel, String> playersDao = null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable
     * statements here to create the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, MessageModel.class);
            TableUtils.createTable(connectionSource, TournamentModel.class);
            TableUtils.createTable(connectionSource, GameModel.class);
            TableUtils.createTable(connectionSource, PlayerModel.class);

            createTestGames();


        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    private void createTestGames() throws SQLException{
        GameModel g = new GameModel();
        g.setPlayer1Id(1);
        g.setPlayer1Id(2);
        g.setTournamentID(3);
        g.setRound(1);

        getGamesDao().create(g);
    }

    /**
     * This is called when your application is upgraded and it has a higher version number.
     * This allows you to adjust the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {

    }

    public void clearAllTables() {
        clearTable(MessageModel.class);
    }

    public void clearTable(Class dbClass) {
        try {
            TableUtils.clearTable(connectionSource, dbClass);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't clean table", e);
            throw new RuntimeException(e);
        }
    }


    public Dao<MessageModel, String> getMessagesDao() throws SQLException {
        if (messagesDao == null) {
            messagesDao = getDao(MessageModel.class);
        }
        return messagesDao;
    }

    public Dao<TournamentModel, String> getTournamentsDao() throws SQLException {
        if (tournamentsDao == null) {
            tournamentsDao = getDao(TournamentModel.class);
        }
        return tournamentsDao;
    }

    public Dao<GameModel, String> getGamesDao() throws SQLException {
        if (gamesDao == null) {
            gamesDao = getDao(GameModel.class);
        }
        return gamesDao;
    }

    public Dao<PlayerModel, String> getPlayersDao() throws SQLException {
        if (playersDao == null) {
            playersDao = getDao(PlayerModel.class);
        }
        return playersDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        messagesDao = null;
        tournamentsDao = null;
        gamesDao = null;
        playersDao = null;
    }
}
