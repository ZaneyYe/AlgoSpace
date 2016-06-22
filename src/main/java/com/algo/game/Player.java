package com.algo.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzy on 2016/2/26.
 */
public class Player {
    private String name;
    private Integer age;

    private double rate; //获胜几率

    private List<PuKe> pukes;

    public Player(String name, Integer age) {
        this.name = name;
        this.age = age;
        pukes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PuKe> getPukes() {
        return pukes;
    }

    public void setPukes(List<PuKe> pukes) {
        this.pukes = pukes;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void initial(){
        PuKe p1 = pukes.get(0);
        PuKe p2 = pukes.get(1);
        rate = CountRate.countRate(p1, p2);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pukes=" + pukes +
                '}';
    }
}
