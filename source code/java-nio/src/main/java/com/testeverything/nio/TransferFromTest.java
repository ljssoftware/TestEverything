package com.testeverything.nio;

import com.testeverything.common.LogHelper;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 测试通道的transferFrom 方法
 * Created by lijinsheng on 14-7-15.
 */
public class TransferFromTest {
    public static void main(String[] args) {
        String fromPath = "E:\\\\工作任务\\\\pop 游戏点卡\\\\点卡下单的sku.txt";
        RandomAccessFile fromFile = null;

        String toPath = "E:\\\\工作任务\\\\pop 游戏点卡\\\\toFile.txt";
        RandomAccessFile toFile = null;
        try {
            //source file
            fromFile = new RandomAccessFile(fromPath, "r");
            FileChannel fromChannel = fromFile.getChannel();

            //destination file
            toFile = new RandomAccessFile(toPath, "rw");
            FileChannel toChannel = toFile.getChannel();

            //position 是toChannel的开始的position
            long byteTransferred = toChannel.transferFrom(fromChannel, toChannel.size(), fromChannel.size());
            toChannel.force(false);
            LogHelper.NIO_LOGGER.info("TransferToTest transfer count:" + byteTransferred + ";toChannelPosition:" + toChannel.position() + ";fromChannelPosition:" + fromChannel.position());
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("TransferFromTest 异常", e);
        } finally {
            try {
                if (fromFile != null) {
                    fromFile.close();
                }

                if (toFile != null) {
                    toFile.close();
                }
            } catch (Exception e) {
                LogHelper.NIO_LOGGER.error("TransferFromTest close 文件异常", e);
            }
        }
    }
}
