package com.algo.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yzy on 2016/9/1.
 */
public class ReflectTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        Method[] methods = student.getClass().getDeclaredMethods();
        AccessibleObject.setAccessible(methods,true);
        for(Method method : methods){
            if(method.getName().equals("getTeacherName")){
                String str = (String) method.invoke(student);
                System.out.println(str);
            }
        }


    }
}
