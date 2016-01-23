package com.armoz.roadtoalcatraz.base.domain.model;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class PlayModel {
    private long playerId;
    private int action;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
