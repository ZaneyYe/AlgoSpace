package com.algo.demo;

/**
 * Created by yzy on 2016/10/31.
 */
public class Manager extends Employee {

    private Long bonus;

    public Long getBonus() {
        return bonus;
    }

    public void setBonus(Long bonus) {
        this.bonus = bonus;
    }

    public static String getSalaryName2(){
        return  "sb";
    }

}
