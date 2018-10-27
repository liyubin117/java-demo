package com.threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                System.out.println("都到达 " + getNow()+ " 发出通知者： " + Thread.currentThread().getName());
            }
        });
//        for (int i = 0; i < num; i++) {
//            threads[i] = new Tourist(barrier);
//            threads[i].start();
//        }

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Tourist(barrier));
        executor.submit(new Tourist(barrier));
        executor.submit(new Tourist(barrier));
        executor.shutdown();
    }
    
    static class Tourist extends Thread {
        CyclicBarrier barrier;
        int timeout;

        public Tourist(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            timeout = new Random().nextInt(3) * 3000;
            try {
                // 模拟先各自独立运行
                System.out.println(this.getName()+"开始准备... "+getNow());
                Thread.sleep(timeout);

                // 集合点A
                barrier.await();

                System.out.println(this.getName() + "到达A点 "+ getNow());

                // 集合后模拟再各自独立运行
                Thread.sleep(timeout);

                // 集合点B
                barrier.await();
                System.out.println(this.getName() + "到达B点 "+ getNow());
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
        }
    }
    
    private synchronized static String getNow(){
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}