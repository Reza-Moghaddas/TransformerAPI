package com.equilibrium.transformerapi.model;

import javax.persistence.*;

@Entity
@Table(name = "transformers")
public class Transformer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private TransformerType type;

    @Column(name = "strength")
    Integer strength;
    @Column(name = "intelligence")
    Integer intelligence;
    @Column(name = "speed")
    Integer speed;
    @Column(name = "endurance")
    Integer endurance;
    @Column(name = "rank")
    Integer rank;
    @Column(name = "courage")
    Integer courage;
    @Column(name = "firepower")
    Integer firepower;
    @Column(name = "skill")
    Integer skill;
    @Column(name = "overallRate")
    Integer overallRate;

    public Transformer(String name, TransformerType type, Integer strength, Integer intelligence, Integer speed,
                       Integer endurance, Integer rank, Integer courage, Integer firepower, Integer skill) {
        this.name = name;
        this.type = type;
        this.strength = strength;
        this.intelligence = intelligence;
        this.speed = speed;
        this.endurance = endurance;
        this.rank = rank;
        this.courage = courage;
        this.firepower = firepower;
        this.skill = skill;
    }

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

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getCourage() {
        return courage;
    }

    public void setCourage(Integer courage) {
        this.courage = courage;
    }

    public Integer getFirepower() {
        return firepower;
    }

    public void setFirepower(Integer firepower) {
        this.firepower = firepower;
    }

    public Integer getSkill() {
        return skill;
    }

    public void setSkill(Integer skill) {
        this.skill = skill;
    }

    public Integer getOverallRate() {
        return overallRate;
    }

    public void setOverallRate() {
        this.overallRate = strength + intelligence + speed + endurance + firepower;;
    }

    public TransformerType getType() {
        return type;
    }

    public void setType(TransformerType type) {
        this.type = type;
    }
}

