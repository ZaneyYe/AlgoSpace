package com.algo.lambda.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzy on 2016/2/23.
 */
public class Main {

    public static void main(String[] args){
        Map<Integer,String> map = new HashMap<>();
        for(int i = 0; i < 10;i++){
            map.putIfAbsent(i,"val:"+i);
        }
        map.forEach((integer, s) -> System.out.println(s));

//        map.computeIfPresent(3,((num, s) -> s + num));
//        System.out.println(map.get(3));

        map.computeIfPresent(3,((num,val) -> val + num));
        System.out.println(map.get(3));

//        int de$er = 23;
//        System.out.println(de$er);


    }
}
