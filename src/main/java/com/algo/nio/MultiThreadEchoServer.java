package com.algo.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yzy on 2016/7/13.
 */
public class MultiThreadEchoServer {
    private static ExecutorService tp = Executors.newFixedThreadPool(10);

    static class HandleMsg implements Runnable{

        Socket clientSocket;

        public HandleMsg(Socket clientSocket){
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;

            try {
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintWriter(clientSocket.getOutputStream(),true);
                //从InputStream 读取数据
                String inputLine = null;
                long b = System.currentTimeMillis();
                while((inputLine = is.readLine()) != null){
                    os.print(inputLine);
                    System.out.println(inputLine);
                }
                long e = System.currentTimeMillis();

                System.out.println("耗时：" + (e-b) + "ms");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(is != null){
                        is.close();
                    }
                    if(os != null){
                        os.close();
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args){
            ServerSocket echoServer = null;
            Socket clientSocket = null;
            try {
                echoServer = new ServerSocket(9000);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while(true){
                try {
                    clientSocket = echoServer.accept();
                    System.out.println(clientSocket.getRemoteSocketAddress() + "connect!");

                    tp.execute(new HandleMsg(clientSocket));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
