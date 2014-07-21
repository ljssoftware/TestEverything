package com.testeverything.nio;

import com.testeverything.common.LogHelper;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Server Channel 的测试类
 * Created by lijinsheng on 14-7-11.
 */
public class SocketChannelTest {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
            //初始化 selector 和 socket channel
            socketChannel = SocketChannel.open();
            boolean connectResult = socketChannel.connect(new InetSocketAddress("wan.jd.net", 80));
            if (!connectResult) {
                LogHelper.NIO_LOGGER.error("SocketChannelTest connection 没有建立成功");
                return;
            }

            String writeString = "The String write to the wan.jd.net";
            ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
            byteBuffer.put(writeString.getBytes());
            byteBuffer.flip();
            int writeNumber = socketChannel.write(byteBuffer);
            LogHelper.NIO_LOGGER.info("SocketChannelTest write byte number " + writeNumber);

            byteBuffer.clear();
            int byteRead = socketChannel.read(byteBuffer);//读取数据
            LogHelper.NIO_LOGGER.info("SocketChannelTest socket channel 读取的字节数量:" + byteRead);
            LogHelper.NIO_LOGGER.info("SocketChannelTest socket channel 读取的内容:" + new String(byteBuffer.array()));


        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("SocketChannelTest socket channel and selector 结合 使用异常", e);
        } finally {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
            } catch (Exception e) {
                LogHelper.NIO_LOGGER.error("SocketChannelTest 关闭selector和channel异常", e);
            }
        }
    }
}
