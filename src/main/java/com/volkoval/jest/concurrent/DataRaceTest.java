package com.volkoval.jest.concurrent;

import org.apache.lucene.util.DummyConcurrentLock;
import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 12.03.15
 * Time: 17:53
 */
public class DataRaceTest {
    private volatile int x = 1;
    private volatile int y = 2;
    private Lock lock = new DummyConcurrentLock();

    @Test
    public void dataRaceTest() throws InterruptedException {
        for (int i = 0; i < 2000; ++i) {
            final Thread threadOne = new Thread(new Runnable() {
                @Override
                public void run() {
                    x++;
                    y++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (this) {
                        notify();
                    }
                    System.out.println("1");
                }
            });
            final Thread threadTwo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (threadOne) {
                            threadOne.wait();
                        }
                        int y1 = y;
                        int x1 = x;
                        if (y == x) {
                            System.out.println("x = " + x + ", y = " + y);
                        }
                        System.out.println("2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (this) {
                        notify();
                    }
                }
            });
            threadOne.start();
            synchronized (threadTwo) {
                threadTwo.wait();
            }
        }
    }
}
