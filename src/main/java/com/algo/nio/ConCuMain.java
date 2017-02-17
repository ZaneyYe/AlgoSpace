package com.algo.nio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzy on 2016/11/28.
 */
public class ConCuMain {

    static  ConCu conCu = new ConCu();

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ConCuMain.conCu.getStrs());
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ConCuMain.conCu.getStrs());
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    List<String> stringList = new ArrayList<>();
                    stringList.add("demo");
                    ConCuMain.conCu.setStrs(stringList);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println(ConCuMain.conCu.getStrs());
    }

}
