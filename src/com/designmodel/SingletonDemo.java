package com.designmodel;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;

//单例模式
public class SingletonDemo{

    @Test
    public void testSingleThread(){
        Singleton s1=Singleton.getInstance();
        Singleton s2=Singleton.getInstance();
        System.out.println(s1==s2);
    }

    @Test
    public void testMultiThread() throws InterruptedException {
        ConcurrentSkipListSet<Object> set = new ConcurrentSkipListSet<>();
        int count = 999999;
        CountDownLatch latch = new CountDownLatch(count);
        for(int i=0;i<count;i++){
            new Thread(() -> {
                set.add(ConcurrentSingleton.getInstance());
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(set.size());
    }
}

class Singleton{
	private static Singleton instance;
	
	private Singleton(){
		System.out.println("make Singleton instance");
	}
	
	public static Singleton getInstance(){
		if(instance==null){
			instance=new Singleton();
		}
		return instance;
	}
}

class ConcurrentSingleton implements Comparable{
    //use volatile to aviod cpu instruction orderless
    private volatile static ConcurrentSingleton INSATNCE;
    private ConcurrentSingleton(){
        System.out.println("make Concurrent Singleton instance");
    }
    public static ConcurrentSingleton getInstance(){
        System.out.println(Thread.currentThread());
        //DCL: double check lock
        if(INSATNCE==null){
            synchronized (ConcurrentSingleton.class){
                if(INSATNCE==null)
                    INSATNCE = new ConcurrentSingleton();
            }
        }
        return INSATNCE;
    }

    @Override
    public int compareTo(Object o) {
        if(o==null)
            return -1;
        if(o==this)
            return 0;
        return -1;
    }

}
