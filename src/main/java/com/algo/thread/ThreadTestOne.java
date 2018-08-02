package com.algo.thread;

/**
 * Created by yzy on 2018/7/14.
 * email: mia5121@163.com
 * Thread 中断标识位
 */
public class ThreadTestOne {


	public static void main(String[] args){
		Thread t1 = new Thread();
		System.out.println(t1.getState());

		t1.interrupt();
		System.out.println(t1.isInterrupted());
	}
}
