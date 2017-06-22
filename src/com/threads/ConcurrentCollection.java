package com.threads;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
//并发容器
public class ConcurrentCollection {

	public static void main(String[] args) throws InterruptedException {
		/**
		 * CopyOnWriteArrayList
		 */
		//写时拷贝List，支持容器被修改时，并发迭代读取
		final List<String> list=new CopyOnWriteArrayList<>();
		SCIterator.startIteratorThread(list);
		SCIterator.startModifyThread(list);

		//JDK1.8前CopyOnWriteArrayList的迭代器不支持修改操作，也不支持一些依赖迭代器修改方法的操作，比如Collections的sort方法
		CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<>();
	    list1.add("c");
	    list1.add("a");
	    list1.add("b");
	    Collections.sort(list1);
	    System.out.println(list1);
	    
	    /**
	     * ConcurrentHashMap
	     */
	    //HashMap非线程安全，jdk1.7下CPU会达到100%，死循环。因为1.7里HashMap的table[i]后是一个链表，并发更新极可能形成环，出现死循环。jdk1.8是用红黑树实现的，没这个问题
	    unsafeConcurrentUpdate();
	    
	}

	
	
	public static void unsafeConcurrentUpdate() {
	    final Map<Integer, Integer> map = new HashMap<>();
	    for (int i = 0; i < 10; i++) {
	        Thread t = new Thread() {
	            Random rnd = new Random();

	            @Override
	            public void run() {
	                for (int i = 0; i < 100; i++) {
	                    map.put(rnd.nextInt(), 1);
	                }
	            }
	        };
	        t.start();
	    }
	}    
}
