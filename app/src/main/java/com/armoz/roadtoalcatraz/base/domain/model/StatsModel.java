package com.armoz.roadtoalcatraz.base.domain.model;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class StatsModel {

    private int points;

    private int threeShots;
    private int threeShotsCompleted;

    private int twoShoots;
    private int twoShootsCompleted;

    private int freeThrows;
    private int freeThrowsCompleted;

    private int offensiveRebounds;
    private int defensiveRebounds;

    private int steals;
    private int blocks;

    private int faults;
    private int turnovers;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getThreeShots() {
        return threeShots;
    }

    public void setThreeShots(int threeShots) {
        this.threeShots = threeShots;
    }

    public int getThreeShotsCompleted() {
        return threeShotsCompleted;
    }

    public void setThreeShotsCompleted(int threeShotsCompleted) {
        this.threeShotsCompleted = threeShotsCompleted;
    }

    public int getFreeThrows() {
        return freeThrows;
    }

    public void setFreeThrows(int freeThrows) {
        this.freeThrows = freeThrows;
    }


    public int getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public void setOffensiveRebounds(int offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }

    public int getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public void setDefensiveRebounds(int defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getFaults() {
        return faults;
    }

    public void setFaults(int faults) {
        this.faults = faults;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public int getTwoShoots() {
        return twoShoots;
    }

    public void setTwoShoots(int twoShoots) {
        this.twoShoots = twoShoots;
    }

    public int getTwoShootsCompleted() {
        return twoShootsCompleted;
    }

    public void setTwoShootsCompleted(int twoShootsCompleted) {
        this.twoShootsCompleted = twoShootsCompleted;
    }

    public int getFreeThrowsCompleted() {
        return freeThrowsCompleted;
    }

    public void setFreeThrowsCompleted(int freeThrowsCompleted) {
        this.freeThrowsCompleted = freeThrowsCompleted;
    }
}
