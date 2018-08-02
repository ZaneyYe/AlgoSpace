package com.algo.thread;

/**
 * Created by yzy on 2018/7/14.
 * email: mia5121@163.com
 */
public class ThreadTestTwo {

	public static void main(String[] args) throws InterruptedException {
//		MyThread t2 = new MyThread();
//		t2.start();
//
//		System.out.println(t2.isInterrupted());
//		System.out.println(t2.getState());
//
//		t2.interrupt();
//		Thread.sleep(1000);
//		t2.interrupt();
//		System.out.println(t2.getState());
//		System.out.println(t2.isInterrupted());
		MyThread2 tt2 = new MyThread2();
		tt2.start();
		tt2.interrupt();




	}
}
