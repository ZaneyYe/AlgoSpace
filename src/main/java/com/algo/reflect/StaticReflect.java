package com.algo.reflect;

import java.lang.reflect.Field;

/**
 * Created by yzy on 2016/10/31.
 */
public class StaticReflect {

    public static class ATestClass{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


    public static void main(String[] args){
        Field[] fields = ATestClass.class.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getName());
        }
    }



}
