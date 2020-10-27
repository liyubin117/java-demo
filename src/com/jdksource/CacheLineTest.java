package com.jdksource;

import org.junit.Before;
import org.junit.Test;
import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;

/**
 * 伪共享 64k
 */
public class CacheLineTest {
    private final long COUNT = 1_0000_0000L;
    private CountDownLatch latch = new CountDownLatch(2);

    @Test
    public void testSlow() throws InterruptedException {
        T[] arr = new T[2];
        arr[0] = new T();
        arr[1] = new T();
        long begin = System.currentTimeMillis();
        new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[0].x += 1;
            }
            latch.countDown();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[1].x += 1;
            }
            latch.countDown();

        }).start();
        latch.await();
        System.out.println(arr[0].x);
        System.out.println(arr[1].x);
        System.out.println(System.currentTimeMillis() - begin);
    }

    @Test
    public void testQuick() throws InterruptedException {
        O[] arr = new O[2];
        arr[0] = new O();
        arr[1] = new O();
        long begin = System.currentTimeMillis();
        new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[0].x += 1;
            }
            latch.countDown();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[1].x += 1;
            }
            latch.countDown();
        }).start();
        latch.await();
        System.out.println(arr[0].x);
        System.out.println(arr[1].x);
        System.out.println(System.currentTimeMillis() - begin);
    }


    private static class T{
        public volatile long x = 0L;
    }

    private static class O{
        public long a,b,c,d,e,f,g;
        public volatile long x = 0L;
        public long i,j,k,z,m,n,o;
    }
}

