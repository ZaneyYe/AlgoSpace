package com.algo.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by yzy on 2016/11/3.
 */
public class TestTwo {

    public static void main(String[] args) throws JsonProcessingException {
        Student s = new Student();
//        s.setAge(10);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(s);
        System.out.println(jsonStr);
    }
}
