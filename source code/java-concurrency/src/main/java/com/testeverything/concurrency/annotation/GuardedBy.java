package com.testeverything.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 共享状态 被什么保护着
 * Created by lijinsheng on 14-7-3.
 */
@Target(ElementType.FIELD)
public @interface GuardedBy {
    public String value();
}
