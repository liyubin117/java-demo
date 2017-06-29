package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用newFixedThreadPool创建线程池易造成死锁，可用newCachedThreadPool不断创建新线程、或用SynchronousQueue作为阻塞队列抛出异常
 * 核心就是尽量能创建新线程来避免死锁发生，但这样容易造成资源争用过度，最好的办法还是优化业务逻辑，实在不行再用无界线程池
 */
public class ThreadPoolDeadLockDemo {
    private static final int THREAD_NUM = 5;
//    static ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);
    static ExecutorService executor = Executors.newCachedThreadPool();
//    static ExecutorService executor = new ThreadPoolExecutor(
//            THREAD_NUM, THREAD_NUM, 0, TimeUnit.SECONDS,
//            new SynchronousQueue<Runnable>());

    //本例，若创建只有5个线程同时运行的线程池，TaskA任务内部执行TaskB，提交5个TaskA后，线程已被占满，TaskB只能进阻塞队列，而TaskA等待TaskB，无法释放造成死锁
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            executor.execute(new TaskA());
        }
        Thread.sleep(2000);
        executor.shutdown();
    }

    static class TaskA implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Future<?> future = executor.submit(new TaskB());
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("finished task A");
        }
    }

    static class TaskB implements Runnable {
        @Override
        public void run() {
            System.out.println("finished task B");
        }
    }
}