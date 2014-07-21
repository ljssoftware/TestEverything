package com.testeverything.generic.util;

import java.util.Collection;
import java.util.List;

/**
 * 泛型类
 * Created by lijinsheng on 14-6-25.
 */
public class Generic<T extends Collection, K> {
    private T parameter;
    private K key;

    public T getParameter() {
        return parameter;
    }

    public void setParameter(T parameter) {
        this.parameter = parameter;
    }


    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void showParameterType() {
        System.out.println("showParameterType:" + parameter.getClass().getName());
    }

    public void showKeyType() {
        System.out.println("showKeyType:" + key.getClass().getName());
    }

    public void testExtendGeneric(T parameter) {
        System.out.println(parameter);
    }
}
