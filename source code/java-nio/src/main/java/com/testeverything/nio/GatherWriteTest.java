package com.testeverything.nio;

import com.testeverything.common.LogHelper;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;

/**
 * gather write test类
 * Created by lijinsheng on 14-7-14.
 */
public class GatherWriteTest {
    public static void main(String[] args) {
        RandomAccessFile randomAccessFile = null;
        String filePath = "E:\\\\工作任务\\\\pop 游戏点卡\\\\点卡下单的sku.txt";
        try {
            randomAccessFile = new RandomAccessFile(filePath, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            fileChannel.position(fileChannel.size());

            ByteBuffer msgHeader = ByteBuffer.allocate(128);
            ByteBuffer msgBody = ByteBuffer.allocate(1024);
            msgHeader.put(new String("李佳骏").getBytes("GBK"));
            msgHeader.flip();
            msgBody.put(new String("李德佳").getBytes("GBK"));
            msgBody.flip();
            ByteBuffer[] byteBuffers = {msgHeader, msgBody};
            fileChannel.write(byteBuffers);
            fileChannel.force(false);
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("GatherWriteTest 测试 scatter read", e);
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Exception e) {
                LogHelper.NIO_LOGGER.error("GatherWriteTest 测试 scatter read", e);
            }
        }
    }
}
