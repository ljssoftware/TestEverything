package com.testeverything.zookeeper;

import com.testeverything.common.LogHelper;
import org.apache.zookeeper.ZooKeeper;

/**
 * zookeeper Client 的相关操作,  包括在初始化的时候连接上数据库， 并且在destroy 方法里面 管理zookeeper的相关配置
 * Created by lijinsheng on 14-8-5.
 */
public class ZookeeperClient {
    private static final String IP = "192.168.215.128:2181,192.168.215.129:2181"; //zookeeper的IP地址， 所有的服务器信息
    private static final int SESSION_TIME_OUT = 500000; // 连接超时时间, 单位是毫秒
    private ZooKeeper zooKeeper = null;
    //    管理的用户名 密码的相关配置
    private static final String ADMIN_USER_NAME = "admin_username"; // 管理者的用户名
    private static final String ADMIN_PASSWORD = "admin_password"; //管理者的密码
    //监控的根目录， 在这个目录下面创建监控相关的内容
    private static final String NODE_PATH = "/testRootPathDuplicate";

    public void init() {
        try {
            if (zooKeeper == null) {
                zooKeeper = new ZooKeeper(IP, SESSION_TIME_OUT, null);
                zooKeeper.register(new ClientWatcher(zooKeeper));
                //连接上之后 添加客户端验证信息 并添加根节点的监控数据
                zooKeeper.addAuthInfo("digest", (ADMIN_USER_NAME + ":" + ADMIN_PASSWORD).getBytes());

                // 监控子节点的变化
                zooKeeper.getChildren(NODE_PATH, true);
                //监控父节点的数据变化
                zooKeeper.exists(NODE_PATH, true);
            }
        } catch (Exception e) {
            LogHelper.ZOOKEEPER_LOGGER.error("ZookeeperClient 连接zookeeper 失败", e);
        }
    }

    public void destroy() {
        if (zooKeeper != null) {
            try {
                LogHelper.ZOOKEEPER_LOGGER.info("ZookeeperClient 正在关闭zookeeper的连接");
                zooKeeper.close();
            } catch (InterruptedException e) {
                LogHelper.ZOOKEEPER_LOGGER.error("ZookeeperClient 关闭 zookeeper 连接失败", e);
            }
        }
    }
}
