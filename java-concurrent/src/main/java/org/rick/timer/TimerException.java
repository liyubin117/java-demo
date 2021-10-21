package org.rick.timer;

import java.util.Timer;
import java.util.TimerTask;
/**
 * 一个定时任务的未处理异常会导致所有定时任务被取消
 */
public class TimerException {

    static class TaskA extends TimerTask {
        
        @Override
        public void run() {
            System.out.println("task A");
        }
    }
    
    static class TaskB extends TimerTask {
        
        @Override
        public void run() {
            System.out.println("task B");
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TaskA(), 1, 1000);
        timer.schedule(new TaskB(), 2000, 1000);
    }
}