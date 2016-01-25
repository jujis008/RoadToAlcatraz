package com.armoz.roadtoalcatraz.base.domain.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class StrategyModel {

    public static final String ID = "ID";

    public static final String LESS_TO_MORE_PHYSICAL = "LESS_TO_MORE_PHYSICAL";
    public static final String LESS_TO_MORE_TRASHTALKING = "LESS_TO_MORE_TRASHTALKING";

    public static final String LESS_TO_MORE_EXT_ACTION = "LESS_TO_MORE_EXT_ACTION";
    public static final String PENETRATION_VS_POSTMOVE = "PENETRATION_VS_POSTMOVE";
    public static final String QUICK_VS_ELABORATED_SHOT = "QUICK_VS_ELABORATED_SHOT";
    public static final String FIGHT_OFFENSIVE_REBOUNDING = "FIGHT_OFFENSIVE_REBOUNDING";

    public static final String LESS_TO_MORE_STEAL = "LESS_TO_MORE_STEAL";
    public static final String LESS_TO_MORE_SPACING = "LESS_TO_MORE_SPACING";
    public static final String LESS_TO_MORE_BLOCKING = "LESS_TO_MORE_BLOCKING";
    public static final String FIGHT_DEFENSIVE_REBOUNDING = "FIGHT_DEFENSIVE_REBOUNDING";


    @DatabaseField(generatedId = true, columnName = ID)
    private int id;

    // All fields must have a number between 0 and 100


    //General strategy

    //0 -> less physical, 100-> more physical
    @DatabaseField(columnName = LESS_TO_MORE_PHYSICAL)
    private int lessToMorePhysical;

    //0 -> never, 100-> always
    @DatabaseField(columnName = LESS_TO_MORE_TRASHTALKING)
    private int lessToMoreTrashtalking;


    //Ofensive strategy

    //0 -> int, 100-> ext
    @DatabaseField(columnName = LESS_TO_MORE_EXT_ACTION)
    private int lessToMoreExtActions;

    //0 -> penetration, 100 -> post moves
    @DatabaseField(columnName = PENETRATION_VS_POSTMOVE)
    private int penetrationVsPostmove;

    //0 -> quick shoot, 100 -> try to get a better shoot
    @DatabaseField(columnName = QUICK_VS_ELABORATED_SHOT)
    private int quickVsElaboratedShoot;

    //0 -> never, 100 -> always
    @DatabaseField(columnName = FIGHT_OFFENSIVE_REBOUNDING)
    private int fightOffensiveRebounding;


    //Defensive Strategy

    //0 -> never, 100-> always
    @DatabaseField(columnName = LESS_TO_MORE_STEAL)
    private int lessToMoreSteal;

    //0 -> dont let ext shoot, 100-> always let ext shoot
    @DatabaseField(columnName = LESS_TO_MORE_SPACING)
    private int lessToMoreSpacing;

    //0 -> never, 100-> always
    @DatabaseField(columnName = LESS_TO_MORE_BLOCKING)
    private int lessToMoreBlocking;

    //0 -> never, 100 -> always
    @DatabaseField(columnName = FIGHT_DEFENSIVE_REBOUNDING)
    private int fightDefensiveRebounding;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessToMorePhysical() {
        return lessToMorePhysical;
    }

    public void setLessToMorePhysical(int lessToMorePhysical) {
        this.lessToMorePhysical = lessToMorePhysical;
    }

    public int getLessToMoreTrashtalking() {
        return lessToMoreTrashtalking;
    }

    public void setLessToMoreTrashtalking(int lessToMoreTrashtalking) {
        this.lessToMoreTrashtalking = lessToMoreTrashtalking;
    }

    public int getLessToMoreExtActions() {
        return lessToMoreExtActions;
    }

    public void setLessToMoreExtActions(int lessToMoreExtActions) {
        this.lessToMoreExtActions = lessToMoreExtActions;
    }

    public int getPenetrationVsPostmove() {
        return penetrationVsPostmove;
    }

    public void setPenetrationVsPostmove(int penetrationVsPostmove) {
        this.penetrationVsPostmove = penetrationVsPostmove;
    }

    public int getQuickVsElaboratedShoot() {
        return quickVsElaboratedShoot;
    }

    public void setQuickVsElaboratedShoot(int quickVsElaboratedShoot) {
        this.quickVsElaboratedShoot = quickVsElaboratedShoot;
    }

    public int getFightOffensiveRebounding() {
        return fightOffensiveRebounding;
    }

    public void setFightOffensiveRebounding(int fightOffensiveRebounding) {
        this.fightOffensiveRebounding = fightOffensiveRebounding;
    }

    public int getLessToMoreSteal() {
        return lessToMoreSteal;
    }

    public void setLessToMoreSteal(int lessToMoreSteal) {
        this.lessToMoreSteal = lessToMoreSteal;
    }

    public int getLessToMoreSpacing() {
        return lessToMoreSpacing;
    }

    public void setLessToMoreSpacing(int lessToMoreSpacing) {
        this.lessToMoreSpacing = lessToMoreSpacing;
    }

    public int getLessToMoreBlocking() {
        return lessToMoreBlocking;
    }

    public void setLessToMoreBlocking(int lessToMoreBlocking) {
        this.lessToMoreBlocking = lessToMoreBlocking;
    }

    public int getFightDefensiveRebounding() {
        return fightDefensiveRebounding;
    }

    public void setFightDefensiveRebounding(int fightDefensiveRebounding) {
        this.fightDefensiveRebounding = fightDefensiveRebounding;
    }
}
