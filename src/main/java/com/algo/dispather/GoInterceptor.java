package com.algo.dispather;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by yzy on 2016/4/15.
 */
public class GoInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
        System.out.println("GoGo 拦截 =====");
        return methodProxy.invoke(o,objects);
    }
}
