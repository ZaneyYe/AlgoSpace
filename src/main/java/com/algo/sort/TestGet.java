package com.algo.sort;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by yzy on 2016/8/8.
 */
public class TestGet {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String property = BeanUtils.getProperty(new Man(), "info");
        System.out.println(property);
    }
}
