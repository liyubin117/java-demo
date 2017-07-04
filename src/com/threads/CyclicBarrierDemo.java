package com.threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/**
 * 循环栅栏
 *
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int num = 3;
        Tourist[] threads = new Tourist[num];
        CyclicBarrier barrier = new CyclicBarrier(num, new Runnable() {
            @Override
            public void run() {
                System.out.println("all arrived " + getNow()
                        + " executed by " + Thread.currentThread().getName());
            }
        });
        for (int i = 0; i < num; i++) {
            threads[i] = new Tourist(barrier);
            threads[i].start();
        }
    }
    
    static class Tourist extends Thread {
        CyclicBarrier barrier;

        public Tourist(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                // 模拟先各自独立运行
                Thread.sleep((int) (Math.random() * 1000));

                // 集合点A
                barrier.await();

                System.out.println(this.getName() + " arrived A "
                        + getNow());

                // 集合后模拟再各自独立运行
                Thread.sleep((int) (Math.random() * 1000));

                // 集合点B
                barrier.await();
                System.out.println(this.getName() + " arrived B "
                        + getNow());
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
        }
    }
    
    private static String getNow(){
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}