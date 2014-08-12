package com.testeverything.pattern.singleton;

import com.testeverything.common.LogHelper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lijinsheng on 14-8-12.
 */
public class GetSingletonRunnable implements Runnable {
    private CountDownLatch startCount;
    private CountDownLatch endCount;

    public GetSingletonRunnable(CountDownLatch startCount, CountDownLatch endCount) {
        this.startCount = startCount;
        this.endCount = endCount;
    }

    @Override
    public void run() {
        try {
            startCount.await();
        } catch (InterruptedException e) {
            LogHelper.CONCURRENCY_LOGGER.info("startCount wait exception", e);
        }
        Singleton singleton = Singleton.getInstance();
        LogHelper.CONCURRENCY_LOGGER.info(singleton);
        endCount.countDown();
    }
}
