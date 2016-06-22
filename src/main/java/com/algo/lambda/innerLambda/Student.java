package com.algo.lambda.innerLambda;

/**
 * Created by yzy on 2016/2/23.
 */
public class Student {

    private String name;

    private Integer sex;

    public Student(){}

    public Student(String name, Integer sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
