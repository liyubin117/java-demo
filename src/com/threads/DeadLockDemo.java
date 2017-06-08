package com.threads;
/**
 * 死锁
 * 运行后aThread和bThread陷入了相互等待。
 * 解决方法：
 * 首先，应该尽量避免在持有一个锁的同时去申请另一个锁，
 * 如果确实需要多个锁，所有代码都应该按照相同的顺序去申请锁
 * 可使用java自带的jstack命令检测死锁
 */
public class DeadLockDemo {
    private static Object lockA = new Object();
    private static Object lockB = new Object();

    private static void startThreadA() {
        Thread aThread = new Thread() {

            @Override
            public void run() {
                synchronized (lockA) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    synchronized (lockB) {
                    }
                }
            }
        };
        aThread.start();
    }

    private static void startThreadB() {
        Thread bThread = new Thread() {
            @Override
            public void run() {
                synchronized (lockB) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    synchronized (lockA) {
                    }
                }
            }
        };
        bThread.start();
    }

    public static void main(String[] args) {
        startThreadA();
        startThreadB();
    }
}