package com.algo.thread;

/**
 * Created by yzy on 2016/6/21.
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while(true){
                    System.out.println(Thread.currentThread().getName());
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupted");
                        break;
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
