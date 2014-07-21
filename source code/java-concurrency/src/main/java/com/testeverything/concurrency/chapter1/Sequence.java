package com.testeverything.concurrency.chapter1;

import com.testeverything.concurrency.annotation.GuardedBy;
import com.testeverything.concurrency.annotation.ThreadSafe;

/**
 * 单个jvm 的 线程安全的序列
 * Created by lijinsheng on 14-7-3.
 */
@ThreadSafe
public class Sequence {
    @GuardedBy("Sequence.class")
    private static int value;

    public static synchronized int getNext() {
        return value++;
    }
}
