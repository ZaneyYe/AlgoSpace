package com.algo.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yzy on 2016/7/26.
 */
public class Demo2 {

    public static void main(String[] args){
        try {
            FileInputStream is = new FileInputStream("E://1.txt");
            FileChannel fileChannel = is.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(1024);
            fileChannel.read(buf);

            System.out.println(new String(buf.array()));

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
