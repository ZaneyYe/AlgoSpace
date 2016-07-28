package com.algo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yzy on 2016/7/26.
 */
public class CopyFile {

    public static void main(String[] args){
        try {
            FileInputStream is = new FileInputStream("E://1.txt");
            FileOutputStream os = new FileOutputStream("E://2.txt");

            FileChannel inChannel = is.getChannel();
            FileChannel outChannel = os.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(1024);
            while(true){
                buf.clear();

                int r = inChannel.read(buf);
                if(r == -1)
                    break;
                buf.flip();

                outChannel.write(buf);

                buf.compact();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
