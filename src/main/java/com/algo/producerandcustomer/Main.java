package com.algo.producerandcustomer;

/**
 * Created by yzy on 2017/2/17.
 */
public class Main {

    public static void main(String[] args){
        Thread t1 = new Thread(new Customer(), "customer");
        Thread t2 = new Thread(new Producer(), "producer");
        t1.start();
        t2.start();
    }

}
