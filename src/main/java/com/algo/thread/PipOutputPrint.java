package com.algo.thread;

import java.io.PipedOutputStream;

/**
 * Created by yzy on 2018/7/16.
 * email: mia5121@163.com
 */
public class PipOutputPrint implements Runnable {

	private PipedOutputStream outputStream;

	public PipOutputPrint(PipedOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void run() {

	}

}
