package com.algo.lambda.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by yzy on 2016/2/22.
 */
public class Main {

    public static void main(String[] args){
        List<String> names = Arrays.asList("china","america","japan","gege");
        Collections.sort(names,(String a,String b) -> a.compareToIgnoreCase(b));
        System.out.println(names);

        Convert<String,Integer> convert = (arg -> Integer.valueOf(arg));
//        convert.convert("123");
        System.out.println(convert.convert("1234"));

        Convert<String,Integer> convert2 = Integer::valueOf;
        System.out.println(convert2.convert("23425"));

        ConvertClass convertClass = new ConvertClass();
        Convert<String,Integer> convert3 = convertClass::convertString;
        System.out.println(convert3.convert("2016"));

        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.createPerson("小明", 23);
        System.out.println(person);

        int num = 1;
        Convert<Integer,String> convert4 = (arg -> String.valueOf(num+arg));
        System.out.println(convert4.convert(12));

    }
}
