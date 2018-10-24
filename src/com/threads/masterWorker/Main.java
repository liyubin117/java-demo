package com.threads.masterWorker;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Master master = new Master(new Worker(), 10);
        //提交任务
        for(int i=1;i<=100;i++){
            Task t = new Task();
            t.setId(i);
            t.setName("任务"+i);
            t.setPrice(new Random().nextInt(1000));
            master.submit(t);
        }
        //执行任务
        master.execute();
        //子进程全部执行完任务后，停止主进程并打印通知
        long start = System.currentTimeMillis();
        while(true){
            if(master.isCompleted()){
                long end = System.currentTimeMillis();
                System.out.println("结果是："+master.getResult());
                System.out.println("执行耗时："+(end-start));
                break;
            }
        }
    }
}
