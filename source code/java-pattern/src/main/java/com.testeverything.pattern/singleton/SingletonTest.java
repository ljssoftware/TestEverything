package com.testeverything.pattern.singleton;

import com.testeverything.common.LogHelper;

import java.util.concurrent.CountDownLatch;

/**
 * 单例模式的测试类
 * Created by lijinsheng on 14-8-12.
 */
public class SingletonTest {

    public static void main(String[] args) {
        CountDownLatch startCount = new CountDownLatch(1);
        CountDownLatch endCount = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new GetSingletonRunnable(startCount, endCount));
            thread.start();
        }

        startCount.countDown();
        try {
            endCount.await();
        } catch (InterruptedException e) {
            LogHelper.CONCURRENCY_LOGGER.info("end count wait exception", e);
        }
    }
}
