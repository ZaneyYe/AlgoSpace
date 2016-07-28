package com.algo;

/**
 * Created by yzy on 2016/6/24.
 */
public class SingleDemo {

    private SingleDemo() {}

    private static class SingleDemoHolder{
        public static SingleDemo demo = new SingleDemo();
    }

    public static SingleDemo getInstance(){
        return SingleDemoHolder.demo;
    }

    public static void main(String[] args){
        SingleDemo.getInstance();
    }
}
