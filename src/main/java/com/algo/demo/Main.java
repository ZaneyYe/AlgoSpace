package com.algo.demo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yzy on 2016/2/16.
 */
public class Main {

    public static void main(String[] args){
//        String[] arr = new String[]{"aa","足够","1234"};
//        for(String str : arr){
//            System.out.println(str);
//        }
//        System.out.println(new BigDecimal(14).divide(new BigDecimal(36),2).multiply(new BigDecimal(28)));
        int[] arr = new int[]{1,2,3,6,345};
        System.out.println(Arrays.toString(arr));

        List<String> list = Arrays.asList("yzy", "cjj", "ql", "qb");
        System.out.println(list);

        System.out.println(Arrays.asList(new AClass("yzy"),new AClass("bb")));
    }
}
