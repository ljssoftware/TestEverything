package com.testeverything.concurrency.test;

import com.testeverything.common.LogHelper;
import com.testeverything.concurrency.particle.IThreadGroup;

/**
 * 测试ThreadGroup
 * Created by lijinsheng on 14-7-10.
 */
public class TestThreadGroup {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LogHelper.CONCURRENCY_LOGGER.info("before throw Exception");
                throw new RuntimeException("用于测试ThreadGroup的 unCaughtException 方法");
            }
        };

//        IThreadGroup iThreadGroup = new IThreadGroup("我的ThreadGroup");
        Thread thread = new Thread(runnable, "Thread test");
        thread.start();

        ThreadGroup threadGroup = thread.getThreadGroup();
        LogHelper.CONCURRENCY_LOGGER.info("获取的ThreadGroup:" + threadGroup.getName());

        Thread[] threads = new Thread[10];
        int copyCount = threadGroup.enumerate(threads);
        LogHelper.CONCURRENCY_LOGGER.info("获取的线程数量:" + copyCount);

        for (int i = 0; i < copyCount; i++) {
            LogHelper.CONCURRENCY_LOGGER.info("thread Name:" + threads[i].getName());
        }
    }
}
