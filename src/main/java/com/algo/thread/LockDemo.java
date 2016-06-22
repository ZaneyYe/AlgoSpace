package com.algo.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 中断响应
 * Created by yzy on 2016/6/22.
 */
public class LockDemo implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int flag;

    public LockDemo(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            if(flag == 1){
                lock1.lockInterruptibly();
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName()+" want get lock2");
                lock2.lockInterruptibly();
            }else{
                lock2.lockInterruptibly();
                Thread.sleep(500);
                System.out.print(Thread.currentThread().getName() + " want get lock1");
                lock1.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "get lock1");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + "线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo l1 = new LockDemo(1);
        LockDemo l2 = new LockDemo(2);
        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l2);
        t1.start();
        t2.start();
        Thread.sleep(5000);
        t1.interrupt();
    }
}
