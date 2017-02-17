package com.algo.nio;

import java.util.List;

/**
 * Created by yzy on 2016/11/28.
 */
public class ConCu {
    private boolean ready = false;

    private List<String> stringList;

    public synchronized List<String> getStrs(){
        while(!ready){
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        return stringList;
    }

    public synchronized void setStrs(List<String> names) {
        this.stringList = names;
        this.ready = true;
        notifyAll();
    }


}
