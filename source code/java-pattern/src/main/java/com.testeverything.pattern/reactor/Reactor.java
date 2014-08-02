package com.testeverything.pattern.reactor;

import com.testeverything.common.LogHelper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Reactor 模式中的reactor  负责处理I/O 时间 并分发到 各个handler
 * Created by lijinsheng on 14-7-21.
 */
public class Reactor implements Runnable {
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
        serverSocketChannel.socket().bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
        LogHelper.NIO_LOGGER.info("Reactor 初始化 Reactor 成功");
    }

    @Override
    public void run() { //normal in a new Thread
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = keySet.iterator();
                while (keyIterator.hasNext()) {
                    disPatch(keyIterator.next());
                }
                keySet.clear();
            }
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("Reactor run reactor exit error", e);
        }
    }

    /**
     * @param selectionKey
     */
    private void disPatch(SelectionKey selectionKey) {
        Runnable runnable = (Runnable) selectionKey.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }

    /**
     * 用于接收请求数据
     */
    class Acceptor implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    new RequestHandler(socketChannel, selector);
                }
            } catch (IOException e) {
                LogHelper.NIO_LOGGER.error("Acceptor run 接收请求失败", e);
            }
        }
    }


}
