package com.testeverything.zookeeper;

import com.testeverything.common.LogHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * 用于管理zookeeper的工具类
 * Created by lijinsheng on 14-8-6.
 */
public class ZookeeperUtil {
    /**
     * 递归删除节点  先删除所有的子节点
     *
     * @param zooKeeper
     * @param node
     */
    public static void deleteNodeRecursive(ZooKeeper zooKeeper, String node) {
        try {
            List<String> childrenNodeList = zooKeeper.getChildren(node, false);
            //存在子节点的 先删除子节点
            if (CollectionUtils.isNotEmpty(childrenNodeList)) {
                for (String childrenNode : childrenNodeList) {
                    deleteNodeRecursive(zooKeeper, node + "/" + childrenNode);
                }
            }
            zooKeeper.delete(node, -1);
        } catch (Exception e) {
            LogHelper.ZOOKEEPER_LOGGER.error("ZookeeperUtil deleteNodeRecursive 循环删除 节点 " + node + " 失败", e);
        }
    }
}
