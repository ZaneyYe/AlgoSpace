package com.algo.thread;

/**
 * Created by yzy on 2016/6/22.
 */
public class ThreadDemo4 implements Runnable{

    static ThreadDemo4 demo4 = new ThreadDemo4();
    static int i = 0;

    @Override
    public void run() {
        for(int j=0;j< 100;j++){
            synchronized (demo4){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(demo4);
        Thread t2 = new Thread(demo4);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println("i : " + i);
    }
}
