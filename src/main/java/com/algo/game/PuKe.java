package com.algo.game;

/**
 * Created by yzy on 2016/2/26.
 * 扑克牌
 */
public class PuKe {

    public static String[] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    public static String[] color = {"♥","♦","♣","♠"};

    private Integer v; //扑克牌的点数

    private Integer t; //扑克牌的花色

    public PuKe(Integer v, Integer t) {
        this.v = v;
        this.t = t;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return color[t]+" "+values[v];
    }
}
