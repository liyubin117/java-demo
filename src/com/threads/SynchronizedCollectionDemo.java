package com.threads;

import java.util.Collections;
import java.util.HashMap;
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
		Map<String,Integer> map=new HashMap<>();
		EnhancedMap<String, Integer> eMap=new EnhancedMap<>(map);
		
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
	}

}

//对一个同步容器进行复合操作的装饰类。会出现并发问题
class EnhancedMap <K, V> {
    Map<K, V> map;
    
    public EnhancedMap(Map<K,V> map){
        this.map = Collections.synchronizedMap(map);
    }
    
    public V putIfAbsent(K key, V value){
         V old = map.get(key);
         if(old!=null){
             return old;
         }else{
        	 System.out.println("null");
         }
         map.put(key, value);
         return null;
     }
    
    public void put(K key, V value){
        map.put(key, value);
    }
    
    public String toString(){
    	return this.map.toString();
    }
}
class EnhancedMapThread extends Thread{

	@Override
	public void run() {
	
	}
	
}