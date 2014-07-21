package com.testeverything.nio;

import com.testeverything.common.LogHelper;

import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;

/**
 * Buffer 的测试用例
 * Created by lijinsheng on 14-7-14.
 */
public class BufferTest {
    public static void main(String[] args) {
        // 测试从文件中 使用 buffer 读取数据
        RandomAccessFile randomAccessFile = null;
        String filePath = "E:\\工作任务\\pop 游戏点卡\\点卡下单的sku.txt";
        try {
            randomAccessFile = new RandomAccessFile(filePath, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();

            //create buffer with capacity of 48 bytes
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            int bytesRead = fileChannel.read(byteBuffer);
            while (bytesRead != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    LogHelper.NIO_LOGGER.info((char) byteBuffer.get());
                }
                byteBuffer.clear();
                bytesRead = fileChannel.read(byteBuffer);
            }
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("read the random file error:" + filePath, e);
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (Exception e) {
                    LogHelper.NIO_LOGGER.error("close the random file error:" + filePath, e);
                }
            }
        }

        //测试 buffer mark 和reset方法
        LongBuffer longBuffer = LongBuffer.allocate(100);
        longBuffer.put(1000L);
        longBuffer.mark();
        longBuffer.put(1001L);
        longBuffer.put(1002L);
        LogHelper.NIO_LOGGER.error("after put position:" + longBuffer.position());
        longBuffer.reset();
        LogHelper.NIO_LOGGER.error("after reset position:" + longBuffer.position());

        //test buffer equals and compareTo
        LongBuffer longBuffer1 = LongBuffer.allocate(101);
        LongBuffer longBuffer2 = LongBuffer.allocate(100);

        //设置longBuffer1
        longBuffer1.put(100L);
        longBuffer1.put(101L);
        longBuffer1.flip();

        //设置longBuffer2
        longBuffer2.put(100L);
        longBuffer2.put(100L);
        longBuffer2.flip();
        longBuffer2 = (LongBuffer) longBuffer2.limit(90);

        if (longBuffer1.equals(longBuffer2)) {
            LogHelper.NIO_LOGGER.info("buffer1 equals buffer2");
        } else {
            LogHelper.NIO_LOGGER.info("buffer1 is not equals to buffer2");
        }

        LogHelper.NIO_LOGGER.info("buffer1 and buffer2 compare result:" + longBuffer1.compareTo(longBuffer2));
    }
}
