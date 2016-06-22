package com.algo.dispather;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by yzy on 2016/4/15.
 */
public class LearnCartInterceptor implements MethodInterceptor {
    private LearnCar learnCar = new LearnCar() {
        @Override
        public void guofei(int n) {
            System.out.println("GGGGGGGGGG");
        }
    };

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
        System.out.println("拦截once,干---------");
        int n = (int) objects[0];
//        if (n < 10) {
//            return    methodProxy.invoke(o,new Object[]{n*10});
//        }else {
//            return methodProxy.invokeSuper(o, objects);
//        }
        return  methodProxy.invoke(learnCar,objects);
    }
}
