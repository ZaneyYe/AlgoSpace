package com.algo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时器
 * Created by yzy on 2016/6/23.
 */
public class CountDownLatchDemo implements Runnable{

    public static final CountDownLatch end = new CountDownLatch(10);

    public static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
//            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() +  " checked completed");
            end.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exc = Executors.newFixedThreadPool(10);
        for(int i = 0;i < 10;i++){
            Thread.sleep(1000);
            exc.submit(demo);
        }
        end.await();
        System.out.println("发射");
    }
}
