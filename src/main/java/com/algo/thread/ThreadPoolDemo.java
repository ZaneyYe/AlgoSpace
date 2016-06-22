package com.algo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yzy on 2016/6/21.
 */
public class ThreadPoolDemo {

    public static class MyTask implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ": Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args){
            MyTask myTask = new MyTask();
//            ExecutorService es = Executors.newFixedThreadPool(4);
            ExecutorService es = Executors.newCachedThreadPool();
            for(int i = 0;i < 10;i++){
                es.submit(myTask);
            }
        }

    }
}
