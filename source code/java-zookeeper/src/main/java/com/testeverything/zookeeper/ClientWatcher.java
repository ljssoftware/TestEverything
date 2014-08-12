package com.testeverything.zookeeper;

import com.testeverything.common.LogHelper;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * Zookeeper 客户端的watcher， 用于更新本地的数据缓存
 * Created by lijinsheng on 14-8-5.
 */
public class ClientWatcher implements Watcher {
    private ZooKeeper zooKeeper = null;
    private static final String PARENT_NODE = "/testRootPathDuplicate";

    public ClientWatcher(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
        LogHelper.ZOOKEEPER_LOGGER.info("ClientWatcher 获取的event 监控 event:" + event);
        if (event.getType() == Event.EventType.None && event.getPath() == null) {
            LogHelper.ZOOKEEPER_LOGGER.info("ClientWatcher 连接上ZooKeeper");
        }

        if (event.getType() == Event.EventType.NodeChildrenChanged) {
            List<String> childNodeNames = new ArrayList<String>();
            try {
                childNodeNames = zooKeeper.getChildren(PARENT_NODE, true);
            } catch (Exception e) {
                LogHelper.ZOOKEEPER_LOGGER.error("ClientWatcher 获取子节点的数据异常", e);
            }
            LogHelper.ZOOKEEPER_LOGGER.info("ClientWatcher 监控的节点 子节点变化了， 新的子节点数量:" + childNodeNames.size());
        }

        if (event.getType() == Event.EventType.NodeDataChanged) {
            String parentNodeData = null;
            try {
                parentNodeData = new String(zooKeeper.getData(PARENT_NODE, true, null));
            } catch (Exception e) {
                LogHelper.ZOOKEEPER_LOGGER.error("ClientWatcher  获取节点数据异常", e);
            }
            LogHelper.ZOOKEEPER_LOGGER.info("ClientWatcher 监控的节点数据:" + parentNodeData);
        }
    }

}
