package com.armoz.roadtoalcatraz.base.domain.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class PlayerModel {

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String SURNAME = "SURNAME";
    public static final String AGE = "AGE";
    public static final String COUNTRY = "COUNTRY";
    public static final String HEIGHT = "HEIGHT";
    public static final String WEIGHT = "WEIGHT";
    public static final String YEARS_PLAYED = "YEARS_PLAYED";

    public static final String SKILL_STAMINA = "SKILL_STAMINA";
    public static final String SKILL_JUMP = "SKILL_JUMP";
    public static final String SKILL_SPEED = "SKILL_SPEED";
    public static final String SKILL_STRENGTH = "SKILL_STRENGTH";

    public static final String SKILL_DRIBBLE = "SKILL_DRIBBLE";
    public static final String SKILL_POST_PLAY = "SKILL_POST_PLAY";
    public static final String SKILL_INT_SHOOT = "SKILL_INT_SHOOT";
    public static final String SKILL_EXT_SHOOT = "SKILL_EXT_SHOOT";
    public static final String SKILL_OFFENSIVE_REBOUND = "SKILL_OFFENSIVE_REBOUND";

    public static final String SKILL_STEAL = "SKILL_STEAL";
    public static final String SKILL_BLOCK = "SKILL_BLOCK";
    public static final String SKILL_INT_DEFENSE = "SKILL_INT_DEFENSE";
    public static final String SKILL_EXT_DEFENSE = "SKILL_EXT_DEFENSE";
    public static final String SKILL_DEFENSIVE_REBOUND = "SKILL_DEFENSIVE_REBOUND";

    public static final String SKILL_MENTAL_TOUGHNESS = "SKILL_MENTAL_TOUGHNESS";
    public static final String SKILL_WORKETHIC = "SKILL_WORKETHIC";
    public static final String SKILL_FRIENDLY = "SKILL_FRIENDLY";
    public static final String SKILL_POPULAR = "SKILL_POPULAR";

    public static final String STRATEGY = "STRATEGY";
    public static final String TRAINING_TYPE = "TRAINING_TYPE";
    public static final String TRAINING_FINISHING_DATE = "TRAINING_FINISHING_DATE";
    public static final String USER_PLAYER = "USER_PLAYER";

    @DatabaseField(generatedId = true, columnName = ID)
    private long id;

    @DatabaseField(columnName = NAME)
    private String name;

    @DatabaseField(columnName = SURNAME)
    private String surname;

    @DatabaseField(columnName = AGE)
    private int age;

    @DatabaseField(columnName = COUNTRY)
    private String country;

    @DatabaseField(columnName = HEIGHT)
    private long height;

    @DatabaseField(columnName = WEIGHT)
    private long weight;

    @DatabaseField(columnName = YEARS_PLAYED)
    private int yearsPlayed;


    //Physical Skills

    @DatabaseField(columnName = SKILL_STAMINA)
    private long stamina;

    @DatabaseField(columnName = SKILL_JUMP)
    private long jump;

    @DatabaseField(columnName = SKILL_SPEED)
    private long speed;

    @DatabaseField(columnName = SKILL_STRENGTH)
    private long strength;

    //Technical Ofensive Skills

    @DatabaseField(columnName = SKILL_DRIBBLE)
    private long dribble;

    @DatabaseField(columnName = SKILL_POST_PLAY)
    private long postPlay;

    @DatabaseField(columnName = SKILL_INT_SHOOT)
    private long intShoot;

    @DatabaseField(columnName = SKILL_EXT_SHOOT)
    private long extShoot;

    @DatabaseField(columnName = SKILL_OFFENSIVE_REBOUND)
    private long offensiveRebounding;


    //Technical Defensive Skills

    @DatabaseField(columnName = SKILL_STEAL)
    private long steal;

    @DatabaseField(columnName = SKILL_BLOCK)
    private long block;

    @DatabaseField(columnName = SKILL_INT_DEFENSE)
    private long intDefense;

    @DatabaseField(columnName = SKILL_EXT_DEFENSE)
    private long extDefense;

    @DatabaseField(columnName = SKILL_DEFENSIVE_REBOUND)
    private long defensiveRebounding;


    //Mental Skills

    @DatabaseField(columnName = SKILL_MENTAL_TOUGHNESS)
    private long mentalToughness;

    @DatabaseField(columnName = SKILL_WORKETHIC)
    private long workethic;

    @DatabaseField(columnName = SKILL_FRIENDLY)
    private long friendly;

    @DatabaseField(columnName = SKILL_POPULAR)
    private long popular;


    @DatabaseField(columnName = STRATEGY)
    private long strategy;


    private boolean atacking;

    @DatabaseField(columnName = TRAINING_TYPE)
    private String trainingType;

    @DatabaseField(columnName = TRAINING_FINISHING_DATE)
    private Date trainingFinishingDate;

    @DatabaseField(columnName = USER_PLAYER)
    private boolean userPlayer;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public int getYearsPlayed() {
        return yearsPlayed;
    }

    public void setYearsPlayed(int yearsPlayed) {
        this.yearsPlayed = yearsPlayed;
    }

    public long getStamina() {
        return stamina;
    }

    public void setStamina(long stamina) {
        this.stamina = stamina;
    }

    public long getJump() {
        return jump;
    }

    public void setJump(long jump) {
        this.jump = jump;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public long getStrength() {
        return strength;
    }

    public void setStrength(long strength) {
        this.strength = strength;
    }

    public long getDribble() {
        return dribble;
    }

    public void setDribble(long dribble) {
        this.dribble = dribble;
    }

    public long getPostPlay() {
        return postPlay;
    }

    public void setPostPlay(long postPlay) {
        this.postPlay = postPlay;
    }

    public long getIntShoot() {
        return intShoot;
    }

    public void setIntShoot(long intShoot) {
        this.intShoot = intShoot;
    }

    public long getExtShoot() {
        return extShoot;
    }

    public void setExtShoot(long extShoot) {
        this.extShoot = extShoot;
    }

    public long getOffensiveRebounding() {
        return offensiveRebounding;
    }

    public void setOffensiveRebounding(long offensiveRebounding) {
        this.offensiveRebounding = offensiveRebounding;
    }

    public long getSteal() {
        return steal;
    }

    public void setSteal(long steal) {
        this.steal = steal;
    }

    public long getBlock() {
        return block;
    }

    public void setBlock(long block) {
        this.block = block;
    }

    public long getIntDefense() {
        return intDefense;
    }

    public void setIntDefense(long intDefense) {
        this.intDefense = intDefense;
    }

    public long getExtDefense() {
        return extDefense;
    }

    public void setExtDefense(long extDefense) {
        this.extDefense = extDefense;
    }

    public long getDefensiveRebounding() {
        return defensiveRebounding;
    }

    public void setDefensiveRebounding(long defensiveRebounding) {
        this.defensiveRebounding = defensiveRebounding;
    }

    public long getMentalToughness() {
        return mentalToughness;
    }

    public void setMentalToughness(long mentalToughness) {
        this.mentalToughness = mentalToughness;
    }

    public long getWorkethic() {
        return workethic;
    }

    public void setWorkethic(long workethic) {
        this.workethic = workethic;
    }

    public long getFriendly() {
        return friendly;
    }

    public void setFriendly(long friendly) {
        this.friendly = friendly;
    }

    public long getPopular() {
        return popular;
    }

    public void setPopular(long popular) {
        this.popular = popular;
    }

    public long getStrategy() {
        return strategy;
    }

    public void setStrategy(long strategy) {
        this.strategy = strategy;
    }

    public boolean isAtacking() {
        return atacking;
    }

    public void setAtacking(boolean atacking) {
        this.atacking = atacking;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public Date getTrainingFinishingDate() {
        return trainingFinishingDate;
    }

    public void setTrainingFinishingDate(Date trainingFinishingDate) {
        this.trainingFinishingDate = trainingFinishingDate;
    }

    public boolean isUserPlayer() {
        return userPlayer;
    }

    public void setUserPlayer(boolean userPlayer) {
        this.userPlayer = userPlayer;
    }
}
