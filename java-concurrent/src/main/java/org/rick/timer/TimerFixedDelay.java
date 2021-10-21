package org.rick.timer;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 固定延时执行
 *
 */
public class TimerFixedDelay {
	
    public static void main(String[] args) throws InterruptedException {
    	//第一个10ms后运行一次，耗时3秒后，第二个开始重复执行，2秒一次（但固定延时和固定频率的运行次数是不同的）
        Timer timer = new Timer();

        timer.schedule(new LongRunningTask(), 10);
        //固定延时
//        timer.schedule(new FixedDelayTask(), 100, 2000);
        //固定频率
        timer.scheduleAtFixedRate(new FixedDelayTask(), 100, 2000);
    }

    static class LongRunningTask extends TimerTask {
        @Override
        public void run() {
            System.out.println(getNow()+"---LongRunningTask starting...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            System.out.println(getNow()+"---LongRunningTask finished...");
        }
    }

    static class FixedDelayTask extends TimerTask {
        @Override
        public void run() {
            System.out.println(getNow()+"---FixedDelayTask finished...");
        }
    }
    
    private static String getNow(){
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }

}