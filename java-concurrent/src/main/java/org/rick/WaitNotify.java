package org.rick;

import java.util.ArrayList;

public class WaitNotify{
    private volatile int i = 0;
    public void putList(){
        i++;
        System.out.println(Thread.currentThread().getName()+" 加1");
    }
    public int getSize(){
        return i;
    }

    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify();
        Object lock = new Object();


//        Thread insert = new Thread(){
//            @Override
//            public void run(){
//                for(int i=0;i<10;i++){
//                    wn.putList();
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        Thread get = new Thread(){
//            @Override
//            public void run(){
//                while(true){
//                    if(wn.getSize()==5){
//                        System.out.println("元素数量等于5");
//                        throw new RuntimeException();
//                    }
//                }
//            }
//        };

        Thread insert = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("insert开始");
                try {
                    synchronized (lock) {
                        for (int i = 0; i < 10; i++) {
                            wn.putList();
                            Thread.sleep(50);
                            if (wn.getSize() == 5) {
                                System.out.println("数量达到5，发出notify");
                                lock.notify();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
            }
        }, "insert");
        Thread get = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("get开始");
                synchronized (lock){
                    System.out.println("获取到的数量是"+wn.getSize());
                    if(wn.getSize()!=5){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("数量达到5，get接收到通知");

                }
                System.out.println(Thread.currentThread().getName()+"结束");
                throw new RuntimeException();
            }
        }, "get");
        //notify不释放锁，只是唤醒沉睡的线程，而不会立即释放锁，锁的释放要看代码块的具体执行情况
        get.start();
        insert.start();

    }
}
