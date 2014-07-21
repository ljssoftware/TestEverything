package com.testeverything.concurrency.test;

import com.testeverything.common.LogHelper;

/**
 * 测试日志信息
 * Created by lijinsheng on 14-6-26.
 */
public class TestLogger {
    public static void main(String[] args) {
        LogHelper.CONCURRENCY_LOGGER.info("test the log4j file");
    }
}
