package com.testeverything.nio;

import com.testeverything.common.LogHelper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * socket Server 的测试类
 * Created by lijinsheng on 14-7-11.
 */
public class ServerSocketChannelTest {
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        final BlockingQueue<SocketChannel> socketChannels = new ArrayBlockingQueue<SocketChannel>(100, true);
        try {
            //开一个线程 专门负责connect 并且select
            //初始化serverSocket
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(80));

            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int readyChannel = selector.select();
                if (readyChannel == 0) {
                    continue;
                }

                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = keySet.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    //将已经取出的keyIterator 去除
                    keyIterator.remove();

                    //ServerSocketChannel 不存在关闭的情况
                    if (key.isAcceptable()) {
                        if (socketChannels.remainingCapacity() != 0) {
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_CONNECT);
                            socketChannels.add(socketChannel);
                        }
                        continue;
                    }


                    //统一检查关闭的情况
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    //write 事件不能监控， 在连接连接上的时候， 会一直的writable
                    if (key.isWritable()) {
                        LogHelper.NIO_LOGGER.info("ServerSocketChannelTest ready for write");
                        String writeString = "The String write to the client";
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
                        byteBuffer.put(writeString.getBytes());
                        byteBuffer.flip();
                        //需要根据抓取的异常 进行处理
                        try {
                            socketChannel.write(byteBuffer);
                        } catch (Exception e) {

                        }
                    }


                    if (key.isReadable()) {
                        //这边读写
                        ByteBuffer buffer = ByteBuffer.allocate(300);
                        int byteRead = socketChannel.read(buffer);
                        while (byteRead != 0) {
                            if (byteRead == -1) {
                                //说明socket被关闭了
                                LogHelper.NIO_LOGGER.info("ServerSocketChannelTest socket is close");
                                socketChannels.remove(socketChannel);
                                break;
                            }
                            LogHelper.NIO_LOGGER.info("ServerSocketChannelTest 读取的内容 :" + new String(buffer.array()));
                            buffer.clear();
                            byteRead = socketChannel.read(buffer);
                        }
//                        String responseContent = "Server Socket response";
//                        buffer.put(responseContent.getBytes());
//                        buffer.flip();
//                        int writeByteNumber = socketChannel.write(buffer);
//                        LogHelper.NIO_LOGGER.info("ServerSocketChannelTest 返回的字节数:" + writeByteNumber);
                    }
                }
            }
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("ServerSocketChannelTest operate the ServerSocketChannel error", e);
        } finally {
            try {
                if (serverSocketChannel != null) {
                    serverSocketChannel.close();
                }
                if (selector != null) {
                    selector.close();
                }
                if (socketChannels.size() > 0) {
                    for (SocketChannel socketChannel : socketChannels) {
                        socketChannel.close();
                    }
                }

            } catch (Exception e) {
                LogHelper.NIO_LOGGER.error("ServerSocketChannelTest close the ServerSocketChannel error", e);
            }
        }
    }
}
