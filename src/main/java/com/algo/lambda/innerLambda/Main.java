package com.algo.lambda.innerLambda;

import com.algo.lambda.bibao.Supplier;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by yzy on 2016/2/23.
 */
public class Main {

    public static void main(String[] args){
        Predicate<String> predicate = s -> s.length() > 0;
        System.out.println(predicate.test("123"));

        Predicate<Student> studentPredicate = student -> student.getName().equals("小明")&&student.getSex() == 23;
        Student student = new Student("小花",23);
        System.out.println(studentPredicate.test(student));

        Function<String,Integer> toInteger = Integer::valueOf;
        System.out.println(toInteger.apply("1234"));

        Function<String,String> backToString = toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("12345"));

        Supplier<Student> studentSupplier = Student::new;
        Student stu = studentSupplier.get();
        stu.setName("哈哈");
        stu.setSex(2);
        System.out.println(stu);

        Consumer<Student> studentConsumer = student1 -> System.out.println("Hello"+student1.getName());
        studentConsumer.accept(new Student("习近平",66));


    }
}
