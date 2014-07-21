package com.testeverything.nio;

import com.testeverything.common.LogHelper;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * file channel 的transfer to的 方法测试
 * Created by lijinsheng on 14-7-15.
 */
public class TransferToTest {
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
            toChannel.position(toChannel.size());
            //position 设置的是this的开始position， 不过不会修改position属性， 只会修改toChannel的position属性
            long byteTransferred = fromChannel.transferTo(100, fromChannel.size(), toChannel);
            toChannel.force(false);
            LogHelper.NIO_LOGGER.info("TransferToTest transfer count:" + byteTransferred + ";toChannelPosition:" + toChannel.position() + ";fromChannelPosition:" + fromChannel.position());
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("TransferToTest fromPath:" + fromPath + ";toPath:" + toPath, e);
        } finally {
            try {
                if (fromFile != null) {
                    fromFile.close();
                }
                if (toFile != null) {
                    toFile.close();
                }
            } catch (Exception e) {
                LogHelper.NIO_LOGGER.error("TransferToTest close file exception fromPath:" + fromPath + ";toPath:" + toPath, e);
            }
        }
    }
}
