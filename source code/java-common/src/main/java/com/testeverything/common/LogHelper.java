package com.testeverything.common;


import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
 * 打印日志的帮助类
 * Created by lijinsheng on 14-6-26.
 */
public class LogHelper {
    //并发的日志信息
    private static final String CONCURRENCY_LOGGER_NAME = "CONCURRENCY";
    public static final Logger CONCURRENCY_LOGGER = Logger.getLogger(CONCURRENCY_LOGGER_NAME);
    //NIO的日志信息
    private static final String NIO_LOGGER_NAME = "NIO";
    public static final Logger NIO_LOGGER = Logger.getLogger(NIO_LOGGER_NAME);
}
