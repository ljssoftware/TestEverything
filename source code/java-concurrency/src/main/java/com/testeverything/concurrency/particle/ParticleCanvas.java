package com.testeverything.concurrency.particle;

import java.awt.*;

/**
 * 粒子画布
 * Created by lijinsheng on 14-7-9.
 */
public class ParticleCanvas extends Canvas {
    private Particle[] particles = new Particle[0];

    //    构造函数 设置大小
    public ParticleCanvas(int size) {
        setSize(new Dimension(size, size));
    }

    public synchronized Particle[] getParticles() {
        return particles;
    }

    public synchronized void setParticles(Particle[] particles) {
        if (particles == null) {
            throw new IllegalArgumentException("粒子数组不能为null");
        }
        this.particles = particles;
    }

    //override the paint method
    public void paint(Graphics graphics) {
        Particle[] particlesLocal = getParticles();
        for (int i = 0; i < particlesLocal.length; i++) {
            particlesLocal[i].paint(graphics);
        }
    }

}
