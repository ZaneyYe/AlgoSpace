package com.algo.thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by yzy on 2018/7/16.
 * email: mia5121@163.com
 */
public class ThreadPip {

	public static void main(String[] args) throws IOException {
		PipedOutputStream out = new PipedOutputStream();
		PipedInputStream in = new PipedInputStream();
		in.connect(out);
		Thread inputThread = new Thread(new PipInputPrint(in),"inputThread-1");
//		Thread outThread = new Thread(new PipOutputPrint(out),"outputThread-2");
		inputThread.start();
		int index = 0;
		byte[] buf = new byte[1024];
		try {
			while ((index=System.in.read(buf)) != -1){
				out.write(buf,0,index);
			}
		} finally {
			out.close();
		}
	}


}
