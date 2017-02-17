package com.service.demo;

import org.springframework.context.annotation.Configuration;

/**
 * Created by yzy on 2017/1/16.
 */
@Configuration
public class Bean {


    @org.springframework.context.annotation.Bean
    public Student getStudent(){
        return new Student("矽晶片",78);
    }


}
