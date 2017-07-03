package com.threads.timer;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * ScheduledExecutorService
 * 一个任务不会使另一个任务延时
 * 一个任务发生异常，也不会使整个任务组取消
 */
public class ScheduledFixedDelay {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService timer = Executors.newScheduledThreadPool(10);
        timer.schedule(new LongRunningTask(), 10, TimeUnit.MILLISECONDS);
        timer.scheduleWithFixedDelay(new FixedDelayTask(), 100, 1000,
                TimeUnit.MILLISECONDS);
        
        ScheduledExecutorService timer1 = Executors
                .newSingleThreadScheduledExecutor();
        timer1.scheduleWithFixedDelay(new TaskA(), 0, 1, TimeUnit.SECONDS);
        timer1.scheduleWithFixedDelay(new TaskB(), 2, 1, TimeUnit.SECONDS);
    }
    
    static class LongRunningTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            System.out.println("long running finished");
        }
    }

    static class FixedDelayTask implements Runnable {
        @Override
        public void run() {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
        }
    }
    
    static class TaskA implements Runnable {

        @Override
        public void run() {
            System.out.println("task A");
        }
    }

    static class TaskB implements Runnable {

        @Override
        public void run() {
            System.out.println("task B");
            throw new RuntimeException();
        }
    }
}