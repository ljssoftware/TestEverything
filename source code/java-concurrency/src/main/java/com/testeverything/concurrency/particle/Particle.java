package com.testeverything.concurrency.particle;

import java.awt.*;
import java.util.Random;

/**
 * 粒子 domain类
 * Created by lijinsheng on 14-7-9.
 */
public class Particle {
    protected int x; //x坐标
    protected int y; //y坐标
    protected final Random random = new Random();

    public Particle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void move() {
        x += random.nextInt(10);
        x = Math.abs(x) % 500;
        y += random.nextInt(20);
        y = Math.abs(y) % 500;
    }

    public void paint(Graphics graphics) {
        int xl, yl;
        synchronized (this) {
            xl = x;
            yl = y;
        }
        graphics.drawRect(xl, yl, 10, 10);
    }
}
