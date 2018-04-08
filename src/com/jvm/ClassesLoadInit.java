package com.jvm;

/**
 * 类初始化
 * 在多线程环境中会被正确加锁、同步，若有多个线程同时初始化一个类，只会有一个线程得以执行，且这个线程执行后其他线程无法再次执行此类的初始化
 */
public class ClassesLoadInit
{
    static class DeadLoopClass
    {
        static
        {
            if(true)
            {
                System.out.println(Thread.currentThread()+"init DeadLoopClass");
                while(true)
                {
                }
            }
        }
    }
 
    public static void main(String[] args)
    {
        Runnable script = new Runnable(){
            public void run()
            {
                System.out.println(Thread.currentThread()+" start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread().getName()+" run over");
            }
        };
 
        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();
    }
}