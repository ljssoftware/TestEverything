package com.testeverything.zookeeper;

/**
 * zookeeper 的client端测试类
 * Created by lijinsheng on 14-7-25.
 */
public class ZookeeperClientTest {

    public static void main(String[] args) {
        ZookeeperClient zookeeperClient = new ZookeeperClient();
        zookeeperClient.init();
        while (true) {
        }
//        try {
//            TimeUnit.HOURS.sleep(1);
//        } catch (InterruptedException e) {
//            LogHelper.ZOOKEEPER_LOGGER.error("ZookeeperClientTest 线程sleep 被中断", e);
//            zookeeperClient.destroy();
//        }
    }
}
