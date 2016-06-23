package com.algo.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yzy on 2016/6/23.
 */
public class TryLock implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    private int flag;
    public TryLock(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if(flag == 1){
            while(true){
                if(lock1.tryLock()){
                    try {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(lock2.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getName() + " is done");
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            }
        }else{
            while(true){
                if(lock2.tryLock()){
                    try {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(lock1.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getName() + " is done");
                                return;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        TryLock r1 = new TryLock(1);
        TryLock r2 = new TryLock(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
