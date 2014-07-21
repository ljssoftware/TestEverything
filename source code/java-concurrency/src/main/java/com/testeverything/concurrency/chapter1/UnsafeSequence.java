package com.testeverything.concurrency.chapter1;

import com.testeverything.concurrency.annotation.NotThreadSafe;

/**
 * 非安全的 序列类
 * Created by lijinsheng on 14-6-26.
 */
@NotThreadSafe(key = "key", value = "value")
public class UnsafeSequence {
    private static int value = 0;

    public static int getNext() {
        return value++;
    }
}
