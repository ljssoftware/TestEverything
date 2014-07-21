package com.testeverything.concurrency.particle;

import com.testeverything.common.LogHelper;

/**
 * 自己定制的ThreadGroup 用于重写UncaughtException
 * Created by lijinsheng on 14-7-10.
 */
public class IThreadGroup extends ThreadGroup {
    public IThreadGroup(String name) {
        super(name);
    }

    public IThreadGroup(ThreadGroup parent, String name) {
        super(parent, name);
    }

    public void uncaughtException(Thread t, Throwable e) {
        LogHelper.CONCURRENCY_LOGGER.error("method uncaughtException 线程未处理的异常" + t.getName(), e);
    }
}
