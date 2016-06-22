package com.algo.dispather;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * Created by yzy on 2016/4/15.
 */
public class Main {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LearnCar.class);
        enhancer.setCallbackFilter(new LearnCallBackFilter());

        Callback interceptor1 = new LearnCartInterceptor();
        Callback interceptor2 = new GoInterceptor();
        Callback callback3 = NoOp.INSTANCE;
        Callback[] callbacks = new Callback[]{interceptor1,interceptor2,callback3};

        enhancer.setCallbacks(callbacks);
        LearnCar learnCar = (LearnCar) enhancer.create();

//        learnCar.ceStop();
//
//        learnCar.backCar();
//
//        learnCar.stopThenGo();
        learnCar.guofei(2);
    }
}
