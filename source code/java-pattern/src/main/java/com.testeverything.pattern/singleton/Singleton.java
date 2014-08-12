package com.testeverything.pattern.singleton;

/**
 * 单例 的类
 * Created by lijinsheng on 14-8-12.
 */
public class Singleton {
    private static Singleton instance;

    public Singleton() {
    }

    public static Singleton getInstance() {
        if (instance != null) {
            return instance;
        }
        
        instance = new Singleton();
        return instance;
    }
}
