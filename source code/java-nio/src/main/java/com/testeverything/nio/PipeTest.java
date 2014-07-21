package com.testeverything.nio;

import com.testeverything.common.LogHelper;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

/**
 * 管道测试类
 * Created by lijinsheng on 14-7-21.
 */
public class PipeTest {
    private static final int THREAD_NUMBER = 1;

    public static void main(String[] args) {
        try {
            final Pipe pipe = Pipe.open();
            final CountDownLatch startLatch = new CountDownLatch(1);
            final CountDownLatch endLatch = new CountDownLatch(THREAD_NUMBER);
            for (int i = 0; i < THREAD_NUMBER; i++) {
                PipeWriteTask pipeWriteTask = new PipeWriteTask(pipe, startLatch, endLatch);
                Thread thread = new Thread(pipeWriteTask);
                thread.start();
            }
            startLatch.countDown();
//            endLatch.wait();

            //从source channel中 读取数据
            Pipe.SourceChannel sourceChannel = pipe.source();
            sourceChannel.configureBlocking(false);
            Selector selector = Selector.open();
            sourceChannel.register(selector, SelectionKey.OP_READ);

            //设置buffer区 和StringBuilder的数据
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            StringBuilder stringBuilder = new StringBuilder();

            //读取采用异步的方式进行读取
            boolean isClosed = false;

            while (true) {
                int readyChannel = selector.select();
                if (readyChannel == 0) {
                    continue;
                }

                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isReadable()) {
                        //不断的读取， 直到获取的数据为空
                        byteBuffer.clear();
                        Pipe.SourceChannel channel = (Pipe.SourceChannel) key.channel();
                        int byteRead = channel.read(byteBuffer);
                        while (byteRead != 0) {
                            if (byteRead == -1) {
                                //channel被关闭了
                                isClosed = true;
                                break;
                            }
                            byteBuffer.flip();
                            stringBuilder.append(new String(Arrays.copyOf(byteBuffer.array(), byteBuffer.limit())));
                            byteBuffer.clear();
                            byteRead = channel.read(byteBuffer);
                        }
                    }
                }
                if (isClosed) {
                    break;
                }
            }
            LogHelper.NIO_LOGGER.info("PipeTest 从pipe中读取到的全部数据:" + stringBuilder.toString());
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("PipeTest 操作管道异常", e);
        }
    }
}

class PipeWriteTask implements Runnable {
    private Pipe writePipe = null;
    private CountDownLatch startLatch = null;
    private CountDownLatch endLatch = null;

    PipeWriteTask(Pipe writePipe, CountDownLatch startLatch, CountDownLatch endLatch) {
        this.writePipe = writePipe;
        this.startLatch = startLatch;
        this.endLatch = endLatch;
    }

    @Override
    public void run() {
        Pipe.SinkChannel sinkChannel = writePipe.sink();
        try {
            startLatch.await();
            String writeString = "This is the string  written to pipe \n" +
                    "This is the string  written to pipe\n" +
                    "This is the string  written to pipe\n" +
                    "This is the string  written to pipe\n" +
                    "This is the string  written to pipe\n";
            ByteBuffer byteBuffer = ByteBuffer.allocate(500);
            byteBuffer.put(writeString.getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                int byteWrite = sinkChannel.write(byteBuffer);
                LogHelper.NIO_LOGGER.info("PipeTest 写入sink channel字节数:" + byteWrite);
            }
//            endLatch.countDown();
        } catch (Exception e) {
            LogHelper.NIO_LOGGER.error("PipeTest 操作 sink channel 失败", e);
        } finally {
            try {
                sinkChannel.close();
            } catch (Exception e) {
                LogHelper.NIO_LOGGER.error("PipeTest 关闭 sink channel 失败", e);
            }
        }
    }
}