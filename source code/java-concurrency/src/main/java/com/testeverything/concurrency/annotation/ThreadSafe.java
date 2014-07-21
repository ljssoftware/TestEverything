package com.testeverything.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 线程安全的注释
 * Created by lijinsheng on 14-7-3.
 */
@Target(ElementType.TYPE)
public @interface ThreadSafe {
}
