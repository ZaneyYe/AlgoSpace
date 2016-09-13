package com.algo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * Created by yzy on 2016/9/1.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Student t = new Student();
        System.out.println(t.getClass().getName());

        Class<?> tClass = Class.forName(t.getClass().getName());
        System.out.println(tClass == Student.class);

        try {
            Field name = t.getClass().getDeclaredField("name");
            Class<?> fieldClass = name.getType();
            System.out.println(fieldClass.getName());
            System.out.println(fieldClass == String.class);

            System.out.println(Modifier.toString(name.getModifiers()));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println("*********************************************");

        System.out.println(Modifier.toString(tClass.getModifiers()));

        Field[] fields = tClass.getDeclaredFields();
        for(Field field : fields){
            Class<?> fieldType = field.getType();
            System.out.print(Modifier.toString(field.getModifiers())+" ");
            System.out.print(fieldType.getSimpleName() + " ");
            System.out.println(field.getName());
        }

        System.out.println("*************************************************");

        Method[] methods = tClass.getDeclaredMethods();
        for(Method method : methods){
            System.out.print(Modifier.toString(method.getModifiers()) + " ");
            Class<?> returnType = method.getReturnType();
            System.out.print(returnType.getSimpleName() + " ");
            System.out.print(method.getName() + "(");
            Parameter[] methodParameters = method.getParameters();
            if(methodParameters.length == 0){
                System.out.println(")");
                continue;
            }
            for(int i = 0;i < methodParameters.length;i++){
                if(i > 0){
                    System.out.print(", ");
                }
                Class<?> parameterType = methodParameters[i].getType();
                System.out.print(parameterType.getSimpleName() + " ");
                System.out.print(methodParameters[i].getName());
            }
            System.out.println(")");
        }

        System.out.println("********************************************");

        t.setSex(2);
        try {
            Field sex = tClass.getDeclaredField("sex");
            sex.setAccessible(true);
            try {
                Object o = sex.get(t);
                System.out.println(o.toString());

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
