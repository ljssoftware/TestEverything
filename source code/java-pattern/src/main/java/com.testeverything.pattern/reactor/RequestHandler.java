package com.testeverything.pattern.reactor;

import com.testeverything.common.LogHelper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 处理写请求
 * Created by lijinsheng on 14-7-21.
 */
public class RequestHandler implements Runnable {
    private final SocketChannel socketChannel;
    private final Selector selector;

    public RequestHandler(SocketChannel otherSocketChannel, Selector otherSelector) throws IOException {
        this.socketChannel = otherSocketChannel;
        this.selector = otherSelector;
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ, this);
    }

    @Override
    public void run() {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.read(byteBuffer);
            byteBuffer.flip();
            LogHelper.NIO_LOGGER.info("RequestHandler 获取的数据内容:" + new String(Arrays.copyOf(byteBuffer.array(), byteBuffer.limit())));
            // 这边可以开启多线程 进行数据的decode， process, encode, send
            handleRequest(socketChannel, byteBuffer);
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("RequestHandler run", e);
        }
    }

    /**
     * 进行数据的 decode, process, encode,send
     *
     * @param socketChannel socket 通道
     * @param byteBuffer    读取的请求数据
     */
    private void handleRequest(SocketChannel socketChannel, ByteBuffer byteBuffer) {
        //decode
        //process
        //encode
        //send
    }
}
