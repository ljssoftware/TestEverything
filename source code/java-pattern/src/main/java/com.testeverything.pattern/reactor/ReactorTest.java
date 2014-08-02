package com.testeverything.pattern.reactor;

import java.io.IOException;

/**
 * Server  端的唯一入口
 * Created by lijinsheng on 14-7-21.
 */
public class ReactorTest {
    public static void main(String[] args) throws IOException {
        Reactor reactor = new Reactor(80);
        Thread thread = new Thread(reactor);
        thread.start();
    }
}
