package com.algo.fuck;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yzy on 2016/11/3.
 */
public class Fuck  implements Runnable{

    public static void main(String[] args){
       for(int i = 1;i < 100;i++){
           new Thread(new Fuck()).start();
       }
    }

    @Override
    public void run() {
        HTTPLongClient4 client = new HTTPLongClient4(50000);
        for(;;){
            String s = client.get("http://www.3856.cc/");
            if(StringUtils.isNotBlank(s)){
                System.out.println("get");
            }
        }
    }
}
