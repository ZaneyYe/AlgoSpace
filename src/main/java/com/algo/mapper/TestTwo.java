package com.algo.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzy on 2016/11/3.
 */
public class TestTwo {

    public static void main(String[] args) throws IOException {
//        Student s = new Student();
//        s.setAge(10);


        Student s1 = new Student("yzy",26);
        Student s2 = new Student("demo",27);
        Student s3 = new Student("hutu",29);

        List<Student> ss = new ArrayList<>();
        ss.add(s1);
        ss.add(s2);
        ss.add(s3);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(ss);
        System.out.println(jsonStr);

        String jsonStr2 = "[{\"name\":\"yzy\",\"age\":26},{\"name\":\"demo\",\"age\":27},{\"name\":\"hutu\",\"age\":29}]";
//        JsonNode node = mapper.readTree(jsonStr2);
        List list = mapper.readValue(jsonStr2, List.class);
        List<Student> listStus = list;
        System.out.println(listStus.get(0));

    }
}
