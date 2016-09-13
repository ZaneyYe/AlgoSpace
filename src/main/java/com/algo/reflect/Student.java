package com.algo.reflect;

/**
 * Created by yzy on 2016/9/1.
 */
public class Student {

    private String name;

    private Integer age;

    private int sex;

    public static String getClassName(){
        return "grade one";
    }

    public String getTeacherName(){
        return "毕姥爷";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHello(String hello){
        return "Hello World";
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
