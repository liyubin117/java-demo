package com.designmodel;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;

//单例模式
public class SingletonDemo{

    @Test
    public void testSingleThread(){
        LazySingleton s1=LazySingleton.getInstance();
        LazySingleton s2=LazySingleton.getInstance();
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

//饿汉式
class EagerSingleton{
    private static EagerSingleton singleton = new EagerSingleton();
    private EagerSingleton(){}
    public static EagerSingleton getInstance(){
        return singleton;
    }
}
//懒汉式，不适用于多线程
class LazySingleton{
    private static LazySingleton singleton;
    private LazySingleton(){}
    public static LazySingleton getInstance(){
        if(singleton==null){
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
//静态内部类
class StaticSingleton{
    private static class SingletonFactory{
        private static StaticSingleton instance = new StaticSingleton();
    }
    private StaticSingleton(){}
    public static StaticSingleton getInstance(){
        return SingletonFactory.instance;
    }
}
//enum
enum EnumSingleton{
    instance;
}
