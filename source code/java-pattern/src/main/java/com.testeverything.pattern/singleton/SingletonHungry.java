package com.testeverything.pattern.singleton;

/**
 * 恶汉变种 方式 创建的singleton  使用 方法内部的静态类 进行创建， 在多个classLoader时 就不能保证有多个实例
 * Created by lijinsheng on 14-8-12.
 */
public class SingletonHungry {

    /**
     * 私有的构造函数 防止 直接new 对象
     */
    private SingletonHungry() {
    }

    private static class SingletonHolder {
        private static final SingletonHungry INSTANCE = new SingletonHungry();
    }

    public static SingletonHungry getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
