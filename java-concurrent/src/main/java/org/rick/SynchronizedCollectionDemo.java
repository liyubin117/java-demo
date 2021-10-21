package org.rick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 同步容器
 * 此处线程安全针对的是容器对象，指的是当多个线程并发访问同一个容器对象时，不需要额外的同步操作，也不会出现错误的结果
 * 不是绝对安全的：
 * 	复合操作，比如先检查再更新
 * 	伪同步
 * 	迭代
 */
public class SynchronizedCollectionDemo {

	public static void main(String[] args) throws InterruptedException {
		//复合操作
		Map<String,Integer> map=new HashMap<>();
		final EnhancedMap<String, Integer> eMap=new EnhancedMap<>(map);
		
		Runnable  run=new Runnable(){
			@Override
			public void run() {
				eMap.putIfAbsent("a", 1);
			}			
		};
		
		final int count=50000;
		Thread[] s=new Thread[count];
		for(int i=0;i<count;i++){
			s[i]=new Thread(run);
			s[i].start();
		}
		for(int i=0;i<count;i++){
			s[i].join();
		}
		//若是线程安全的，只应该出现一次null，但很可能出现多次
		System.out.println(eMap);
		
		
		//遍历操作
		final List<String> list = Collections
	            .synchronizedList(new ArrayList<String>());
		//一边修改一边遍历同步容器，会抛出ConcurrentModificationException异常，因为遍历时容器产生了结构性变化，触发异常
	    SCIterator.startIteratorThread(list);
	    SCIterator.startModifyThread(list);
	}
}

//对一个同步容器进行复合操作的装饰类。会出现并发问题
class EnhancedMap <K, V> {
    Map<K, V> map;
    
    public EnhancedMap(Map<K,V> map){
        this.map = Collections.synchronizedMap(map);
    }
    
    /**
     * 解决方法：
     * 加锁，将复合操作变为原子操作
     */
    public 
    //synchronized 
    V putIfAbsent(K key, V value){
         V old = map.get(key);
         if(old!=null){
             return old;
         }else{
        	 System.out.println("null");
         }
         map.put(key, value);
         return null;
     }
    
    //伪同步
    /**
     * 若只在此处用synchronized修饰putIfAbsent方法，仍无法实现线程安全，是伪同步，因为同步错对象了。
     * putIfAbsent获得EnhancedMap对象锁，而put是Collections.synchronizedMap(map)返回的map对象的方法，不受此锁影响
     * 两种解决方法：
     * 1、都使用EnhancedMap对象锁，即在putIfAbsent和put上都加上synchronized
     * 2、都使用map对象锁，即在两个方法内的代码块上都加上synchronized(map){}
     */
    //synchronized
    public void put(K key, V value){
        map.put(key, value);
    }
    
    public String toString(){
    	return this.map.toString();
    }
}

//迭代同步容器，会出现并发问题
class SCIterator{
	//修改list的线程
	public static void startModifyThread(final List<String> list) {
	    Thread modifyThread = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            for (int i = 0; i < 100; i++) {
	                list.add("item " + i);
	                try {
	                    Thread.sleep((int) (Math.random() * 10));
	                } catch (Exception e) {
	                	return;
	                }
	            }
	        }
	    });
	    modifyThread.start();
	}
	//遍历list的线程
	public static void startIteratorThread(final List<String> list) {
	    Thread iteratorThread = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            while (true) {
	            	/**
	            	 * 解决方法：
	            	 * 遍历时对整个同步容器对象加锁
	            	 */
//	            	synchronized(list){
	            		System.out.println(list.toString());
	            		try {
							Thread.sleep((int) (Math.random() * 10));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		                for (@SuppressWarnings("unused") String str : list) {
		                }
//	            	}
	            }
	        }
	    });
	    iteratorThread.start();
	}	
}

