package com.algo.proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by yzy on 2016/3/24.
 */
public class CglibProxy2 {

    public Object getProxy(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new StrongRun());
        return enhancer.create();
    }
}
