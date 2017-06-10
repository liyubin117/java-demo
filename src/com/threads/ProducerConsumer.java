package com.threads;

import java.util.ArrayDeque;
import java.util.Queue;

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

	public static void main(String[] args) {
		MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
	    new Producer(queue).start();
	    new Consumer(queue).start();

	}
	
	//协作的共享变量是队列
	static class MyBlockingQueue<E> {
	    private Queue<E> queue = null;
	    private int limit;

	    public MyBlockingQueue(int limit) {
	        this.limit = limit;
	        queue = new ArrayDeque<>(limit);
	    }

	    public synchronized void put(E e) throws InterruptedException {
	        while (queue.size() == limit) {
	            wait();
	        }
	        queue.add(e);
	        notifyAll();
	    }

	    public synchronized E take() throws InterruptedException {
	        while (queue.isEmpty()) {
	            wait();
	        }
	        E e = queue.poll();
	        notifyAll();
	        return e;
	    }
	}
	
	//生产者
	static class Producer extends Thread {
	    MyBlockingQueue<String> queue;

	    public Producer(MyBlockingQueue<String> queue) {
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
	    MyBlockingQueue<String> queue;

	    public Consumer(MyBlockingQueue<String> queue) {
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
}


