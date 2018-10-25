package com.threads;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 线程协调工具类，比wait/notify好用
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        Thread s1 = new Thread(){
          @Override
          public void run(){
              System.out.println("线程1启动...，等待线程2、3完成后通知其");
              try {
                  latch.await();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println("线程1完成");
              System.out.println("end:"+System.currentTimeMillis());
          }
        };
        Thread s2 = new Thread(){
            @Override
            public void run(){
                System.out.println("线程2启动...，执行1.5s");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2完成...");
                latch.countDown();

            }
        };
        Thread s3 = new Thread(){
            @Override
            public void run(){
                System.out.println("线程3启动...，执行2s");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程3完成...");
                latch.countDown();

            }
        };

        System.out.println("begin:"+System.currentTimeMillis());
        s1.start();
        s2.start();
        s3.start();
    }
}
