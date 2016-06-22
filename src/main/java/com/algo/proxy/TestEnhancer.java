package com.algo.proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by yzy on 2016/4/15.
 */
public class TestEnhancer {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestEnhancer.class);
        enhancer.setCallback(new StrongRun());
        TestEnhancer test = (TestEnhancer) enhancer.create();
        test.show();
    }

    private void show() {
        System.out.println("CCCCCCCC");
    }


}
