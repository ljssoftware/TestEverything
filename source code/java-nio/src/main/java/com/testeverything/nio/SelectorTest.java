package com.testeverything.nio;

import com.testeverything.common.LogHelper;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * selector 的测试类
 * Created by lijinsheng on 14-7-15.
 */
public class SelectorTest {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        Selector selector = null;
        try {
            //socket channel 不会处理 暂时先使用 null处理
            socketChannel.configureBlocking(false);

            //selector 注册
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, null);

            while (true) {
                int readyChannelNumber = selector.select();
                if (readyChannelNumber == 0) {
                    continue;
                }
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = keySet.iterator();
                    while (keyIterator.hasNext()) {
                    SelectionKey selectionKey = keyIterator.next();
                    if (selectionKey.isReadable()) {
                        LogHelper.NIO_LOGGER.error("SelectorTest socket channel 有数据可以读取");
                    }
                    if (selectionKey.isWritable()) {
                        LogHelper.NIO_LOGGER.error("SelectorTest socket channel  可以写数据了");
                    }
                    keyIterator.remove();
                }
            }
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("SelectorTest 使用select 监控 channel 异常", e);
        } finally {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }

                if (selector != null) {
                    selector.close();
                }
            } catch (Exception e) {
                LogHelper.NIO_LOGGER.error("close socketChannel and selector 异常", e);
            }
        }
    }
}
