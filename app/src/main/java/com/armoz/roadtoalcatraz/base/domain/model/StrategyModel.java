package com.armoz.roadtoalcatraz.base.domain.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class StrategyModel {

    @DatabaseField(generatedId = true, columnName = "ID")
    private long id;

    // All fields must have a number between 0 and 100


    //General strategy

    //0 -> less physical, 100-> more physical
    @DatabaseField(columnName = "PHYSICAL")
    private int physical;

    //0 -> never, 100-> always
    @DatabaseField(columnName = "TRASHTALKING")
    private int trashtalking;


    //Ofensive strategy

    //0 -> int, 100-> ext
    @DatabaseField(columnName = "ACTIONS")
    private int actions;

    //0 -> penetration, 100 -> post moves
    @DatabaseField(columnName = "INT_ACTIONS")
    private int int_actions;

    //0 -> quick shoot, 100 -> try to get a better shoot
    @DatabaseField(columnName = "EXT_ACTIONS")
    private int ext_actions;

    //0 -> never, 100 -> always
    @DatabaseField(columnName = "OFF_REBOUNDING")
    private int off_rebounding;


    //Defensive Strategy

    //0 -> never, 100-> always
    @DatabaseField(columnName = "STEALING")
    private int stealing;

    //0 -> dont let ext shoot, 100-> always let ext shoot
    @DatabaseField(columnName = "SPACEING")
    private int spaceing;

    //0 -> never, 100-> always
    @DatabaseField(columnName = "BLOCKING")
    private int blocking;

    //0 -> never, 100 -> always
    @DatabaseField(columnName = "DEF_REBOUNDING")
    private int def_rebounding;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPhysical() {
        return physical;
    }

    public void setPhysical(int physical) {
        this.physical = physical;
    }

    public int getTrashtalking() {
        return trashtalking;
    }

    public void setTrashtalking(int trashtalking) {
        this.trashtalking = trashtalking;
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public int getInt_actions() {
        return int_actions;
    }

    public void setInt_actions(int int_actions) {
        this.int_actions = int_actions;
    }

    public int getExt_actions() {
        return ext_actions;
    }

    public void setExt_actions(int ext_actions) {
        this.ext_actions = ext_actions;
    }

    public int getOff_rebounding() {
        return off_rebounding;
    }

    public void setOff_rebounding(int off_rebounding) {
        this.off_rebounding = off_rebounding;
    }

    public int getStealing() {
        return stealing;
    }

    public void setStealing(int stealing) {
        this.stealing = stealing;
    }

    public int getSpaceing() {
        return spaceing;
    }

    public void setSpaceing(int spaceing) {
        this.spaceing = spaceing;
    }

    public int getBlocking() {
        return blocking;
    }

    public void setBlocking(int blocking) {
        this.blocking = blocking;
    }

    public int getDef_rebounding() {
        return def_rebounding;
    }

    public void setDef_rebounding(int def_rebounding) {
        this.def_rebounding = def_rebounding;
    }
}
