package com.algo.dispather;



import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by yzy on 2016/4/15.
 */
public class LearnCallBackFilter implements CallbackFilter {

    @Override
    public int accept(Method method) {
        if("backCar".equals(method.getName()))
            return 0;
        if("ceStop".equals(method.getName()))
            return 1;
        if("stopThenGo".equals(method.getName()))
            return 2;
        if("guofei".equals(method.getName()))
            return 0;
        return 1;
    }

}
