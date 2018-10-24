package com.threads.masterWorker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {
    private ConcurrentLinkedQueue<Task> workQueue;
    private ConcurrentHashMap<String,Object> resultMap;

    @Override
    public void run(){
        while(true){
            Task input = this.workQueue.poll();
            if(input == null) break;
            //业务处理
            Object output = handle(input);
            this.resultMap.put(Integer.toString(input.getId()), output);
        }
    }

    public Object handle(Task task){
        Object output = null;
        try {
            output = task.getPrice();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue){
        this.workQueue = workQueue;
    }
    public void setResultMap(ConcurrentHashMap<String,Object> resultMap){
        this.resultMap = resultMap;
    }
}
