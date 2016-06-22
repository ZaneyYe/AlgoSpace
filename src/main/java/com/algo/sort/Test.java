package com.algo.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yzy on 2016/3/8.
 */
public class Test {

    public static void main(String[] args){

        List<String> list = new ArrayList<String>();
        list.add("Aplha");
        list.add("BBQ");
        list.add("Amm");
        list.add("China");

        long start = System.currentTimeMillis();
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);

            }
        });
        long end = System.currentTimeMillis();

        System.out.println(list);
        System.out.println(end - start);
    }
}
