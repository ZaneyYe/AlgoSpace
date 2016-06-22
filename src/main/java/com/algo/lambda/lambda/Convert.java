package com.algo.lambda.lambda;

/**
 * Created by yzy on 2016/2/22.
 */
@FunctionalInterface
public interface Convert<S,T> {
    T convert(S arg);
}
