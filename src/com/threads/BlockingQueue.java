package com.threads;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//阻塞队列，支持多线程添加、删除
//可作为生产者/消费者模式协作的共享变量
public abstract class BlockingQueue<E> {
    public abstract void put(E e) throws InterruptedException;

    public abstract E take() throws InterruptedException;
    
    //使用Object对象的wait/notify协作多线程
    static class  ObjectBlockingQueue<E> extends BlockingQueue<E>{
    	private Queue<E> queue = null;
        private int limit;

    	public ObjectBlockingQueue(int limit) {
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
    
    //使用Condition对象的await/signal协作多线程
	static class ConditionBlockingQueue<E> extends BlockingQueue<E>{
    	private Queue<E> queue = null;
        private int limit;
        
	    private Lock lock = new ReentrantLock();
	    private Condition notFull  = lock.newCondition();
	    private Condition notEmpty = lock.newCondition();


	    public ConditionBlockingQueue(int limit) {
	    	this.limit = limit;
	    	queue = new ArrayDeque<>(limit);
	    }

	    public void put(E e) throws InterruptedException {
	        lock.lockInterruptibly();
	        try{
	            while (queue.size() == limit) {
	                notFull.await();
	            }
	            queue.add(e);
	            notEmpty.signal();    
	        }finally{
	            lock.unlock();
	        }
	    }

	    public E take() throws InterruptedException {
	        lock.lockInterruptibly();
	        try{
	            while (queue.isEmpty()) {
	                notEmpty.await();
	            }
	            E e = queue.poll();
	            notFull.signal();
	            return e;    
	        }finally{
	            lock.unlock();
	        }
	    }
	}
}

