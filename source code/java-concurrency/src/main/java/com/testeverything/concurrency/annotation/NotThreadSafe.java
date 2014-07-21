package com.testeverything.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 表明类不是 线程安全的
 * Created by lijinsheng on 14-6-26.
 */
@Target(ElementType.TYPE)
public @interface NotThreadSafe {
    /**
     * 备注信息
     *
     * @return message
     */
    public String key();

    /**
     * message2 备注信息
     *
     * @return
     */
    public String value();
}
