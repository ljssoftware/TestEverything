package com.testeverything.nio;

import com.testeverything.common.LogHelper;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

/**
 * DatagramChannel 的server端 测试类
 * Created by lijinsheng on 14-7-18.
 */
public class DatagramChannelTest {
    private static final String IP = "10.12.206.65";

    public static void main(String[] args) {
        DatagramChannel readChannel = null;
        DatagramChannel writeChannel = null;
        try {
            //初始化 读取的channel
            readChannel = DatagramChannel.open();
            readChannel.socket().bind(new InetSocketAddress(80));

            //初始化 写的channel
            writeChannel = DatagramChannel.open();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
            byteBuffer.put(new String("socket loop").getBytes());
            byteBuffer.flip();

            int sendNumber = writeChannel.send(byteBuffer, new InetSocketAddress(IP, 80));
            LogHelper.NIO_LOGGER.info("DatagramChannelTest 写出去的数量:" + sendNumber);
            while (true) {
                //读取数据
                byteBuffer.clear();
                readChannel.receive(byteBuffer);
                byteBuffer.flip();
                LogHelper.NIO_LOGGER.info("DatagramChannelTest 接收到的数据" + new String(Arrays.copyOf(byteBuffer.array(), byteBuffer.limit())));

                //将收到的数据写回去
                sendNumber = writeChannel.send(byteBuffer, new InetSocketAddress(IP, 80));
                LogHelper.NIO_LOGGER.info("DatagramChannelTest 写出去的数量:" + sendNumber);
            }
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("DatagramChannelTest server channel operate error", e);
        } finally {
            try {
                if (readChannel != null) {
                    readChannel.close();
                }
                if (writeChannel != null) {
                    writeChannel.close();
                }
            } catch (Exception e) {
                LogHelper.NIO_LOGGER.error("DatagramChannelTest server channel close error", e);
            }
        }
    }
}
