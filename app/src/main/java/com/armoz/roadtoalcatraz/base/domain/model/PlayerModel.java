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
    private int id;

    @DatabaseField(columnName = NAME)
    private String name;

    @DatabaseField(columnName = SURNAME)
    private String surname;

    @DatabaseField(columnName = AGE)
    private int age;

    @DatabaseField(columnName = COUNTRY)
    private String country;

    @DatabaseField(columnName = HEIGHT)
    private int height;

    @DatabaseField(columnName = WEIGHT)
    private int weight;

    @DatabaseField(columnName = YEARS_PLAYED)
    private int yearsPlayed;


    //Physical Skills

    @DatabaseField(columnName = SKILL_STAMINA)
    private int stamina;

    @DatabaseField(columnName = SKILL_JUMP)
    private int jump;

    @DatabaseField(columnName = SKILL_SPEED)
    private int speed;

    @DatabaseField(columnName = SKILL_STRENGTH)
    private int strength;

    //Technical Ofensive Skills

    @DatabaseField(columnName = SKILL_DRIBBLE)
    private int dribble;

    @DatabaseField(columnName = SKILL_POST_PLAY)
    private int postPlay;

    @DatabaseField(columnName = SKILL_INT_SHOOT)
    private int intShoot;

    @DatabaseField(columnName = SKILL_EXT_SHOOT)
    private int extShoot;

    @DatabaseField(columnName = SKILL_OFFENSIVE_REBOUND)
    private int offensiveRebounding;


    //Technical Defensive Skills

    @DatabaseField(columnName = SKILL_STEAL)
    private int steal;

    @DatabaseField(columnName = SKILL_BLOCK)
    private int block;

    @DatabaseField(columnName = SKILL_INT_DEFENSE)
    private int intDefense;

    @DatabaseField(columnName = SKILL_EXT_DEFENSE)
    private int extDefense;

    @DatabaseField(columnName = SKILL_DEFENSIVE_REBOUND)
    private int defensiveRebounding;


    //Mental Skills

    @DatabaseField(columnName = SKILL_MENTAL_TOUGHNESS)
    private int mentalToughness;

    @DatabaseField(columnName = SKILL_WORKETHIC)
    private int workethic;

    @DatabaseField(columnName = SKILL_FRIENDLY)
    private int friendly;

    @DatabaseField(columnName = SKILL_POPULAR)
    private int popular;


    @DatabaseField(columnName = STRATEGY)
    private int strategy;


    private boolean atacking;

    @DatabaseField(columnName = TRAINING_TYPE)
    private String trainingType;

    @DatabaseField(columnName = TRAINING_FINISHING_DATE)
    private Date trainingFinishingDate;

    @DatabaseField(columnName = USER_PLAYER)
    private boolean userPlayer;


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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getYearsPlayed() {
        return yearsPlayed;
    }

    public void setYearsPlayed(int yearsPlayed) {
        this.yearsPlayed = yearsPlayed;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getJump() {
        return jump;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDribble() {
        return dribble;
    }

    public void setDribble(int dribble) {
        this.dribble = dribble;
    }

    public int getPostPlay() {
        return postPlay;
    }

    public void setPostPlay(int postPlay) {
        this.postPlay = postPlay;
    }

    public int getIntShoot() {
        return intShoot;
    }

    public void setIntShoot(int intShoot) {
        this.intShoot = intShoot;
    }

    public int getExtShoot() {
        return extShoot;
    }

    public void setExtShoot(int extShoot) {
        this.extShoot = extShoot;
    }

    public int getOffensiveRebounding() {
        return offensiveRebounding;
    }

    public void setOffensiveRebounding(int offensiveRebounding) {
        this.offensiveRebounding = offensiveRebounding;
    }

    public int getSteal() {
        return steal;
    }

    public void setSteal(int steal) {
        this.steal = steal;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getIntDefense() {
        return intDefense;
    }

    public void setIntDefense(int intDefense) {
        this.intDefense = intDefense;
    }

    public int getExtDefense() {
        return extDefense;
    }

    public void setExtDefense(int extDefense) {
        this.extDefense = extDefense;
    }

    public int getDefensiveRebounding() {
        return defensiveRebounding;
    }

    public void setDefensiveRebounding(int defensiveRebounding) {
        this.defensiveRebounding = defensiveRebounding;
    }

    public int getMentalToughness() {
        return mentalToughness;
    }

    public void setMentalToughness(int mentalToughness) {
        this.mentalToughness = mentalToughness;
    }

    public int getWorkethic() {
        return workethic;
    }

    public void setWorkethic(int workethic) {
        this.workethic = workethic;
    }

    public int getFriendly() {
        return friendly;
    }

    public void setFriendly(int friendly) {
        this.friendly = friendly;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(int popular) {
        this.popular = popular;
    }

    public int getStrategy() {
        return strategy;
    }

    public void setStrategy(int strategy) {
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
