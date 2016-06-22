package com.algo.proxy;

/**
 * Created by yzy on 2016/3/24.
 */
public class TestCglib {

    public static void main(String[] args) {
//        CglibProxy cglibProxy = new CglibProxy();
//        Car carProxy = (Car) cglibProxy.getProxy(Car.class);
//        carProxy.start();


        CglibProxy2 cglibProxy2 = new CglibProxy2();
        Car car2 = (Car) cglibProxy2.getProxy(Car.class);
        car2.run();
    }


}
