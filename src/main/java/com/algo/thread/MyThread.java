package com.algo.thread;

/**
 * Created by yzy on 2018/7/14.
 * email: mia5121@163.com
 */
public class MyThread extends Thread{

	@Override
	public void run() {
		while (true){
			//判断标志位被标记，立即退出
			try {
				Thread.sleep(5000);
				if(Thread.currentThread().isInterrupted()){
					System.out.println("你已经被包围了");
					break;
				}
			} catch (InterruptedException e) {
				System.out.println("谁在叫醒我，mmd");
				System.out.println("ppppp:" + Thread.currentThread().isInterrupted());
				break;
//				if(Thread.currentThread().isInterrupted()){
//					System.out.println("你又是被包围了");
////					System.out.println(/**/);
//					break;
//				}
			}
		}
	}
}
