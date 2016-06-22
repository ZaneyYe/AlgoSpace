package com.algo.lambda.parallelstream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by yzy on 2016/2/23.
 */
public class Main {

    public static void main(String[] args){
        int max = 10000;
        List<String> values = new ArrayList<>(max);
        for(int i = 0;i < values.size();i++){
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        test1(values);
        test2(values);
    }

    public static void test1(List<String> strs){
        long t0 = System.nanoTime();
        long count = strs.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();

        long ms = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println("cost minus:  "+ ms+ " ms");
    }

    public static void test2(List<String> strs){
        long t0 = System.nanoTime();
        long count = strs.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();

        long ms = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println("cost minus:  "+ ms+ " ms");
    }
}
