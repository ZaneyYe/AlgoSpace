package com.algo.nag;

/**
 * Created by yzy on 2016/8/26.
 */
public class Circle extends Shape {

    public int num = 30;

    private String d = "Hello";

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String getD() {
        return d;
    }

    @Override
    public void setD(String d) {
        this.d = d;
    }
}
