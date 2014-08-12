package com.testeverything.zookeeper;

import com.testeverything.common.LogHelper;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * 事件监听者
 * Created by lijinsheng on 14-7-28.
 */
public class AdminWatcher implements Watcher {
    @Override
    public void process(WatchedEvent event) {
        LogHelper.ZOOKEEPER_LOGGER.info("AdminWatcher:process 监听到的事件信息:" + event.toString());
    }
}
