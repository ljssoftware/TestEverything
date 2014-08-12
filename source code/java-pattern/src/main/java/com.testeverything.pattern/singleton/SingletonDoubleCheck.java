package com.testeverything.pattern.singleton;

import com.testeverything.common.LogHelper;

/**
 * 使用double check的方式 创建 单例
 * Created by lijinsheng on 14-8-12.
 */
public class SingletonDoubleCheck {
    private static volatile SingletonDoubleCheck singletonDoubleCheck;

    private SingletonDoubleCheck() {
    }

    public static SingletonDoubleCheck getInstance() {
        if (singletonDoubleCheck != null) {
            return singletonDoubleCheck;
        }

        synchronized (SingletonDoubleCheck.class) {
            if (singletonDoubleCheck == null) {
                singletonDoubleCheck = new SingletonDoubleCheck();
            } else {
                LogHelper.CONCURRENCY_LOGGER.info("尝试创建已经存在的");
            }
        }
        return singletonDoubleCheck;
    }
}
