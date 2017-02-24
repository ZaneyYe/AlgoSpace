package com.algo.algo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yzy on 2016/6/3.
 */
public class Main {

    public static void main(String[] args){
       List<String> strs = new ArrayList<>();
//       printList(strs);

//        List<Object> obs = new ArrayList<String>();
    }

    private static void printList(List<Object> obs) {
        Iterator<Object> iterator = obs.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
