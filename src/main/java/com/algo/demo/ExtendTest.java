package com.algo.demo;

import java.lang.reflect.Method;

/**
 * Created by yzy on 2016/10/31.
 */
public class ExtendTest {

    public static void main(String[] args){
        //1.test
//        Manager[] managers = new Manager[10];
//        Employee[] employees = managers;
//        employees[0] = new Employee();
//        managers[0].setBonus(1000l); //会发生ArrayStore 错误


        Employee employee = new Employee();
        Manager manager = new Manager();
        //静态方法不能访问实例域（没有this），对象可以调用静态方法，不建议这么调用静态方法
//        System.out.println(employee.getSalaryName());

        System.out.println(Manager.getSalaryName());

        Method[] methods = Manager.class.getDeclaredMethods();
        for(Method method : methods){
            System.out.println(method.getName());
        }


    }
}
