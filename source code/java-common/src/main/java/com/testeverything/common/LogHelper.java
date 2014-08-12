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

    //Zookeeper的日志信息
    private static final String ZOOKEEPER_LOGGER_NAME = "ZOOKEEPER";
    public static final Logger ZOOKEEPER_LOGGER = Logger.getLogger(ZOOKEEPER_LOGGER_NAME);

    //一致性hash的日志信息
    private static final String CONSISTENT_LOGGER_NAME = "CONSISTENT";
    public static final Logger CONSISTENT_LOGGER = Logger.getLogger(CONSISTENT_LOGGER_NAME);
}
