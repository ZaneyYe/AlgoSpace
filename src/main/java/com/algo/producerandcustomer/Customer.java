package com.algo.producerandcustomer;

/**
 * Created by yzy on 2017/2/17.
 */
public class Customer implements Runnable{

    public static volatile boolean flag = false;

    public static volatile Object o = new Object();

    @Override
    public void run() {
        synchronized (o){
            while(!flag){
                try {
                    System.out.println("waiting");
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread() + "begin ...having");
        }
    }
}
