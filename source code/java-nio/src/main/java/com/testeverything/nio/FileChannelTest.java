package com.testeverything.nio;

import com.testeverything.common.LogHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel的测试类
 * Created by lijinsheng on 14-7-11.
 */
public class FileChannelTest {
    //main 方法的测试内容
    public static void main(String[] args) {

        //open 文件内容
        String filePath = "E:\\工作任务\\pop 游戏点卡\\点卡下单的sku.txt";
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(filePath, "rw");
        } catch (FileNotFoundException e) {
            LogHelper.NIO_LOGGER.error("com.testeverything.nio.FileChannelTest:main 打开文件 " + filePath + " 失败", e);
            return;
        }

        //获取fileChanel 的对象
        FileChannel fileChannel = randomAccessFile.getChannel();

//        File channel 的测试代码
        long position = 0, size;
        try {
            position = fileChannel.position();
            size = fileChannel.size();
        } catch (IOException e) {
            LogHelper.NIO_LOGGER.error("position:" + position, e);
            return;
        }
        LogHelper.NIO_LOGGER.info("position:" + position + ";size:" + size);

//        MappedByteBuffer mappedByteBuffer;
//        try {
//            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, size);
//        } catch (IOException e) {
//            LogHelper.NIO_LOGGER.info(e);
//            return;
//        }
//        LogHelper.NIO_LOGGER.info("capacity:" + mappedByteBuffer.capacity() + "; limit:" + mappedByteBuffer.limit() + ";position:" + mappedByteBuffer.position());

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(48);
        try {
            LogHelper.NIO_LOGGER.info("channel position:" + fileChannel.position() + ";byteBuffer position:" + byteBuffer.position() + ";remain:" + byteBuffer.remaining());
            int byteRead = fileChannel.read(byteBuffer);
            LogHelper.NIO_LOGGER.info("channel position:" + fileChannel.position() + ";byteBuffer position:" + byteBuffer.position() + ";remain:" + byteBuffer.remaining() + ";byteRead:" + byteRead);

            while (byteRead != -1) {
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.limit()];
                for (int i = 0; i < byteBuffer.limit(); i++) {
                    bytes[i] = byteBuffer.get();
                }
                LogHelper.NIO_LOGGER.info("read content:" + new String(bytes, "GBK"));
                byteBuffer.clear();
                byteRead = fileChannel.read(byteBuffer);
                LogHelper.NIO_LOGGER.info("channel position:" + fileChannel.position() + ";byteBuffer position:" + byteBuffer.position() + ";remain:" + byteBuffer.remaining() + ";byteRead:" + byteRead);
            }
            byteBuffer.put(new String("李金声").getBytes("GBK"));
//            LogHelper.NIO_LOGGER.info("content:" + new String(byteBuffer.array(), "GBK"));
            byteBuffer.flip();
            fileChannel.position(fileChannel.size());
            fileChannel.write(byteBuffer);
        } catch (IOException e) {
            LogHelper.NIO_LOGGER.error(e);
        } finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                LogHelper.NIO_LOGGER.info("close the file ", e);
            }
        }
    }
}
