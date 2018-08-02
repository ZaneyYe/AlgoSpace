package com.algo.thread;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * Created by yzy on 2018/7/16.
 * email: mia5121@163.com
 */
public class PipInputPrint implements Runnable {

	private PipedInputStream inputStream;;

	public PipInputPrint(PipedInputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public void run() {
		int index = 0;
		byte[] buf = new byte[1024];
		try {
			while ((index = inputStream.read(buf)) != -1){
				System.out.println(Thread.currentThread().getName() + new String(buf,0,index));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
