package com.testeverything.concurrency.particle;

import com.testeverything.common.LogHelper;

import java.applet.Applet;
import java.util.concurrent.TimeUnit;

/**
 * 粒子 applet
 * Created by lijinsheng on 14-7-9.
 */
public class ParticleApplet extends Applet {
    protected Thread[] threads = null; // 没有运行的 就是null
    protected final ParticleCanvas canvas = new ParticleCanvas(500);

    public void init() {
        add(canvas);
    }

    /**
     * 根据粒子构建线程
     *
     * @param particle 粒子对象
     * @return 线程
     */
    protected Thread makeThread(final Particle particle) {
        Runnable loopRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        particle.move();
                        canvas.repaint();
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    LogHelper.CONCURRENCY_LOGGER.info("interrupted, the thread is stop!", e);
                }
                try {
                    TimeUnit.SECONDS.sleep(60);
                } catch (InterruptedException e) {
                    LogHelper.CONCURRENCY_LOGGER.info("sleep interrupt:", e);
                }
                LogHelper.CONCURRENCY_LOGGER.info("interrupt Status after exception:" + Thread.currentThread().isInterrupted());
//                Thread.interrupted();
//                LogHelper.CONCURRENCY_LOGGER.info("interrupt Status after Thread.interrupted:" + Thread.currentThread().isInterrupted());
            }
        };
        return new Thread(loopRunnable);
    }

    public void start() {
        int n = 10;
        if (threads == null) {
            Particle[] particles = new Particle[n];
            for (int i = 0; i < n; i++) {
                particles[i] = new Particle(200, 200);
            }
            canvas.setParticles(particles);

            threads = new Thread[n];
            for (int i = 0; i < n; i++) {
                threads[i] = makeThread(particles[i]);
            }

            for (int i = 0; i < n; i++) {
                threads[i].setDaemon(true);
                threads[i].start();
            }
        }
    }

    public void stop() {
        if (threads != null) {
            for (Thread thread : threads) {
                thread.interrupt();
                LogHelper.CONCURRENCY_LOGGER.info("interrupt Status after interrupt:" + thread.isInterrupted());
            }
//            for (Thread thread : threads) {
//                try {
//                    thread.join();
//                } catch (InterruptedException e) {
//                    LogHelper.CONCURRENCY_LOGGER.info("join exception" ,e);
//                }
//            }

            threads = null;
        }
    }
}
