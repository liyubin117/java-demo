package com.threads;

import com.threads.BlockingQueue.MyBlockingQueue;

//线程协作的应用：生产者/消费者模式
/**
 * JDK中的实现，实际开发中应该用这些类：
 * 	•接口BlockingQueue和BlockingDeque
	•基于数组的实现类ArrayBlockingQueue
	•基于链表的实现类LinkedBlockingQueue和LinkedBlockingDeque
	•基于堆的实现类PriorityBlockingQueue
 *
 */
public class ProducerConsumer {

	//生产者
	static class Producer extends Thread {
	    BlockingQueue<String> queue;

	    public Producer(BlockingQueue<String> queue) {
	        this.queue = queue;
	    }

	    @Override
	    public void run() {
	        int num = 0;
	        try {
	            while (true) {
	                String task = String.valueOf(num);
	                queue.put(task);
	                System.out.println("produce task " + task);
	                num++;
	                Thread.sleep((int) (Math.random() * 3000));
	            }
	        } catch (InterruptedException e) {
	        }
	    }
	}
	
	//消费者
	static class Consumer extends Thread {
		BlockingQueue<String> queue;

	    public Consumer(BlockingQueue<String> queue) {
	        this.queue = queue;
	    }

	    @Override
	    public void run() {
	        try {
	            while (true) {
	                String task = queue.take();
	                System.out.println("handle task " + task);
	                Thread.sleep((int)(Math.random()*3000));
	            }
	        } catch (InterruptedException e) {
	        }
	    }
	}
	
	public static void main(String[] args) {
		BlockingQueue<String> queue = new MyBlockingQueue<>(10);
	    new Producer(queue).start();
	    new Consumer(queue).start();

	}
}


