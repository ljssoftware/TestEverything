package com.testeverything.zookeeper;

import com.testeverything.common.LogHelper;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Zookeeper Admin 端的test类， 用于更改数据， 增删节点
 * Created by lijinsheng on 14-7-25.
 */
public class ZookeeperAdminTest {
    private static final String IP = "192.168.215.128:2181,192.168.215.129:2181"; //zookeeper的IP地址， 所有的服务器信息
    private static final int SESSION_TIME_OUT = 500000; // 连接超时时间, 单位是毫秒

    //    管理的用户名 密码的相关配置
    private static final String ADMIN_USER_NAME = "admin_username"; // 管理者的用户名
    private static final String ADMIN_PASSWORD = "admin_password"; //管理者的密码

    //访问者的用户名 密码的相关配置
    private static final String GUEST_USER_NAME = "guest_username"; //访问者的用户名
    private static final String GUEST_PASSWORD = "guest_password"; //访问这的密码

    private static final String PARENT_NODE = "/testRootPathDuplicate";


    public static void main(String[] args) {
        ZooKeeper zooKeeper = null;
        try {
            //连接zookeeper的集群
            zooKeeper = new ZooKeeper(IP, SESSION_TIME_OUT, new AdminWatcher());
            zooKeeper.addAuthInfo("digest", (ADMIN_USER_NAME + ":" + ADMIN_PASSWORD).getBytes());

            //根节点 如果 不存在则创建根节点
            Id adminId = new Id("digest", DigestAuthenticationProvider.generateDigest(ADMIN_USER_NAME + ":" + ADMIN_PASSWORD));
            ACL adminAcl = new ACL(ZooDefs.Perms.ALL, adminId);

            String realPath;

            //如果项目的根节点不存在，创建自己的根节点
            Stat stat = null;
            stat = zooKeeper.exists(PARENT_NODE, true);
            if (stat == null) {
                List<ACL> aclList = Collections.singletonList(adminAcl);
                realPath = zooKeeper.create(PARENT_NODE, "root node".getBytes(), aclList, CreateMode.PERSISTENT);
                LogHelper.ZOOKEEPER_LOGGER.info("ZookeeperAdminTest root node data:" + new String(zooKeeper.getData(realPath, false, null)) + ";real Path:" + realPath);
            }
            int version = stat.getVersion();
            zooKeeper.setData(PARENT_NODE, "root node change".getBytes(), version);
            LogHelper.ZOOKEEPER_LOGGER.info("ZookeeperAdminTest root node data:" + new String(zooKeeper.getData(PARENT_NODE, false, null)));

            //ACL 相关信息
            Id guestId = new Id("digest", DigestAuthenticationProvider.generateDigest(GUEST_USER_NAME + ":" + GUEST_PASSWORD));
            ACL guestAcl = new ACL(ZooDefs.Perms.READ, guestId);


            //创建子节点 设置数据， 更新数据  并删除节点
            String dataNode = PARENT_NODE + "/data";
            String dataData = "data Node";
            List<ACL> aclList2 = new ArrayList<ACL>(2);
            aclList2.add(adminAcl);
            aclList2.add(guestAcl);
            realPath = zooKeeper.create(dataNode, dataData.getBytes(), aclList2, CreateMode.PERSISTENT_SEQUENTIAL);
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
