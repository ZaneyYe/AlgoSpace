package com.algo.sort;

/**
 * Created by yzy on 2016/8/8.
 */
public class Man {

    private String name = "yzy";

    private Long age = 25l;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getInfo(){
        return name + "::::" + age;
    }


}
