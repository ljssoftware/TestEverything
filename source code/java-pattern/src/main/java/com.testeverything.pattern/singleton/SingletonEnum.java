package com.testeverything.pattern.singleton;

/**
 * 单例 模式的 Enum
 * Created by lijinsheng on 14-8-12.
 */
public enum SingletonEnum {
    INSTANCE;

    public static SingletonEnum getInstance() {
        return INSTANCE;
    }
}
