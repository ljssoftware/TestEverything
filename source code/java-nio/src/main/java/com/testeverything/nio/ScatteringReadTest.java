package com.testeverything.nio;

import com.testeverything.common.LogHelper;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 测试分散读取
 * Created by lijinsheng on 14-7-14.
 */
public class ScatteringReadTest {
    public static void main(String[] args) {

        RandomAccessFile randomAccessFile = null;
        String filePath = "E:\\\\工作任务\\\\pop 游戏点卡\\\\点卡下单的sku.txt";
        try {
            randomAccessFile = new RandomAccessFile(filePath, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer msgHeader = ByteBuffer.allocate(128);
            ByteBuffer msgBody = ByteBuffer.allocate(1024);
            ByteBuffer[] byteBuffers = new ByteBuffer[]{msgHeader, msgBody};
            long byteRead = fileChannel.read(byteBuffers);
            LogHelper.NIO_LOGGER.info("ScatteringReadTest 测试 read byte number:" + byteRead);
            LogHelper.NIO_LOGGER.info("ScatteringReadTest 测试 msg Header:" + new String(msgHeader.array(), "GBK"));
            LogHelper.NIO_LOGGER.info("ScatteringReadTest 测试 msg Body:" + new String(msgBody.array(), "GBK"));
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("ScatteringReadTest 测试 scatter read", e);
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Exception e) {
                LogHelper.NIO_LOGGER.error("ScatteringReadTest 测试 scatter read", e);
            }
        }
    }
}
