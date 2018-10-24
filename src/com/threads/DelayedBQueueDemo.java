package com.threads;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 延时阻塞队列
 */
public class DelayedBQueueDemo {
    private static final AtomicLong taskSequencer = new AtomicLong(0);
    
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedTask> tasks = new DelayQueue<>();
        DelayedTask task1 = new DelayedTask(1, new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + " execute delayed task");
            }
        });
        DelayedTask task2 = new DelayedTask(2, new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + " execute delayed task");
            }
        });

        System.out.println(System.currentTimeMillis());
        tasks.put(task1);
        tasks.put(task2);
        System.out.println(tasks);
        int size = tasks.size();
        for(int i=0;i<size;i++){
            System.out.println(tasks.take());
        }

        task1 = new DelayedTask(2, new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + " execute delayed task");
            }
        });
        task2 = new DelayedTask(1, new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + " execute delayed task");
            }
        });
        tasks.put(task1);
        tasks.put(task2);
        System.out.println(System.currentTimeMillis());
        new Thread(tasks.take().getTask()).start();
        new Thread(tasks.take().getTask()).start();

    }

    static class DelayedTask implements Delayed {
        private long runTime;
        private long sequence;
        private Runnable task;

        public DelayedTask(int delayedSeconds, Runnable task) {
            this.runTime = System.currentTimeMillis() + delayedSeconds * 1000;
            this.sequence = taskSequencer.getAndIncrement();
            this.task = task;
        }

        @Override
        public int compareTo(Delayed o) {
            if (o == this) {
                return 0;
            }
            if (o instanceof DelayedTask) {
                DelayedTask other = (DelayedTask) o;
                return (this.runTime<other.runTime) ? -1 : (this.runTime>other.runTime ? 1 : (this.sequence<other.sequence ? -1 : (this.sequence>other.sequence ? 1 : 0)));
            }
            throw new IllegalArgumentException();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runTime - System.currentTimeMillis(),
                    TimeUnit.MICROSECONDS);
        }

        public Runnable getTask() {
            return task;
        }

        @Override
        public String toString(){
            return this.sequence + ":" + this.runTime;
        }
    }
}