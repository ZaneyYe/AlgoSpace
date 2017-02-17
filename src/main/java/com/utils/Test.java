package com.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by yzy on 2016/12/1.
 */
public class Test {

    public static String testGoGo(@GetTimeParam("name")@GetDateParam("huhu") String name,@GetTimeParam("age") String age){
        return name.toUpperCase();
    }

    @Demo(1213)
    public void haha(){

    }

    public static void main(String[] args){
        Method[] methods = Test.class.getDeclaredMethods();
        for(Method method : methods){
            if(method.getName().equals("testGoGo")){
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                System.out.println(parameterAnnotations);
                System.out.println(parameterAnnotations.length);
                for(Annotation[] annotations : parameterAnnotations){
                    for(Annotation a : annotations){
//                        System.out.println(a.annotationType().getName());
                        a.annotationType();
                    }
                }
            }
        }
    }
}
