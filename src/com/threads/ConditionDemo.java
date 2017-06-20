package com.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.threads.BlockingQueue.ConditionBlockingQueue;
import com.threads.BlockingQueue;

public class ConditionDemo extends Thread {
    private volatile boolean fire = false;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    
    public static void main(String[] args) throws InterruptedException {
    	ConditionDemo waitThread = new ConditionDemo();
        waitThread.start();
        Thread.sleep(1000);
        System.out.println("fire");
        waitThread.fire();
        
        //生产者/消费者模式
        BlockingQueue<String> queue = new ConditionBlockingQueue<>(10);
	    new ProducerConsumer.Producer(queue).start();
	    new ProducerConsumer.Consumer(queue).start();
    }

    @Override
    public void run() {
        try {
            lock.lock();
            try {
                while (!fire) {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
            System.out.println("fired");
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }

    public void fire() {
        lock.lock();
        try {
            this.fire = true;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
    

}