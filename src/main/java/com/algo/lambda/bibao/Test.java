package com.algo.lambda.bibao;

/**
 * Created by yzy on 2016/2/22.
 */
public class Test {

    public static Supplier<Integer> testClosure(){
        final int i = 1;
        return new Supplier<Integer>() {
            public Integer get() {
                return i;
            }
        };
    }

    public static void main(String[] args){
        System.out.println(testClosure().get());
    }
}
