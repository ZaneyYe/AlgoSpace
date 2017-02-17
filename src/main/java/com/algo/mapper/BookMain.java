package com.algo.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * Created by yzy on 2017/2/13.
 */
public class BookMain {

    public static void main(String[] args) throws JsonProcessingException {
        //测试1
//        ObjectMapper mapper = new ObjectMapper();
//
//        mapper.setFilterProvider(new SimpleFilterProvider().addFilter("book",SimpleBeanPropertyFilter.filterOutAllExcept("id","name")));
//
//        String include = mapper.writeValueAsString(new Book());
//
//        System.out.println(include);
//
//        mapper.setFilterProvider(new SimpleFilterProvider().addFilter("book",SimpleBeanPropertyFilter.serializeAllExcept("id","name")));
//
//        String filter = mapper.writeValueAsString(new Book());
//
//        System.out.println(filter);

        //测试2
        CustomerJsonSerializer serializer = new CustomerJsonSerializer();
//        serializer.filter(Article.class,"id,name",null);
        serializer.filter(Article.class,"name",null);
        System.out.println(serializer.toJson(new Article()));


    }
}
