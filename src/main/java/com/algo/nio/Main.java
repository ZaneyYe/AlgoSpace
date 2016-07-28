package com.algo.nio;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yzy on 2016/5/30.
 */
public class Main {

    public static void main(String[] args){
        method_1();
    }

    public static void method_1(){
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("E://1.txt","rw");
            FileChannel channel = file.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(1);
            int bytesRead = channel.read(buf);
            System.out.println(bytesRead);
            while(bytesRead != -1){
                buf.flip();
                while(buf.hasRemaining()){
                    System.out.println((char)buf.get());
                }
                buf.compact();
                bytesRead = channel.read(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(file != null){
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
