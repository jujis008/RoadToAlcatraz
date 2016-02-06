package com.armoz.roadtoalcatraz.base.domain.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class TournamentModel {

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String LEVEL = "LEVEL";
    public static final String DATE = "DATE";
    public static final String ROUNDS = "ROUNDS";


    @DatabaseField(generatedId = true, columnName = ID)
    private int id;

    @DatabaseField(columnName = NAME)
    private String name;

    @DatabaseField(columnName = LEVEL)
    private int level;

    @DatabaseField(columnName = DATE)
    private Date date;

    @DatabaseField(columnName = ROUNDS)
    private int rounds;

    private List<GameModel> games = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public List<GameModel> getGames() {
        return games;
    }

    public void setGames(List<GameModel> games) {
        this.games = games;
    }
}
