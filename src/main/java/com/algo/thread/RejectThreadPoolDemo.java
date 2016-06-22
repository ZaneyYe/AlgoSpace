package com.algo.thread;

import java.util.concurrent.*;

/**
 * Created by yzy on 2016/6/21.
 */
public class RejectThreadPoolDemo {

    public static class Mytask implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "thread id :" + Thread.currentThread().getId());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Mytask mytask = new Mytask();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10), Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + "is discard");
                    }
                });

        for(int i = 0;i < 100;i++){
            es.submit(mytask);
            Thread.sleep(10);
        }
    }
}
