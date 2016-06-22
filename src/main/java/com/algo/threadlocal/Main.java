package com.algo.threadlocal;

/**
 * Created by yzy on 2016/2/17.
 */
public class Main {

    public ThreadLocal<String> threadLocal1 = new ThreadLocal();

    public ThreadLocal<String> threadLocal2 = new ThreadLocal();

    public void set(){
        threadLocal1.set("中国您好" + Thread.currentThread().getId());
        threadLocal2.set("世界您好" + Thread.currentThread().getId());
    }

    public String get1(){
        return threadLocal1.get();
    }

    public String get2(){
        return threadLocal2.get();
    }

    public static void main(String[] args){
        final Main test = new Main();
        test.set();
        System.out.println(test.get1());
        System.out.println(test.get2());

        Thread thread  = new Thread(){
            public void run() {
                test.set();
                System.out.println(test.get1());
                System.out.println(test.get2());
            }
        };

        thread.start();
    }
}
