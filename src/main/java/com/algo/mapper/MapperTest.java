package com.algo.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by yzy on 2016/3/24.
 */
public class MapperTest {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,false);
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider)
                    throws IOException, JsonProcessingException {
                jsonGenerator.writeString("");
            }
        });
        String jsonStr = objectMapper.writeValueAsString(new Student());
        objectMapper.writeValue(new File("e://json"),new Student());
        Student student = objectMapper.readValue(new File("e://json"), Student.class);
        System.out.println(student.getName());
        System.out.println(student.getAge());
//        System.out.println(jsonStr);
    }
}
