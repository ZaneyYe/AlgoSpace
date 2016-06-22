package com.algo.lambda.lambda;

/**
 * Created by yzy on 2016/2/22.
 */
@FunctionalInterface
public interface PersonFactory<P extends Person>{
    P createPerson(String name,Integer age);
}
