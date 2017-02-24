package com.algo.producerandcustomer;

/**
 * Created by yzy on 2017/2/17.
 */
public class Producer implements Runnable{

    @Override
    public void run() {
        synchronized (Customer.o){
//            获取锁
            Customer.flag = true;
            Customer.o.notify();
            System.out.println(Thread.currentThread() + "begin producing");
        }
    }

}
