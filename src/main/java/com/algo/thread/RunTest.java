package com.algo.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by yzy on 2018/7/14.
 * email: mia5121@163.com
 */
public class RunTest {

	public static void main(String[] args) throws InterruptedException {
		Runner one = new Runner();
		Thread countThread = new Thread(one, "countThreadOne");
		countThread.start();
		TimeUnit.SECONDS.sleep(1);
		countThread.interrupt();

		Runner two = new Runner();
		Thread countThreadTwo = new Thread(two, "countThreadTwo");
		countThreadTwo.start();
		TimeUnit.SECONDS.sleep(1);
		two.cancle();

	}


	public static class Runner implements Runnable{
		private long i;
		private volatile boolean flag = true;
		@Override
		public void run() {
			while (flag && !Thread.currentThread().isInterrupted()){
				i++;
			}
			System.out.println("Count i :" +  i);
		}

		public void cancle(){
			flag = false;
		}

	}


}
