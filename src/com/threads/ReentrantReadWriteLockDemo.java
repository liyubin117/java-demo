package com.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 读写锁
 *
 */
public class ReentrantReadWriteLockDemo {
    private Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();
    
    public static void main(String[] args) throws InterruptedException {
    	final ReentrantReadWriteLockDemo rwLock=new ReentrantReadWriteLockDemo();
    	final String key="1";
    	rwLock.put(key, key);
    	Runnable writeRun=new Runnable(){
			@Override
			public void run() {
				rwLock.put(key, Integer.valueOf(rwLock.get(key).toString())+1);
			}
    	};
    	Runnable readRun=new Runnable(){
			@Override
			public void run() {
				System.out.println(rwLock.get(key));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
    	};

    	
    	//1个线程写，3个线程同时读，若用ReentrantLock，只能一个接一个出现，但用了读写锁，可同时出现。且由于写阻塞读，写在读前1ms执行，会在写线程完成后再同时出现
    	int max=3;
    	Thread[] s=new Thread[max];
		for(int i=0;i<max;i++){
			s[i]=new Thread(readRun);
		}
    	Thread t=new Thread(writeRun);
    	t.start();
    	t.join(1);
		for(int i=0;i<max;i++){
			s[i].start();
		}
		
	}

    public Object get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Object put(String key, Object value) {
        writeLock.lock();
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }
}