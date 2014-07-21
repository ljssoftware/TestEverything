package com.testeverything.concurrency.test;

import com.testeverything.concurrency.particle.ParticleApplet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

/**
 * 测试粒子的 Applet
 * Created by lijinsheng on 14-7-9.
 */
public class TestParticle {
    public static void main(String[] args) {
        final ParticleApplet particleApplet = new ParticleApplet();
        JFrame frame = new JFrame("Particle");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                particleApplet.stop();
            }
        });
        frame.getContentPane().add(particleApplet, BorderLayout.CENTER);
        particleApplet.init();
        particleApplet.start();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
