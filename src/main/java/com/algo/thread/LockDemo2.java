package com.algo.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 超时等待
 * Created by yzy on 2016/6/22.
 */
public class LockDemo2 implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
            } else {
                System.out.println("get lock failed, shut " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                System.out.println("lock release " + Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args){
        LockDemo2 demo2 = new LockDemo2();
        Thread t1 = new Thread(demo2);
        Thread t2 = new Thread(demo2);
        t1.start();
        t2.start();
    }
}

