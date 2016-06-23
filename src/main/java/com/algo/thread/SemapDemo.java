package com.algo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by yzy on 2016/6/23.
 */
public class SemapDemo implements Runnable {
    public static Semaphore semp = new Semaphore(5);

    @Override
    public void run() {
        try {
//            semp.acquire();
            semp.tryAcquire(5, TimeUnit.SECONDS);
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " is doing now");
            semp.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ExecutorService exc = Executors.newFixedThreadPool(20);
        final SemapDemo semapDemo = new SemapDemo();
        for(int i = 0;i < 20;i++){
            exc.submit(semapDemo);
        }
    }
}
