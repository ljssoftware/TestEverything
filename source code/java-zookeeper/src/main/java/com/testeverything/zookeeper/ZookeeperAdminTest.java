package com.testeverything.zookeeper;

import com.testeverything.common.LogHelper;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Zookeeper Admin 端的test类， 用于更改数据， 增删节点
 * Created by lijinsheng on 14-7-25.
 */
public class ZookeeperAdminTest {
    private static final String IP = "192.168.215.128:2181,192.168.215.129:2181"; //zookeeper的IP地址， 所有的服务器信息
    private static final int CONNECTION_TIMEOUT = 5000; // 连接超时时间, 单位是毫秒

    //    管理的用户名 密码的相关配置
    private static final String ADMIN_USER_NAME = "admin_username"; // 管理者的用户名
    private static final String ADMIN_PASSWORD = "admin_password"; //管理者的密码

    //访问者的用户名 密码的相关配置
    private static final String GUEST_USER_NAME = "guest_username"; //访问者的用户名
    private static final String GUEST_PASSWORD = "guest_password"; //访问这的密码


    public static void main(String[] args) {
        ZooKeeper zooKeeper = null;
        try {
            //连接zookeeper的集群
            zooKeeper = new ZooKeeper(IP, CONNECTION_TIMEOUT, new MyWatcher());

            //ACL 相关信息
            Id adminId = new Id("digest", DigestAuthenticationProvider.generateDigest(ADMIN_USER_NAME + ":" + ADMIN_PASSWORD));
            ACL adminAcl = new ACL(ZooDefs.Perms.ALL, adminId);

            Id guestId = new Id("digest", DigestAuthenticationProvider.generateDigest(GUEST_USER_NAME + ":" + GUEST_PASSWORD));
            ACL guestAcl = new ACL(ZooDefs.Perms.READ, guestId);


            // 创建第一层节点， 并且 设置数据
            String parentNode = "/testRootPath";
            String rootData = "root Node";

            List<ACL> aclList = Collections.singletonList(adminAcl);
            String realPath = zooKeeper.create(parentNode, rootData.getBytes(), aclList, CreateMode.PERSISTENT_SEQUENTIAL);
            zooKeeper.addAuthInfo("digest", (ADMIN_USER_NAME + ":" + ADMIN_PASSWORD).getBytes());
            LogHelper.ZOOKEEPER_LOGGER.info("ZookeeperAdminTest root node data:" + new String(zooKeeper.getData(parentNode, false, null)) + ";real Path:" + realPath);

            //创建子节点 设置数据， 更新数据  并删除节点
            String dataNode = parentNode + "/data";
            String dataData = "data Node";
            List<ACL> aclList2 = new ArrayList<ACL>(2);
            aclList2.add(adminAcl);
            aclList2.add(guestAcl);
            realPath = zooKeeper.create(dataNode, dataData.getBytes(), aclList2, CreateMode.PERSISTENT);
            LogHelper.ZOOKEEPER_LOGGER.info("ZookeeperAdminTest data node data:" + new String(zooKeeper.getData(dataNode, false, null)) + ";real Path:" + realPath);
        } catch (Exception e) {
            LogHelper.ZOOKEEPER_LOGGER.error("ZookeeperAdminTest 操作zookeeper 失败", e);
        } finally {
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e) {
                    LogHelper.ZOOKEEPER_LOGGER.error("ZookeeperAdminTest 关闭zookeeper失败", e);
                }
            }
        }
    }
}
