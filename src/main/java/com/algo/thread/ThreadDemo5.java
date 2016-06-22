package com.algo.thread;

/**
 * Created by yzy on 2016/6/22.
 */
public class ThreadDemo5 implements Runnable {

    static int i = 0;

    @Override
    public void run() {
        increase();
    }

    private static synchronized void increase() {
        for(int j=0;j < 100;j++){
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ThreadDemo5());
        Thread t2 = new Thread(new ThreadDemo5());
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println("i:" + i);
    }
}
