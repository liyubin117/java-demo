package org.rick;

import java.util.concurrent.atomic.AtomicInteger;

//用原子变量的CAS实现锁，0代表未锁，1代表已锁。不过这样过于消耗CPU,有更为高效的方法
//类似于jdk的ReentrantLock
public class MyLock {
    private AtomicInteger status = new AtomicInteger(0);

    public void lock() {
        while (!status.compareAndSet(0, 1)) {
            Thread.yield();
        }
    }

    public void unlock() {
        status.compareAndSet(1, 0);
    }
}